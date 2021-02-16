package com.iesvi.gestionPedido.domain.builder;

import com.iesvi.gestionPedido.domain.LineaVO;
import com.iesvi.gestionUsuario.domain.*;
import com.iesvi.shared.domain.VOBuilder;
import io.beanmother.core.ObjectMother;
import lombok.NoArgsConstructor;
import lombok.With;

//TODO:Lombok builder annotations
@With
@NoArgsConstructor
//@Builder
public class LineaVOBuilder extends VOBuilder {
    Integer uds=null;
    String codproducto;
    Double precio=null;
    Double total=null;

    private LineaVOBuilder(Integer uds, String codproducto, Double precio, Double total) {
        this.uds = uds;
        this.codproducto = codproducto;
        this.precio = precio;
        this.total = total;
    }



    public LineaVO build() {
        ObjectMother om = ObjectMother.getInstance();
        LineaVO mother= om.bear("LineaVO",LineaVO.class);

        return new LineaVO(
                codproducto!=null ? codproducto : mother.getCodproducto(),
                uds!=null ? uds : mother.getUds(),
                precio!=null ? precio : mother.getPrecio()
        );
    }
}
