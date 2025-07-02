package com.tarea4.tarea4.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.tarea4.tarea4.models.*;

@Service
public class ApiService {
    private final NotaRepository notaRepository;
    public ApiService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    public Double getNotaPromedio(Long id) {
        List<Nota> notas = notaRepository.findByActividadId(id);
        if (notas.isEmpty()) {
            return null;
        }
        Long sum = 0L;
        for (Nota nota : notas) {
            sum += nota.getNota();
        }
        return sum / (double) notas.size();
    }
    
}
