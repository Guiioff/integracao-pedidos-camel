package com.upe.pedidos_microservice.model;

import com.upe.pedidos_microservice.controller.dtos.PedidoRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "tb_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long produtoId;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private String nomeCliente;

    public Pedido(PedidoRequestDto dto){
        this.produtoId = dto.produtoId();
        this.quantidade = dto.quantidade();
        this.nomeCliente = dto.nomeCliente();
    }

}
