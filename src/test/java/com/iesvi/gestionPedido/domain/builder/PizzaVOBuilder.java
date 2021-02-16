package com.iesvi.gestionPedido.domain.builder;

import com.iesvi.gestionPedido.domain.PizzaVO;
import com.iesvi.gestionPedido.domain.ProductoVO;
import com.iesvi.shared.domain.VOBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor
@With
//@Builder
public class PizzaVOBuilder extends VOBuilder {

    String codigo=null;
    String nombre=null;
    Double precio=null;
    String ingredientes=null;
    Integer duracion=null;

    public PizzaVO build() {

        ProductoVO base = new ProductoVOBuilder().build();

        return new PizzaVO(
               codigo!=null ? codigo : base.getCodigo(),
               nombre!=null ? nombre : base.getNombre(),
               precio!=null ? precio : base.getPrecio()
                ,ingredientes,duracion);
    }
}
