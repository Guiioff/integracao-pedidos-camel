package com.upe.pedidos_microservice.camel.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MessageProcessor implements Processor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {
        String jsonBody = exchange.getIn().getBody(String.class);

        JsonNode jsonNode = objectMapper.readTree(jsonBody);

        Long produtoId = jsonNode.path("produtoId").asLong();
        int quantidade = jsonNode.path("quantidade").asInt();

        ObjectNode resultadoJson = objectMapper.createObjectNode();
        resultadoJson.put("id", produtoId);
        resultadoJson.put("quantidade", quantidade);

        byte[] resultadoBytes = objectMapper.writeValueAsBytes(resultadoJson);

        exchange.getIn().setBody(resultadoBytes);
    }
}
