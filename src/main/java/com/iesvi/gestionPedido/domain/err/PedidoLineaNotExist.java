package com.iesvi.gestionPedido.domain.err;

import com.iesvi.gestionPedido.domain.LineaVO;
import com.iesvi.shared.domain.err.DomainError;

public class PedidoLineaNotExist extends DomainError {
    public PedidoLineaNotExist(LineaVO linea) {
        super("LIN.NOT.EXITS", "Pedido: " + linea.getPedido().getId() + ". La linea:" + linea.getId() + " no existe");
    }
}
