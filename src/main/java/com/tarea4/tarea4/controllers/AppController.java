package com.tarea4.tarea4.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarea4.tarea4.services.AppService;

@Controller
public class AppController {
    private final AppService appService;
    public AppController(AppService appService) {
        this.appService = appService;
    }
    
    @GetMapping("/")
    public String indexRoute(Model model) {
        List<Map<String, String>> modelData = appService.getActividadesData();
        model.addAttribute("data", modelData);
        return "index";
    }

    @GetMapping("/admin-fotos")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminFotosRoute(Model model) {
        List<Map<String, String>> modelData = appService.getAdminFotosData();
        model.addAttribute("data", modelData);
        return "admin_fotos";
    }

    @GetMapping("/log")
    @PreAuthorize("hasRole('ADMIN')")
    public String logRoute(Model model) {
        List<Map<String, String>> modelData = appService.getLogData();
        model.addAttribute("data", modelData);
        return "log";
    }

    @PostMapping("/post-log/{foto-id}")
    @ResponseBody
    public Map<String, Object> postLogRoute(
        @PathVariable("foto-id") Long foto_id,
        @RequestParam("log") String log) {
        Map<String, Object> res = new HashMap<>();
        try {
            appService.handleLogPostRequest(log, foto_id);
            res.put("ok", true);
        } 
        catch (Exception e) {
            res.put("ok", false);
        }
        return res;
    }

    
    @PostMapping("/post-nota/{act-id}")
    @ResponseBody
    public Map<String, Object> postNotaRoute(
        @PathVariable("act-id") Integer act_id,
        @RequestParam("nota") String nota) {
        Map<String, Object> res = new HashMap<>();
        try {
            appService.handlePostRequest(Long.parseLong(nota), act_id);
            res.put("ok", true);
        } 
        catch (Exception e) {
            res.put("ok", false);
        }
        return res;
    }

}
