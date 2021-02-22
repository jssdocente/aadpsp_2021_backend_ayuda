package com.iesvi.gestionUsuario.domain.repos;

import com.iesvi.gestionUsuario.domain.AdministradorVO;
import com.iesvi.shared.domain.repos.GenericRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//public interface AdminRepo extends GenericRepo<AdministradorVO, Integer> {
//}

@Repository
public interface AdminRepo extends JpaRepository<AdministradorVO,Integer> {

}