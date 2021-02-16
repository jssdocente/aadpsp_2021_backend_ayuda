package com.iesvi.gestionPedido.application.mapper;

import com.iesvi.gestionPedido.application.dto.PizzaDTO;
import com.iesvi.gestionPedido.domain.*;

public class PizzaMapper {

    public static PizzaDTO toDTO(PizzaVO vo) {
        return new PizzaDTO(vo.getCodigo(),vo.getNombre(),vo.getPrecio(),vo.getIngredientes() ,vo.getTiempo());


    }

    //TODO: convertDTO ==>  Por ctor
    public PizzaVO fromDTO(PizzaDTO dto){
        return new PizzaVO(dto.getCodigo(),dto.getNombre(),dto.getPrecio(),dto.getIngredientes(),dto.getTiempo());
    }

}
