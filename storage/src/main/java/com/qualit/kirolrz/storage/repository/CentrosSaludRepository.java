package com.qualit.kirolrz.storage.repository;

import com.qualit.kirolrz.storage.entity.CentroSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CentrosSaludRepository extends JpaRepository<CentroSalud, Long> {
}
