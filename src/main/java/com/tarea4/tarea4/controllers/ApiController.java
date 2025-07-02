package com.tarea4.tarea4.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.tarea4.tarea4.services.ApiService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ApiController {
    private final ApiService apiService;
    public ApiController(ApiService apiService) {
        this.apiService = apiService;

    }
    
    @GetMapping("/get-avg/{act-id}")
    public String getAvgEndpoint(@PathVariable("act-id") Long actId) {
        Double promedio = apiService.getNotaPromedio(actId);
        if (promedio == null) return "-";
        return String.format("%.1f", promedio);
    }
}
