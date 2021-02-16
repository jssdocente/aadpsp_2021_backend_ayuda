package com.iesvi.gestionPedido.application.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@With
public class ProductoDTO {

    private Integer id;
    private String codigo;
    private String nombre;
    private Double precio;

    public ProductoDTO(String codigo, String nombre, Double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }
}