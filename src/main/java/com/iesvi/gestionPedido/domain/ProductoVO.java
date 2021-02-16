package com.iesvi.gestionPedido.domain;

import javax.persistence.*;


import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ProductoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //TODO:Codigo es necesario
    @Column(length = 10, unique = true, nullable = false)
    private String codigo;

    @Column(length = 150, nullable = false)
    private String nombre;

    //TODO:valores numericos no tienen longitud
    @Column(nullable = false)
    private Double precio;

    public ProductoVO(String codigo, String nombre, Double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    //TODO:En producto no hay cantidad
//    @Column(nullable = false)
//    private Integer cantidad;
}