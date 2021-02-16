package com.iesvi.gestionPedido.domain.builder;

import com.iesvi.gestionPedido.domain.ProductoVO;
import com.iesvi.gestionUsuario.domain.AdministradorVO;
import com.iesvi.gestionUsuario.domain.ClienteVO;
import com.iesvi.gestionUsuario.domain.UsuarioVO;
import com.iesvi.gestionUsuario.domain.builder.AdministradorVOBuilder;
import com.iesvi.gestionUsuario.domain.builder.ClienteVOBuilder;
import com.iesvi.gestionUsuario.domain.builder.UsuarioVOBuilder;

public class PizzaMother {

    public static ProductoVO pizzaMargarita() {

        return new PizzaVOBuilder()
                .withCodigo("PZZMARG")
                .withNombre("Pizza Margarita")
                .withIngredientes("Tomate;Mozzarela;oreganao")
                .withPrecio(9.95)
                .withDuracion(15)
                .build();
    }

    public static ProductoVO pizzaCarbonara() {

        return new PizzaVOBuilder()
                .withCodigo("PZZCARB")
                .withNombre("Pizza Carbonara")
                .withIngredientes("Tomate;Mozzarela;bacon;champiñones")
                .withPrecio(12.95)
                .withDuracion(15)
                .build();
    }

    public static ProductoVO pizzaAmericana() {

        return new PizzaVOBuilder()
                .withCodigo("PZZAMER")
                .withNombre("Pizza America")
                .withIngredientes("Tomate;Mozzarela;bacon;vacuno;salsa-barbacoa")
                .withPrecio(11.95)
                .withDuracion(15)
                .build();
    }
}