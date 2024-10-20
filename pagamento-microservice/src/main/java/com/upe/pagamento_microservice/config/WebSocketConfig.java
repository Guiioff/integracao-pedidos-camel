package com.upe.pagamento_microservice.config;

import com.upe.pagamento_microservice.handler.PagamentoWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(new PagamentoWebSocketHandler(), "/pagamento")
        .setAllowedOrigins("*")
        .addInterceptors(new HttpSessionHandshakeInterceptor());
  }
}
