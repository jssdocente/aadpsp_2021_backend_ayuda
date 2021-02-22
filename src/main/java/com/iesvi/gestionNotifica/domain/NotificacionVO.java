package com.iesvi.gestionNotifica.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Document(collection="Notificaciones")
public class NotificacionVO {

    @Id
    private Integer id;

    private String userId;
    private String message;
    private String type;
    private java.util.Date timestamp;

    public NotificacionVO(Integer id, String userId, String message, String type, Date timestamp) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.type = type;
        this.timestamp = timestamp;
    }
}