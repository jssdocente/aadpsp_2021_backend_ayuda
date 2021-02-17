package com.iesvi.gestionPedido.domain;

import javax.persistence.*;
import javax.sound.sampled.Line;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.*;

import com.iesvi.gestionPedido.domain.err.PedidoErr;
import com.iesvi.gestionPedido.domain.err.PedidoLineaNotExist;
import lombok.*;

@Entity(name = "Pedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoVO implements Serializable {

    public static String PENDIENTE="P";
    public static String DELIVERY="D";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //TODO: Tengo que calcular el numero siguiente a través
    @Column()
    private String numero="";

    @Column(nullable = false)
    private Integer id_usuario;

    @Column()
    private Double total=0d;

    @Column(updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_entrada;

    //TODO: debe poder ser null, porque hasta que no se entregue, será null.
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_entrega=null;

    //TODO: un estado por defecto
    @Column(length = 2, nullable = false)
    private String estado="P";

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    //private Set<LineaVO> lineas = new HashSet<LineaVO>(0);
    private List<LineaVO> lineas = new ArrayList<>();

    public PedidoVO(Integer id_usuario, List<LineaVO> lineas) {
        this.id_usuario = id_usuario;

        this.fecha_entrada = new Date();
        this.fecha_entrega = null;

        this.estado = PENDIENTE;

        //lineas.forEach((linea) -> addLinea(linea)); //AMBAS LINEAS SON IGUALES
        if (lineas!=null)
            lineas.forEach(this::addLinea);

        calculateTotal();
    }

    public void changeToDelivered() {
        this.estado = DELIVERY;
    }

    public void addLinea(LineaVO linea) {
        if (lineas==null)
            lineas = new ArrayList<>();

        lineas.add(linea);
        linea.setPedido(this);

        calculateTotal();
    }

    public void removeLinea(LineaVO linea) {
        if (linea==null)
            throw new PedidoErr("LIN.NULL","REMOVE LINEA PARAM IS NULL");

        if (lineas==null)
            throw new PedidoLineaNotExist(linea);

        lineas.remove(linea);
        linea.setPedido(null);

        calculateTotal();
    }

    //+++++ METODOS PRIVADOS ++++++
    void calculateTotal() {

        this.total=0d;
        lineas.forEach((linea) -> {
            linea.calculateTotals();
            this.total+=linea.total;
        });

        this.total = Math.round(this.total * 100.0)/100.0;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PedidoVO pedidoVO = (PedidoVO) o;

        if (!id.equals(pedidoVO.id)) return false;
        if (!numero.equals(pedidoVO.numero)) return false;
        if (!id_usuario.equals(pedidoVO.id_usuario)) return false;
        if (!total.equals(pedidoVO.total)) return false;
        if (!fecha_entrada.equals(pedidoVO.fecha_entrada)) return false;
        if (fecha_entrega != null ? !fecha_entrega.equals(pedidoVO.fecha_entrega) : pedidoVO.fecha_entrega != null)
            return false;
        if (!estado.equals(pedidoVO.estado)) return false;

        for (int i = 0; i < lineas.size(); i++) {
            if (!lineas.get(i).equals(((PedidoVO) o).lineas.get(i)))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + numero.hashCode();
        result = 31 * result + id_usuario.hashCode();
        result = 31 * result + total.hashCode();
        result = 31 * result + fecha_entrada.hashCode();
        result = 31 * result + (fecha_entrega != null ? fecha_entrega.hashCode() : 0);
        result = 31 * result + estado.hashCode();
        result = 31 * result + lineas.hashCode();
        return result;
    }
}
