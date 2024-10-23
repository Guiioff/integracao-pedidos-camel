package com.upe.camelhub.camel.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upe.camelhub.camel.processor.ApprovePaymentProcessor;
import com.upe.camelhub.camel.processor.CancelPaymentProcessor;
import com.upe.camelhub.camel.processor.MessageProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IntegrationRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        getContext().setStreamCaching(true);

        from("activemq:pedidos-queue")
                .log("Recebido pedido na fila: ${body}")
                .log("Mensagem recebida antes do envio para pagamento: ${body}")
                .to("direct:enviarParaPagamento");

        from("direct:enviarParaPagamento")
                .log("Enviando para o ms de pagamento: ${body}")
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .convertBodyTo(String.class)
                .setHeader("originalBody", simple("${body}"))
                .to("http://localhost:8082/pagamentos?bridgeEndpoint=true")
                .log("Recebida resposta do pagamento: ${body}")
                .unmarshal().json(JsonLibrary.Jackson)
                .choice()
                    .when(simple("${body['statusPagamento']} == 'APROVADO'"))
                        .setHeader("status", constant("CONCLUIDO"))
                        .process(new ApprovePaymentProcessor())
                        .log("Pagamento aprovado. Status do pedido: CONCLUIDO")
                        .to("direct:enviarParaEstoque")
                    .otherwise()
                        .setHeader("status", constant("CANCELADO"))
                        .process(new CancelPaymentProcessor())
                        .log("Pagamento rejeitado. Status do pedido: CANCELADO")
                .end()
                .log("Status do pedido atualizado: ${header.status}");

        from("direct:enviarParaEstoque")
                .process(new MessageProcessor())
                .log("Enviando pedido para o RabbitMQ: ${body}")
                .to("spring-rabbitmq:estoque-exchange?routingKey=alterar-estoque")
                .log("Pedido enviado para o RabbitMQ com sucesso.");
    }
}
