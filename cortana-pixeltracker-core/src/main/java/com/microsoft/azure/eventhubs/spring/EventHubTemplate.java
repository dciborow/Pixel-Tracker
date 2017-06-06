package com.microsoft.azure.eventhubs.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by dcibo on 6/5/2017.
 */
@ConfigurationProperties(
        prefix = "azure.eventhub"
)
public class EventHubTemplate {

    private String serviceBusNamespaceName;
    private String eventHubName;
    private String sharedAccessSignatureKeyName;
    private String sharedAccessSignatureKey;

    public EventHubTemplate setServiceBusNamespaceName(String serviceBusNamespaceName) {
        this.serviceBusNamespaceName = serviceBusNamespaceName;
        return this;
    }

    public EventHubTemplate setEventHubName(String eventHubName) {
        this.eventHubName = eventHubName;
        return this;
    }

    public EventHubTemplate setSharedAccessSignatureKeyName(String sharedAccessSignatureKeyName) {
        this.sharedAccessSignatureKeyName = sharedAccessSignatureKeyName;
        return this;
    }

    public EventHubTemplate setSharedAccessSignatureKey(String sharedAccessSignatureKey) {
        this.sharedAccessSignatureKey = sharedAccessSignatureKey;
        return this;
    }

    public String getServiceBusNamespaceName() {
        return serviceBusNamespaceName;
    }

    public String getEventHubName() {
        return eventHubName;
    }

    public String getSharedAccessSignatureKeyName() {
        return sharedAccessSignatureKeyName;
    }

    public String getSharedAccessSignatureKey() {
        return sharedAccessSignatureKey;
    }

}
