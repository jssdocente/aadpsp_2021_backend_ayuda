package com.iesvi.gestionPedido.domain;

import java.util.ArrayList;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "Pizza")
public class PizzaVO extends ProductoVO{

    //TODO: lista separadas por ;
    @Column(length = 150, nullable = false)
    private String ingredientes;

    @Column(nullable = false)
    private Integer tiempo;

    public PizzaVO(String codigo, String nombre, Double precio, String ingredientes, Integer tiempo) {
        super(codigo, nombre, precio);
        this.ingredientes = ingredientes;
        this.tiempo = tiempo;
    }
}