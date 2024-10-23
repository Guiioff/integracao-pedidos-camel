package com.upe.pedidos_microservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upe.pedidos_microservice.controller.dtos.PedidoRequestDto;
import com.upe.pedidos_microservice.model.Pedido;
import com.upe.pedidos_microservice.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    @Transactional
    public Pedido salvarPedido(PedidoRequestDto dto){
        Pedido pedido = new Pedido(dto);

        try {
            String pedidoJson = converterParaJson(pedido);
            enviarMensagem(pedidoJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter Pedido para JSON", e);
        }

        return pedidoRepository.save(pedido);
    }

    private String converterParaJson(Pedido pedido) throws JsonProcessingException {
        return objectMapper.writeValueAsString(pedido);
    }

    private void enviarMensagem(String message) {
        try {
            jmsTemplate.convertAndSend("pedidos-queue", message);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar Pedido para a fila", e);
        }
    }
}
