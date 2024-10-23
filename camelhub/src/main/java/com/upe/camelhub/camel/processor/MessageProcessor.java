package com.upe.camelhub.camel.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Map;

@Slf4j
public class MessageProcessor implements Processor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {
        Map<String, Object> pedidoMap = exchange.getIn().getBody(Map.class);

        log.info("Corpo recebido pelo MessageProcessor: {}", pedidoMap);

        Long produtoId = ((Number) pedidoMap.get("produtoId")).longValue();
        int quantidade = ((Number) pedidoMap.get("quantidade")).intValue();

        ObjectNode resultadoJson = objectMapper.createObjectNode();
        resultadoJson.put("id", produtoId);
        resultadoJson.put("quantidade", quantidade);

        log.info("Resultado a ser enviado: {}", resultadoJson);

        byte[] resultadoBytes = objectMapper.writeValueAsBytes(resultadoJson);

        exchange.getIn().setBody(resultadoBytes);
    }
}
