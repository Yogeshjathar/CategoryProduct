package com.Rest.CategoryProduct.pubsub.brand;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(name = "pubsub.enabled", havingValue = "true")
public class GcpBrandPubSubSubscriber implements BrandPubSubSubscriber{
    private final PubSubTemplate pubSubTemplate;

    public GcpBrandPubSubSubscriber(PubSubTemplate pubSubTemplate) {
        this.pubSubTemplate = pubSubTemplate;
    }

    @PostConstruct
    public void subscribeToBrandEvents() {
        // Subscribe to the Pub/Sub topic and processing the message
        pubSubTemplate.subscribe("yoj-brand-topic-sub", (BasicAcknowledgeablePubsubMessage message) -> {
            String payload = new String(message.getPubsubMessage().getData().toByteArray());
            handleMessage(payload);
            message.ack(); // Acknowledging the message after processing
        });
    }

    public void handleMessage(String message) {
        // Log and process the message
        try {
            JsonObject jsonObject = JsonParser.parseString(message).getAsJsonObject();

            String eventType = jsonObject.get("event").getAsString();

            switch (eventType){
                case "GET_All_BRANDS":
                    int count = jsonObject.has("Count") ? jsonObject.get("Count").getAsInt() : 0;
                    System.out.println("Handling event: " + eventType + ", Brand Count: "+count);
                    break;
                case "BRAND_CREATED":
                case "GET_BRAND":
                case "BRAND_UPDATED":
                case "BRAND_DELETED":
                    long brandId = jsonObject.get("id").getAsLong();
                    String brandName = jsonObject.get("name").getAsString();
                    System.out.println("Handling event: " + eventType + ", Brand ID: " + brandId + ", Brand Name: " + brandName);
                    break;
                default:
                    System.out.println("Unhandled event type: " + eventType);
            }
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
        }
    }
}
