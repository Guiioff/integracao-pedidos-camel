package com.upe.pedidos_microservice.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PedidoRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:pedidos-queue")
                .log("Recebido pedido na fila: ${body}")
                .to("log:received-message-from-activemq");
        
    }
}