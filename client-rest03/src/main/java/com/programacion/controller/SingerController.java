package com.programacion.controller;

import com.programacion.models.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class SingerController {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://localhost:8080/rest02/singer";

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping({"/singers", "/"})
    public String index(Model model) {
        ResponseEntity<Singer[]> res = restTemplate.getForEntity(URL, Singer[].class);
        model.addAttribute("singers", res.getBody());
        return "singers";
    }

    @GetMapping("/singer/nuevo")
    public String agregar(Model model) {
        Singer singer = new Singer();
        model.addAttribute("singer", singer);
        return "agregar_singer";
    }

    @PostMapping("/singers")
    public String saveSinger(@ModelAttribute("singer") Singer singer) {
        restTemplate.postForEntity(URL, singer, Singer.class);
        return "redirect:/singers";
    }

    @GetMapping("/singer/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        restTemplate.delete(URL + "/" + id);
        return "redirect:/singers";
    }

    @GetMapping("/singer/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        ResponseEntity<Singer> res = restTemplate.getForEntity(URL + "/" + id, Singer.class);
        model.addAttribute("singer", res.getBody());
        return "editar_singer";
    }

    @PostMapping("/singer/editar/{id}")
    public String updateSinger(@PathVariable("id") Integer id, @ModelAttribute("singer") Singer singer) {
        restTemplate.put(URL + "/" + id, singer, Singer.class);
        return "redirect:/singers";
    }

}