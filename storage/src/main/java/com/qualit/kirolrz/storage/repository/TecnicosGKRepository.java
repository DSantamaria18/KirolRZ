package com.qualit.kirolrz.storage.repository;

import com.qualit.kirolrz.storage.entity.TecnicoGK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TecnicosGKRepository extends JpaRepository<TecnicoGK, Long> {

        List<TecnicoGK> findAllByNombre(String nombre);

        List<TecnicoGK> findAllByDni(String dni);
}
