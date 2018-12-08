package com.qualit.kirolrz.storage.repository;

import com.qualit.kirolrz.storage.entity.CentroDeportivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CentrosDeportivosRepository extends JpaRepository<CentroDeportivo, Long> {

    List<CentroDeportivo> findAllByNombre(String nombre);
}
