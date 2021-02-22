package com.iesvi.gestionNotifica.domain.builder;

import com.iesvi.gestionNotifica.domain.NotificaEnum;
import com.iesvi.gestionNotifica.domain.NotificacionVO;
import com.iesvi.gestionUsuario.domain.UsuarioVO;
import com.iesvi.shared.domain.VOBuilder;
import io.beanmother.core.ObjectMother;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

//TODO:Lombok builder annotations
@AllArgsConstructor
@NoArgsConstructor
@With
//@Builder
public class NotificaVOBuilder extends VOBuilder {

    Integer id;
    String userId;
    String message;
    String type;
    java.util.Date timestamp = new Date();

    public NotificacionVO build() {
        ObjectMother om = ObjectMother.getInstance();
        NotificacionVO mother= om.bear("NotificacionVO",NotificacionVO.class);

        return new NotificacionVO(
                id!=null ? id : mother.getId(),
                userId!=null ? userId : mother.getUserId(),
                message!=null ? message : mother.getMessage(),
                type!=null ? type : randomType(),
                timestamp
        );
    }

    private String randomType() {
        Random r = new Random();
        return Arrays.stream(NotificaEnum.values()).findAny().get().toString();
    }
}
