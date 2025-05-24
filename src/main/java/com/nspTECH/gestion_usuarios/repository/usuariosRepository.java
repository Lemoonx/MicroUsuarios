package com.nspTECH.gestion_usuarios.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nspTECH.gestion_usuarios.model.usuario;

public interface usuariosRepository extends JpaRepository<usuario, Long>{

}
