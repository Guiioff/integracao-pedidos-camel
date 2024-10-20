package com.upe.pagamento_microservice.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
public class PagamentoWebSocketHandler extends TextWebSocketHandler {

  private final ObjectMapper objectMapper = new ObjectMapper();

  private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    sessions.put(session.getId(), session);
    log.info("New WebSocket connection established: " + session.getId());
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    sessions.remove(session.getId());
    log.info("WebSocket connection closed: " + session.getId());
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    String payload = message.getPayload();
    log.info("Received payload: " + payload);

    Map<String, Object> pedidoMap = objectMapper.readValue(payload, Map.class);

    boolean pagamentoAprovado = new Random().nextBoolean();
    String resultadoPagamento = pagamentoAprovado ? "APROVADO" : "REJEITADO";

    pedidoMap.put("status", resultadoPagamento);

    String jsonResp = objectMapper.writeValueAsString(pedidoMap);
    session.sendMessage(new TextMessage(jsonResp));
  }
}
