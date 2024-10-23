package com.upe.camelhub.camel.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Map;

public class ApprovePaymentProcessor implements Processor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {
        Map<String, Object> pedidoMap = exchange.getIn().getBody(Map.class);
        String originalBody = exchange.getIn().getHeader("originalBody", String.class);
        Map<String, Object> originalPedido = objectMapper.readValue(originalBody, Map.class);

        pedidoMap.put("quantidade", originalPedido.get("quantidade"));
        pedidoMap.put("produtoId", originalPedido.get("produtoId"));

        exchange.getIn().setBody(pedidoMap);
    }
}
