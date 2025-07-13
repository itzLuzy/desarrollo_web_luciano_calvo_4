package com.tarea4.tarea4.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {
    Foto findFirstById(Long Id);
    List<Foto> findByActividadId(Integer id_actividad);
}
