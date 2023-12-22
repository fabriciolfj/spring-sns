package com.github.fabriciolfj.demosns.service;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.listener.acknowledgement.Acknowledgement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceListener {

    private static final Logger LOG = LoggerFactory.getLogger(ProducerServiceEvents.class);

    @SqsListener(value = "${aws.sqs.queue}")
    public void receive(final String message) {
        LOG.info("message receive {}", message);
    }
}