package com.programacion.controller;

import com.programacion.models.Album;
import com.programacion.models.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;

@Controller
public class AlbumController {

    @Autowired
    private RestTemplate restTemplate;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private final String URL = "http://localhost:8080/rest02/album";

    @GetMapping("/albums")
    public String index(Model model) {
        ResponseEntity<Album[]> res = restTemplate.getForEntity(URL, Album[].class);
        model.addAttribute("albums", res.getBody());
        return "albums";
    }

    @RequestMapping("/album/nuevo")
    public String agregar(Model model) {
        Album album = new Album();
        model.addAttribute("album", album);
        ResponseEntity<Singer[]> res = restTemplate.getForEntity("http://localhost:8080/rest02/singer", Singer[].class);
        model.addAttribute("singers", res.getBody());
        return "agregar_album";
    }

    @PostMapping("/albums")
    public String saveAlbum(@ModelAttribute("album") Album album) {
        restTemplate.postForEntity(URL, album, Album.class);
        return "redirect:/albums";
    }

    @GetMapping("/album/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        restTemplate.delete(URL + "/" + id);
        return "redirect:/albums";
    }

    @GetMapping("/album/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        ResponseEntity<Album> res = restTemplate.getForEntity(URL + "/" + id, Album.class);
        model.addAttribute("album", res.getBody());
        ResponseEntity<Singer[]> res2 = restTemplate.getForEntity("http://localhost:8080/rest02/singer", Singer[].class);
        model.addAttribute("singers", res2.getBody());
        return "editar_album";
    }

    @PostMapping("/album/editar/{id}")
    public String updateAlbum(@PathVariable("id") Integer id, @ModelAttribute("album") Album album) {
        restTemplate.put(URL + "/" + id, album, Album.class);
        return "redirect:/albums";
    }
}
