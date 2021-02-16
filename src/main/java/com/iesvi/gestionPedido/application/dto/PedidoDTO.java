package com.iesvi.gestionPedido.application.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoDTO {

    private Integer id_pedido;
    private List<ProductoDTO> id_Productos;
    private Integer id_usuario;
    private Double total;
    private Date fecha_entrada, fecha_entrega;
    private String estado;

    private List<PedidoDTO> lineaPedido;
}

class LineaPedido {

    private Integer id_linea;

}