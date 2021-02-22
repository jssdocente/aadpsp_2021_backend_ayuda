package com.iesvi.gestionPedido.domain.repos;

import com.iesvi.gestionPedido.domain.PedidoVO;
import com.iesvi.gestionPedido.domain.ProductoVO;
import com.iesvi.shared.domain.repos.GenericRepo;
import org.springframework.stereotype.Repository;

//public interface ProductoRepo extends GenericRepo<ProductoVO, Integer> {
//}

@Repository
public interface ProductoRepo extends GenericRepo<ProductoVO, Integer> {
}