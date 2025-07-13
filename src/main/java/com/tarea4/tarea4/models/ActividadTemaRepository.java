package com.tarea4.tarea4.models;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadTemaRepository extends JpaRepository<ActividadTema, Long> {
    Page<ActividadTema> findAllByOrderByIdDesc(Pageable pageable);
    List<ActividadTema> findByActividadId(Integer actividad_id);
}
