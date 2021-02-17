package com.iesvi.gestionPedido.application.mapper;

import com.iesvi.gestionPedido.application.dto.*;
import com.iesvi.gestionPedido.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoDTO toDTO(PedidoVO vo) {

        List<PedidoDTO.LineapedidoDTO> lines = vo.getLineas().stream()
                .map(lvo -> new PedidoDTO.LineapedidoDTO()
                            .withId(lvo.getId())
                            .withCodproducto(lvo.getCodproducto())
                            .withUds(lvo.getUds())
                            .withPrecio(lvo.getPrecio())
                            .withTotal(lvo.getTotal())
                ).collect(Collectors.toList());

        return new PedidoDTO()
                .withId(vo.getId())
                .withId_usuario(vo.getId_usuario())
                .withEstado(vo.getEstado())
                .withFecha_entrada(vo.getFecha_entrada())
                .withFecha_entrega(vo.getFecha_entrada())
                .withTotal(vo.getTotal())
                .withLineas(lines);
    }

    public static PedidoVO fromDTO(PedidoDTO dto){

        List<LineaVO> lines = dto.getLineas().stream()
                .map((ldto) -> new LineaVO(ldto.getCodproducto(),ldto.getUds(),ldto.getPrecio()))
                .collect(Collectors.toList());

        return new PedidoVO(dto.getId_usuario(),lines);
    }

    public static LineaVO fromLineaDTO(PedidoDTO.LineapedidoDTO ldto ){

        return new LineaVO(ldto.getCodproducto(),ldto.getUds(),ldto.getPrecio());

    }

}
