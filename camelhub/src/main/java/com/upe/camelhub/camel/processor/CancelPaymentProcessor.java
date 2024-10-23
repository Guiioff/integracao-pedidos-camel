package com.upe.camelhub.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Map;

public class CancelPaymentProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Map<String, Object> pedidoMap = exchange.getIn().getBody(Map.class);
        pedidoMap.put("status", "CANCELADO");
        exchange.getIn().setBody(pedidoMap);
    }
}
