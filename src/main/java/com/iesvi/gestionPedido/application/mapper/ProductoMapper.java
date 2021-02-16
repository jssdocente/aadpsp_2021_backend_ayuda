package com.iesvi.gestionPedido.application.mapper;

import com.iesvi.gestionPedido.application.dto.ProductoDTO;
import com.iesvi.gestionPedido.domain.*;

public class ProductoMapper {

    public static ProductoDTO toDTO(ProductoVO vo) {
        return new ProductoDTO()
                .withCodigo(vo.getCodigo())
                .withNombre(vo.getNombre())
                .withPrecio(vo.getPrecio());
    }

    //TODO: convertDTO ==>  Por ctor
    public ProductoVO fromDTO(ProductoDTO dto){
        return new ProductoVO(dto.getCodigo(),dto.getNombre(),dto.getPrecio());
    }

}
