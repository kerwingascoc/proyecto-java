package com.nttdata.dockerized.postgresql.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDate fechaPedido;

    @Column(nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}