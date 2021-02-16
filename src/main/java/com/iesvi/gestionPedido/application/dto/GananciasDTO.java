package com.iesvi.gestionPedido.application.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GananciasDTO{

    private Integer id_pedido;
    private Integer id_ganancia;
    private double ganancias_pedido;
 
}