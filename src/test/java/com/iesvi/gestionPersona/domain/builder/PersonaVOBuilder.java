package com.iesvi.gestionPersona.domain.builder;

import com.iesvi.gestionPersona.domain.PersonaVO;
import com.iesvi.shared.domain.VOBuilder;
import io.beanmother.core.ObjectMother;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@AllArgsConstructor
@NoArgsConstructor
public class PersonaVOBuilder extends VOBuilder {

    String fullName;
    String login;
    String password;
    String email;
    String telefono;
    String rol;

    public PersonaVO build() {
        ObjectMother om = ObjectMother.getInstance();
        PersonaVO mother= om.bear("PersonaVO",PersonaVO.class);

        return new PersonaVO(
                fullName !=null ? fullName : mother.getFullName(),
                login !=null ? login : mother.getLogin(),
                password!=null ? password : mother.getPassword(),
                email!=null ? email : mother.getEmail(),
                telefono!=null ? telefono : mother.getTelefono(),
                rol!=null ? rol : "Admin"
        );
    }
}
