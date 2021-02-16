package com.iesvi.gestionPedido.domain.builder;

import com.iesvi.gestionPedido.domain.ProductoVO;
import com.iesvi.gestionUsuario.domain.UsuarioVO;
import com.iesvi.shared.domain.VOBuilder;
import io.beanmother.core.ObjectMother;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor
@With
//@Builder
public class ProductoVOBuilder extends VOBuilder {

    String codigo=null;
    String nombre=null;
    Double precio=null;

    public ProductoVO build() {

        ObjectMother om = ObjectMother.getInstance();
        ProductoVO mother= om.bear("ProductoVO", ProductoVO.class);

        return new ProductoVO(
                codigo!=null ? codigo : mother.getCodigo(),
                nombre!=null ? nombre : mother.getNombre(),
                precio!=null ? precio : mother.getPrecio()
        );
    }
}
