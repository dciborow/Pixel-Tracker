/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package com.microsoft.azure.eventhubs.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "azure.eventhub"
)
public class EventHubTemplate {

    private String serviceBusNamespaceName;
    private String eventHubName;
    private String sharedAccessSignatureKeyName;
    private String sharedAccessSignatureKey;

    public EventHubTemplate(String eventHubName,
                            String serviceBusNamespaceName,
                            String sharedAccessSignatureKeyName,
                            String sharedAccessSignatureKey) throws Exception {
        if (eventHubName.isEmpty()
                || serviceBusNamespaceName.isEmpty()
                || sharedAccessSignatureKeyName.isEmpty()
                || sharedAccessSignatureKey.isEmpty())
            throw new Exception("Event Hub Parameters not properly set. Check Configuration");

        this.eventHubName = eventHubName;
        this.serviceBusNamespaceName = serviceBusNamespaceName;
        this.sharedAccessSignatureKeyName = sharedAccessSignatureKeyName;
        this.sharedAccessSignatureKey = sharedAccessSignatureKey;
    }

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
