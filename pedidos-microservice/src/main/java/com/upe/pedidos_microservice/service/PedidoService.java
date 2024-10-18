package com.upe.pedidos_microservice.service;

import com.upe.pedidos_microservice.controller.dtos.PedidoRequestDto;
import com.upe.pedidos_microservice.model.Pedido;
import com.upe.pedidos_microservice.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final JmsTemplate jmsTemplate;

    public Pedido salvarPedido(PedidoRequestDto dto){
        Pedido pedido = new Pedido(dto);
        jmsTemplate.convertAndSend("my-pedidos-queue", pedido);
        return pedidoRepository.save(pedido);
    }
}
