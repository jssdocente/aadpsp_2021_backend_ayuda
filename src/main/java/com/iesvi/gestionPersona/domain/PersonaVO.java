package com.iesvi.gestionPersona.domain;

import com.iesvi.gestionPedido.domain.err.PedidoErr;
import com.iesvi.gestionPersona.domain.err.err.PersonaErr;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"friends","friendOf"})
@Entity(name = "Persona")
public class PersonaVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id=1;

    @Column(unique = true, nullable = false)
    String fullName;

    @Column(unique = true, nullable = false)
    String login;

    @Column(length = 30, nullable = false)
    String password;

    @Column(length = 50, nullable = false)
    String email;

    @Column(length = 50)
    String telefono;

    @Column(length = 150, nullable = false)
    String rol;

    @ManyToMany
    @JoinTable(name="Amigo",joinColumns = @JoinColumn(name="usuarioId"),inverseJoinColumns = @JoinColumn(name="amigoId"))
    private List<PersonaVO> friends = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="Amigo",joinColumns = @JoinColumn(name="amigoId"),inverseJoinColumns = @JoinColumn(name="usuarioId"))
    private List<PersonaVO> friendOf = new ArrayList<>();

    public PersonaVO(String fullName, String login, String password, String email, String telefono, String rol) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
    }

    public void addAmigo(PersonaVO amigo) {
        if (friends==null)
            friends = new ArrayList<>();

        friends.add(amigo); //Usuario agrega a su amigo
        //amigo.addAmigoDe(this); //Amigo agrega al usuario como amigo tambien
    }

    public void removeAmigo(PersonaVO amigo) {
        if (friends==null)
            throw new PersonaErr("PER.REM.FRIEND.NULL","REMOVE FRIEND PARAM IS NULL");

        if (!friends.contains(amigo))
            return;

        friends.remove(amigo); //Usuario agrega a su amigo
        //amigo.addAmigoDe(this); //Amigo agrega al usuario como amigo tambien
    }

}