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
    private final FotoRepository fotoRepository;
    private final LogRepository logRepository;

    public AppService(ActividadRepository actividadRepository, 
                    ActividadTemaRepository actividadTemaRepository,
                    NotaRepository notaRepository,
                    FotoRepository fotoRepository,
                    LogRepository logRepository) throws IOException {
        this.actividadRepository = actividadRepository;
        this.actividadTemaRepository = actividadTemaRepository;
        this.notaRepository = notaRepository;
        this.fotoRepository = fotoRepository;
        this.logRepository = logRepository;
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


    public List<Map<String, String>> getAdminFotosData() {
        List<Foto> fotos = fotoRepository.findAll();
        List<Map<String, String>> actividadesData = new ArrayList<>();
        //System.out.println(fotos.size());
        for (Foto foto : fotos) {
            Map<String, String> actData = new HashMap<>();
            actData.put("id", foto.getId().toString());
            actData.put("ruta_foto", foto.getRuta());
            actData.put("nombre_foto", foto.getNombre());

            Actividad actividad = foto.getActividad();
            actData.put("email", actividad.getEmail());
            actData.put("nombre_act", actividad.getNombre());
            actividadesData.add(actData);
        }
        return actividadesData;
    }

    public List<Map<String, String>> getLogData() {
        List<Log> logs = logRepository.findAllByOrderByFechaDesc();
        List<Map<String, String>> data = new ArrayList<>();
        for (Log log : logs) {
            Map<String, String> actData = new HashMap<>();
            actData.put("id", log.getId().toString());
            actData.put("fecha", log.getFecha().toString());
            actData.put("mensaje", log.getMensaje());
            data.add(actData);
        }
        return data;
    }

    public void handlePostRequest(Long n, Integer act_id) throws Exception {

        if (Nota.validateNota(n)) {
            // Save the nota in the database
            Actividad actividad = actividadRepository.findById(act_id).orElse(null);
            Nota nota = new Nota(actividad, n);
            notaRepository.save(nota);
            System.out.println("Nota saved successfully.");
        } 
        else {
            throw new IllegalArgumentException("Nota validation failed.");
        }
    }

    public void handleLogPostRequest(String s, Long foto_id) throws Exception {
        Foto foto = fotoRepository.findFirstById(foto_id);
        if (Log.validateLog(s) && foto!=null) {
            String fullMsg = "eliminando foto " + foto_id.toString() + " por usuario admin, motivo: " + s;
            fotoRepository.delete(foto);
            Log log = new Log(LocalDateTime.now(), fullMsg);
            logRepository.save(log);
            System.out.println("Log saved successfully.");
        }
        else {
            throw new IllegalArgumentException("Foto elimination failed.");
        }
        
    }
}
