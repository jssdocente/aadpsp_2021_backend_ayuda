package com.iesvi.gestionPedido.application;

import com.iesvi.gestionPedido.application.dto.PedidoDTO;
import com.iesvi.gestionPedido.application.mapper.PedidoMapper;
import com.iesvi.gestionPedido.domain.LineaVO;
import com.iesvi.gestionPedido.domain.PedidoVO;
import com.iesvi.gestionPedido.domain.ProductoVO;
import com.iesvi.gestionPedido.domain.repos.PedidoRepo;
import com.iesvi.shared.domain.err.EntityNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepo pedidoRepo;

    @Transactional
    public PedidoVO realizarPedido(PedidoDTO dto) {

        PedidoVO pedido = PedidoMapper.fromDTO(dto);

        //***** faltan las validaciones

        pedidoRepo.save(pedido);

        return pedido;
    }

    @Transactional
    public void modificarPedido(PedidoDTO dto) {

        PedidoVO pedidoBd = pedidoRepo.findById(dto.getId())
                .orElseThrow(() -> new EntityNotExist(PedidoVO.class.toString(),dto.getId()));

        //** Modificar un pedido es dificil, ya que hay que saber muy bien qué se puede modificar y qué no.
        // >> También a nivel de lineas es complejo, ya que habría que comparar las lineas del dto con las del pedido obtenido de BD.

        /* Cosas que no pueden cambiar (por ahora)
         * Estado, FechaEntrada, FechaEntrega, Usuario-crea,
         */
        pedidoBd.setNumero(dto.getNumero());

        /*LO PRIMERO LAS LINEAS QUE ESTÁN EN BD y SE HAN ELIMINADO EN LA ACTUALIZACIÓN ==> no existen en el PedidoDTO
         * LO HAREAMOS C         */
        for (LineaVO lbd: pedidoBd.getLineas()) {
            //tengo que verificar si están en el dto ==> si no se han eliminado del pedido
            Optional<PedidoDTO.LineapedidoDTO> optlindto =  dto.getLineas().stream().filter(ldto -> ldto.getCodproducto().equalsIgnoreCase(lbd.getCodproducto())).findFirst();
            if (!optlindto.isPresent()) {
                //Este producto no está presente en el pedido modificado ==> se ha eliminado ese producto
                pedidoBd.removeLinea(lbd); //borramos la linea del pedido
            }
        }

        /* LINEAS. COMPARAR LINEA a LINEA por los productos y sus unidades*/
        for (PedidoDTO.LineapedidoDTO lin: dto.getLineas()) {
            //tengo que encontrar la linea con ese producto
            Optional<LineaVO> optlinbd =  pedidoBd.getLineas().stream().filter(lbd -> lin.getCodproducto().equalsIgnoreCase(lbd.getCodproducto())).findFirst();
            if (optlinbd.isPresent()) {
                //Ese producto no es nuevo ==> Esta en el DTO y en BD ==> modificar UDS solamente
                LineaVO lbd = optlinbd.get();
                lbd.setUds(lbd.getUds());
                lbd.calculateTotals();

            } else {
                //Ese producto es nuevo ==> Esta en el DTO y no en BD ==> Agregar una linea nueva
                pedidoBd.addLinea(PedidoMapper.fromLineaDTO(lin));
            }
        }

        //**** AHORA SÍ, ACTUALIZAMOS EL PEDIDO EN BD ******

        pedidoRepo.save(pedidoBd);
    }

    public Boolean cancelarPedido(Integer id) {

        pedidoRepo.deleteById(id);

        return true;
    }

    public PedidoDTO consultarDatosPedidos(Integer id) {

        return PedidoMapper.toDTO(pedidoRepo.findById(id)
                .orElse(new PedidoVO()));
    }

    public void actualizarEstado(Integer id) {

    }
}