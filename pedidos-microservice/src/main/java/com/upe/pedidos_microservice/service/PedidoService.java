package com.upe.pedidos_microservice.service;

import com.upe.pedidos_microservice.controller.dtos.PedidoRequestDto;
import com.upe.pedidos_microservice.model.Pedido;
import com.upe.pedidos_microservice.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public Pedido salvarPedido(PedidoRequestDto dto){
        Pedido pedido = new Pedido(dto);
        return pedidoRepository.save(pedido);
    }
}
