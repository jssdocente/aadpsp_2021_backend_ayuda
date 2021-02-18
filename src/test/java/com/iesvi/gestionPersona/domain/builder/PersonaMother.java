package com.iesvi.gestionPersona.domain.builder;


import com.iesvi.gestionPersona.domain.PersonaVO;

public class PersonaMother {

    public static PersonaVO juan() {
        return new PersonaVOBuilder()
                .withFullName("Juan Fernadez")
                .withLogin("Juanin")
                .build();
    }

    public static PersonaVO manuel() {
        return new PersonaVOBuilder()
                .withFullName("Manuel Garcia")
                .withLogin("machu")
                .build();
    }

    public static PersonaVO jorge() {
        return new PersonaVOBuilder()
                .withFullName("Jorge Vigas")
                .withLogin("jorgito")
                .build();
    }
}