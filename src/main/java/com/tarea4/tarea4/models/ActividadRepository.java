package com.tarea4.tarea4.models;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Integer> {
    Page<Actividad> findAllByOrderByIdDesc(Pageable pageable);
    Actividad findFirstById(Integer id);
    List<Actividad> findByDiaHoraTerminoBefore(LocalDateTime fecha);
}
