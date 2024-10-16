package com.upe.pedidos_microservice.controller;

import com.upe.pedidos_microservice.controller.dtos.PedidoRequestDto;
import com.upe.pedidos_microservice.model.Pedido;
import com.upe.pedidos_microservice.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Pedido salvarPedido(@RequestBody PedidoRequestDto dto){
        return pedidoService.salvarPedido(dto);
    }
}
