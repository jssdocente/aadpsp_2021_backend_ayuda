package com.iesvi.gestionPedido.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GananciasVO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ganancias;

    @Column(length = 150, nullable = false)
    private Integer id_pedido;

    @Column(length = 150, nullable = false)
    private double gananciasPedido;
}