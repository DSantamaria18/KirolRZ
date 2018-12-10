package com.qualit.kirolrz.storage.repository;

import com.qualit.kirolrz.storage.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Repository
@Transactional
public interface MedicosRepository extends JpaRepository<Medico, Long> {

    Collection<Medico> findAllByNombre(String nombre);

    Collection<Medico> findAllByNss(Long nss);
}
