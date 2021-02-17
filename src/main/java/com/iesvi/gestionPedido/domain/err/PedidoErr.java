package com.iesvi.gestionPedido.domain.err;

import com.iesvi.gestionPedido.domain.LineaVO;
import com.iesvi.shared.domain.err.DomainError;

public class PedidoErr extends DomainError {
    public PedidoErr(String errcode, String errtext) {
        super(errcode, errtext);
    }
}
