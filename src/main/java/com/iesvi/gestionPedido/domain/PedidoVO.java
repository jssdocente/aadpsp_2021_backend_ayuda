package com.iesvi.gestionPedido.domain;

import javax.persistence.*;
import javax.sound.sampled.Line;
import java.io.Serializable;
import java.util.*;

import lombok.*;

@Entity(name = "Pedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"fecha_entrada"})
public class PedidoVO implements Serializable {

    public static String PENDIENTE="P";
    public static String DELIVERY="D";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //TODO: Tengo que calcular el numero siguiente a través
    @Column()
    private String numero="";

    //TODO:Quitar esto
//    @Column(length = 150, nullable = false)
//    @OneToMany
//    private List<ProductoVO> id_Productos;

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

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<LineaVO> lineas = new HashSet<>();

    public PedidoVO(Integer id_usuario, Set<LineaVO> lineas) {
        this.id_usuario = id_usuario;

        this.fecha_entrada = new Date();
        this.fecha_entrega = null;

        this.estado = PENDIENTE;

        //lineas.forEach((linea) -> addLinea(linea)); //AMBAS LINEAS SON IGUALES
        lineas.forEach(this::addLinea);

        calculateTotal();
    }

    public void changeToDelivered() {
        this.estado = DELIVERY;
    }

    public void addLinea(LineaVO linea) {
        if (lineas==null)
            lineas = new HashSet<>();

        lineas.add(linea);
    }

    //+++++ METODOS PRIVADOS ++++++
    void calculateTotal() {

        this.total=0d;
        lineas.forEach((linea) -> {
            linea.calculateTotals();
            this.total+=linea.total;
        });

    }
}
