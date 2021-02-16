package com.iesvi.gestionPedido.infra.persistence.jpa;

import com.iesvi.gestionPedido.domain.PedidoVO;
import com.iesvi.gestionPedido.domain.repos.PedidoRepo;
import com.iesvi.shared.infra.jpa.GenericRepositoryJPA;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoRepositoryJPA extends GenericRepositoryJPA<PedidoVO, Integer> implements PedidoRepo {

    public PedidoRepositoryJPA() {
        super(PedidoVO.class);
    }
}