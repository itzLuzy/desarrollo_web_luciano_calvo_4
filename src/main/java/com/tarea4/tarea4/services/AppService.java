package com.tarea4.tarea4.services;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.tarea4.tarea4.models.*;

@Service
public class AppService {

    private final String pathStatic;
    private final ActividadRepository actividadRepository;
    private final ActividadTemaRepository actividadTemaRepository;
    private final NotaRepository notaRepository;

    public AppService(ActividadRepository actividadRepository, 
                    ActividadTemaRepository actividadTemaRepository,
                    NotaRepository notaRepository) throws IOException {
        this.actividadRepository = actividadRepository;
        this.actividadTemaRepository = actividadTemaRepository;
        this.notaRepository = notaRepository;
        // Dynamically resolve the absolute path for the static directory
        Path staticDir = Paths.get(ResourceUtils.getFile("classpath:static").getAbsolutePath());
        this.pathStatic = staticDir.toString();
        System.out.println("Static path resolved to: " + this.pathStatic);
    }

    public List<Map<String, String>> getActividadesData() {
        List<Actividad> actividades = actividadRepository.findByDiaHoraTerminoBefore(LocalDateTime.now());
        List<Map<String, String>> actividadesData = new ArrayList<>();
        
        for (Actividad act : actividades) {
            Map<String, String> actData = new HashMap<>();
            actData.put("id", act.getId().toString());
            actData.put("fecha_inicio", act.getInicio().toString());
            actData.put("sector", act.getSector());
            actData.put("nombre", act.getNombre());

            List<ActividadTema> temas = actividadTemaRepository.findByActividadId(act.getId());
            ActividadTema tema = null;
            if (temas!=null && temas.size() > 0) {
                tema = temas.getFirst();
                actData.put("tema", tema.getTema());
            }
            else {
                actData.put("tema", "-");
            }
            

            actividadesData.add(actData);
        }
        return actividadesData;
    }

    public void handlePostRequest(Long n, Long act_id) throws Exception {

        if (Nota.validateNota(n)) {
            // Save the nota in the database
            Actividad actividad = actividadRepository.findById(act_id).orElse(null);
            Nota nota = new Nota(actividad, n);
            notaRepository.save(nota);
            System.out.println("Nota saved successfully.");
        } else {
            throw new IllegalArgumentException("Nota validation failed.");
        }
    }
}
