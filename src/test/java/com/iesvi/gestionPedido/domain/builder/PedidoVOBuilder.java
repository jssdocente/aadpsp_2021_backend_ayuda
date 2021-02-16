package com.iesvi.gestionPedido.domain.builder;

import com.iesvi.gestionPedido.domain.*;
import com.iesvi.shared.domain.VOBuilder;
import io.beanmother.core.ObjectMother;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//TODO:Lombok builder annotations
@AllArgsConstructor
@NoArgsConstructor
@With
//@Builder
public class PedidoVOBuilder extends VOBuilder {
    private Integer usuarioId=null;
    private String numero=null;
    private Double total=0d;
    private Date fecha_entrega;
    private String estado="P";

    //private Integer numLineas=2;

    private Set<LineaVO> lineas;

    public PedidoVO build() {
        PedidoVO pedido = new PedidoVO(usuarioId,lineas);
        return pedido;
    }

    private int randomNumLineas() {
        Random r = new Random();
        return r.nextInt(4)+1;
    }
}
