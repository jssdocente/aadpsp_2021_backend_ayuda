package com.iesvi.gestionPedido.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity(name = "Linea")
public class LineaVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    String codproducto;

    @Column(nullable = false)
    Integer uds=0;

    @Column(nullable = false)
    Double precio=0d;

    @Column(nullable = false)
    Double total=0.d;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pedidoId",foreignKey = @ForeignKey(name = "PEDIDO_ID_FK"))
    PedidoVO pedido;

    @OneToOne(orphanRemoval = false,fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name="productoId",foreignKey = @ForeignKey(name = "PRODUCTO_ID_FK"))
    ProductoVO producto;

    public void calculateTotals() {
        this.total = uds * precio;
    }

    public LineaVO(String codproducto,Integer uds, Double precio) {
        this.uds = uds;
        this.codproducto = codproducto;
        this.precio = precio;

        calculateTotals();
    }
}