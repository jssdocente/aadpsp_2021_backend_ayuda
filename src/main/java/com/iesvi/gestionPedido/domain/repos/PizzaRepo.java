package com.iesvi.gestionPedido.domain.repos;

import com.iesvi.gestionPedido.domain.PedidoVO;
import com.iesvi.gestionPedido.domain.PizzaVO;
import com.iesvi.shared.domain.repos.GenericRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//public interface PizzaRepo extends GenericRepo<PizzaVO, Integer> {
//}

@Repository
public interface PizzaRepo extends JpaRepository<PizzaVO, Integer> {
}