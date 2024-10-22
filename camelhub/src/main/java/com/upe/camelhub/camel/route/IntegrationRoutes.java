package com.upe.camelhub.camel.route;

import com.upe.camelhub.camel.processor.MessageProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import java.util.Map;

public class IntegrationRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:enviarParaPagamento")
                .log("Enviando para o WebSocket de pagamento: ${body}")
                .to("atmosphere-websocket://localhost:8082/pagamento?sendToAll=true")
                .log("Recebida resposta do pagamento: ${body}");

        from("direct:processarPedido")
                .to("direct:enviarParaPagamento")
                .log("Recebida resposta do pagamento: ${body}")
                .unmarshal().json(JsonLibrary.Jackson)
                .choice()
                .when(simple("${body['status']} == 'APROVADO'"))
                .setHeader("status", constant("CONCLUIDO"))
                .process(exchange -> {
                    Map<String, Object> pedidoMap = exchange.getIn().getBody(Map.class);
                    pedidoMap.put("status", "CONCLUIDO");
                    exchange.getIn().setBody(pedidoMap);
                })
                .otherwise()
                .setHeader("status", constant("CANCELADO"))
                .process(exchange -> {
                    Map<String, Object> pedidoMap = exchange.getIn().getBody(Map.class);
                    pedidoMap.put("status", "CANCELADO");
                    exchange.getIn().setBody(pedidoMap);
                })
                .end()
                .log("Status do pedido atualizado: ${header.status}")
                .log("Pedido atualizado: ${body}")
                .marshal().json(JsonLibrary.Jackson)
                .to("activemq:pedidos-queue");

        from("activemq:pedidos-queue")
                .log("Recebido pedido na fila: ${body}")
                .process(new MessageProcessor())
                .log("Mensagem processada: ${body}")
                .to("spring-rabbitmq:estoque-exchange?routingKey=alterar-estoque");


    }
}
