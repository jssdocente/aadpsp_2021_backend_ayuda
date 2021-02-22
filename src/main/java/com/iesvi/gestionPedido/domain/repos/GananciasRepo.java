package com.iesvi.gestionPedido.domain.repos;

import com.iesvi.gestionPedido.domain.GananciasVO;
import com.iesvi.gestionPedido.domain.PizzaVO;
import com.iesvi.shared.domain.repos.GenericRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//public interface GananciasRepo extends GenericRepo<GananciasVO, Integer> {
//}

@Repository
public interface GananciasRepo extends JpaRepository<GananciasVO, Integer> {
}