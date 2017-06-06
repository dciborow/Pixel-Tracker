package com.microsoft.azure.eventhubs.spring;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.servicebus.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.ServiceBusException;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationCondition;

import java.io.IOException;

/**
 * Created by dcibo on 6/5/2017.
 */
@Configuration
@ConditionalOnClass({com.microsoft.azure.eventhubs.EventHubClient.class})
@Conditional({EventHubAutoConfiguration.EventHubPropertyCondition.class})
@EnableConfigurationProperties({EventHubTemplate.class})
public class EventHubAutoConfiguration {

    private final EventHubTemplate eventHubTemplate;

    public EventHubAutoConfiguration(EventHubTemplate eventHubTemplate) {
        this.eventHubTemplate = eventHubTemplate;
    }

    @Bean
    @ConditionalOnMissingBean({EventHubClient.class})
    public EventHubClient eventHubClient() {
        ConnectionStringBuilder connStr = new ConnectionStringBuilder(
                eventHubTemplate.getServiceBusNamespaceName(),
                eventHubTemplate.getEventHubName(),
                eventHubTemplate.getSharedAccessSignatureKeyName(),
                eventHubTemplate.getSharedAccessSignatureKey());
        try {
            return EventHubClient.createFromConnectionStringSync(connStr.toString());
        } catch (ServiceBusException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static class EventHubPropertyCondition extends AnyNestedCondition {
        public EventHubPropertyCondition(ConfigurationCondition.ConfigurationPhase configurationPhase) {
            super(configurationPhase);
        }

        @ConditionalOnProperty(
                prefix = "azure.eventhub",
                value = {"ServiceBusNamespaceName"}
        )
        static class EventHubServiceBusNamespaceName {
            EventHubServiceBusNamespaceName() {

            }
        }

        @ConditionalOnProperty(
                prefix = "azure.eventhub",
                value = {"EventHubName"}
        )
        static class EventHubName {
            EventHubName() {

            }
        }

        @ConditionalOnProperty(
                prefix = "azure.eventhub",
                value = {"SharedAccessSignatureKeyName"}
        )
        static class EventHubSharedAccessSignatureKeyName {
            EventHubSharedAccessSignatureKeyName() {

            }
        }

        @ConditionalOnProperty(
                prefix = "azure.eventhub",
                value = {"SharedAccessSignatureKey"}
        )
        static class EventHubSharedAccessSignatureKey {
            EventHubSharedAccessSignatureKey() {

            }
        }
    }
}