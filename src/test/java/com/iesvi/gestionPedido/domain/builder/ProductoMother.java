package com.iesvi.gestionPedido.domain.builder;

import com.iesvi.gestionPedido.domain.ProductoVO;

public class ProductoMother {

    public static ProductoVO productoGeneral() {
        return new ProductoVOBuilder().build();
    }

    public static ProductoVO productoUno() {

        return new PizzaVOBuilder()
                .withCodigo("PROUNO")
                .withNombre("Producto Uno")
                .withPrecio(9.95)
                .build();
    }

    public static ProductoVO productoDos() {

        return new PizzaVOBuilder()
                .withCodigo("PRODOS")
                .withNombre("Producto Dos")
                .withPrecio(8.95)
                .build();
    }

    public static ProductoVO productoTres() {

        return new PizzaVOBuilder()
                .withCodigo("PRODOS")
                .withNombre("Producto Tres")
                .withPrecio(6.50)
                .build();
    }



}