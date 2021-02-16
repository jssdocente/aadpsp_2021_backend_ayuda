package com.iesvi.gestionPedido.application.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@With
public class PizzaDTO extends ProductoDTO {

    private String ingredientes;
    private Integer tiempo;

    public PizzaDTO(Integer id, String codigo, String nombre, Double precio, String ingredientes, Integer tiempo) {
        super(id, codigo, nombre, precio);
        this.ingredientes = ingredientes;
        this.tiempo = tiempo;
    }

    public PizzaDTO(String codigo, String nombre, Double precio, String ingredientes, Integer tiempo) {
        super(codigo, nombre, precio);
        this.ingredientes = ingredientes;
        this.tiempo = tiempo;
    }
}