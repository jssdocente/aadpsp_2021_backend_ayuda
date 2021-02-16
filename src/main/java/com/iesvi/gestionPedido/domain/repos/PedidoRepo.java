package com.iesvi.gestionPedido.domain.repos;

import com.iesvi.gestionPedido.domain.PedidoVO;
import com.iesvi.shared.domain.repos.GenericRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepo extends GenericRepo<PedidoVO, Integer> {
}