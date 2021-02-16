package com.iesvi.gestionPedido.application;

//import com.iesvi.gestionPedido.domain.GestionPedidoController;
//import com.iesvi.gestionPedido.domain.PedidoVO;
//import com.iesvi.gestionPedido.domain.ProductoVO;
//import com.iesvi.gestionPedido.domain.repos.PedidoRepo;
//import com.iesvi.gestionPedido.infra.conversores.ConversorPedido;
//import com.iesvi.gestionPedido.infra.conversores.ConversorProducto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class PedidoService implements GestionPedidoController {
//
//    @Autowired
//    PedidoRepo pedidoRepo;
//
//    @Autowired
//    ConversorPedido conversorPedido;
//
//    @Autowired
//    ConversorProducto conversorProducto;
//
//    @Transactional
//    @Override
//    public void realizarPedido(PedidoVO pedidoVO, List<ProductoVO> productos) {
//        //Agregamos todos los productos, al pedido antes de guardar el pedido, y el pedido se encarga de persistir los productos.
//        for (ProductoVO productoVO : productos) {
//            //pedidoVO.addProducto(productoVO);
//        }
//
//        //En este caso, no necesitamos el repositorio de Producto, ya que el pedido es un "Agregado" que sabe persistir el conjunto.
//        pedidoRepo.save(pedidoVO);
//    }
//
//    @Transactional
//    @Override
//    public void modificarPedido(PedidoDTO pedidoDTO) throws Exception {
//        PedidoVO nbd = pedidoRepo.findOne(pedidoDTO.getId_pedido());
//        if (nbd == null)
//            throw new Exception("Pedido no existe");
//
//        //Crear Pedido-entity desde pedido-dto (por ahora lo creamos manualmente)
//
//        List<ProductoVO> listaProductos = conversorProducto.convertLISTA_DTOtoVO(pedidoDTO.getId_Productos());
//
//        //PedidoVO updpedido = new PedidoVO(nbd.getId_pedido(), listaProductos, pedidoDTO.getId_usuario(), pedidoDTO.getTotal(), pedidoDTO.getFecha_entrada(), pedidoDTO.getFecha_entrega(), pedidoDTO.getEstado());
//
//        //pedidoRepo.save(updpedido);
//    }
//
//    @Override
//    public Boolean cancelarPedido(Integer id) {
//        return pedidoRepo.delete(id);
//    }
//
//    @Override
//    public PedidoDTO consultarDatosPedidos(Integer id) {
//        //return conversorPedido.convertVOtoDTO(pedidoRepo.findOne(id));
//        return new PedidoDTO();
//    }
//
//    @Override
//    public void actualizarEstado(Integer id) {
//
//    }
//}