package com.omni.services.boot.metrics;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
 
import javax.net.ssl.HttpsURLConnection;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.omni.services.boot.SpringbootWebservicesApplication;

@Component
public class MyHealthIndicator implements HealthIndicator {

	private static final String MY_URL = "https://www.google.com/";
    private static final Logger logger = LoggerFactory
            .getLogger(SpringbootWebservicesApplication.class);
 
    @Override
    public Health health() {
        try {
            return checkConnectivity();
        } catch (IOException ex) {
            return checkErroneous(ex);
        }
    }
 
    private Health checkConnectivity() throws IOException, IOException {
        HttpsURLConnection urlConnection = (HttpsURLConnection) new URL(MY_URL)
                .openConnection();
        long millisStart = System.currentTimeMillis();
        int responseCode = urlConnection.getResponseCode();
        long millisDuration = System.currentTimeMillis() - millisStart;
        if (responseCode == 200) {
            return Health.up().withDetail("timeMillis", millisDuration).build();
        }
        return Health.down().withDetail("timeMillis", millisDuration).build();
    }
 
    private Health checkErroneous(IOException ex) {
        logger.error("cannot perform health check", ex);
        StringWriter trace = new StringWriter();
        ex.printStackTrace(new PrintWriter(trace));
        return Health.down().withDetail("IOException", trace.toString()).build();
    }

}
