package com.iesvi.gestionNotifica.domain.repo;

import com.iesvi.gestionNotifica.domain.NotificacionVO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificacionRepo extends MongoRepository<NotificacionVO,Integer> {

    List<NotificacionVO> findByType(String type);

}
