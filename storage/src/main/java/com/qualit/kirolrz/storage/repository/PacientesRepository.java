package com.qualit.kirolrz.storage.repository;

import com.qualit.kirolrz.storage.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PacientesRepository extends JpaRepository<Paciente, Long> {

    List<Paciente> findAllByNombre(String nombre);

    List<Paciente> findAllByApellidos(String apellidos);

    List<Paciente> findAllByDni(String dni);

    List<Paciente> findAllByEmail(String email);
}