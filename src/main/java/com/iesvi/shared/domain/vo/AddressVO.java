package com.iesvi.shared.domain.vo;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AddressVO {

    String direccion;
    String ciudad;
    String codigoPostal;
    String pais;

}
