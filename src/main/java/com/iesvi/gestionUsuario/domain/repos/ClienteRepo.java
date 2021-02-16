package com.iesvi.gestionUsuario.domain.repos;

import com.iesvi.gestionUsuario.domain.ClienteVO;
import com.iesvi.shared.domain.repos.GenericRepo;


public interface ClienteRepo extends GenericRepo<ClienteVO, Integer> {

    /*List<ClienteDTO> findByAutor(String autor);
    List<Noticia> findByTitulo(String titulo);
    List<Noticia> findByTituloAndAutor(String titulo,String autor);

    @Query("select n from Noticia n where n.fecha between ?1 and ?2")
    List<Noticia> findByDateInterval(Date fecha1, Date fecha2);*/
}