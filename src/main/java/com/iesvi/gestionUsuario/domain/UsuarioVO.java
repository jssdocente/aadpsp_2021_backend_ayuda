package com.iesvi.gestionUsuario.domain;

import com.iesvi.shared.domain.audit.AuditableEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @With @Builder
@Entity(name = "Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioVO extends AuditableEntity implements UserDetails, Serializable {

    private static final long serialVersionUID = -7863998719466555120L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Builder.Default
    Integer id=1;

    @Column(unique = true, nullable = false)
    String nombreUsuario;

    @Column(length = 500, nullable = false)
    String password;

    @Column(length = 150, nullable = false)
    String nombre;

    @ElementCollection(fetch = FetchType.EAGER)  //Indica una colección de tipos-simples, no de entidades.
    @Enumerated(EnumType.STRING)
    Set<UserRole> roles;

    public UsuarioVO(String nombre, String NombreUsuario, String password) {
        this.nombreUsuario = NombreUsuario;
        this.password = password;
        this.nombre = nombre;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.name()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UsuarioVO usuarioVO = (UsuarioVO) o;

        if (!id.equals(usuarioVO.id)) return false;
        if (!nombreUsuario.equals(usuarioVO.nombreUsuario)) return false;
        if (!password.equals(usuarioVO.password)) return false;
        if (!nombre.equals(usuarioVO.nombre)) return false;
        return roles.equals(usuarioVO.roles);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + nombreUsuario.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + nombre.hashCode();
        result = 31 * result + roles.hashCode();
        return result;
    }

    @Override
    public String getUsername() {
        return this.getNombre();
    }

    //No gestionamos expiraciones de cuenta
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //No gestionamos cuentas bloqueadas
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //No gestionamos credenciales expiradas
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}