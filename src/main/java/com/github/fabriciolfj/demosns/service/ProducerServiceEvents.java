package com.github.fabriciolfj.demosns.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceEvents {

    private final static Logger LOG = LoggerFactory.getLogger(ProducerServiceEvents.class);

    private final Topic topic;
    private final AmazonSNS client;

    public ProducerServiceEvents(@Qualifier("productEventsTopic") final Topic productEvent, final AmazonSNS client) {
        this.topic = productEvent;
        this.client = client;
    }

    public void send(final String message) {
        var result = client.publish(topic.getTopicArn(), message, "titulo");
        LOG.info("message send success {}", result.getMessageId());
    }
}