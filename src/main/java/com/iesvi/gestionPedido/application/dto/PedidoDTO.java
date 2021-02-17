package com.iesvi.gestionPedido.application.dto;

import java.util.Date;
import java.util.List;

import com.iesvi.gestionPedido.domain.LineaVO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@With
public class PedidoDTO {

    private Integer id;
    private String numero;
    private Integer id_usuario;
    private Double total;
    private Date fecha_entrada, fecha_entrega;
    private String estado;
    private List<LineapedidoDTO> lineas;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @With
    public static class LineapedidoDTO {

        private Integer id;
        private String codproducto;  //No viene el id, solo el codigo de producto
        private Integer uds=0;
        private Double precio=0d;
        private Double total=0.d;

    }
}

