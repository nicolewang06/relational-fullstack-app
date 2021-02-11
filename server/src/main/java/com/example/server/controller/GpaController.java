package com.example.server.controller;

import com.example.server.model.Gpa;
import com.example.server.service.GpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gpas")
public class GpaController {
    @Autowired
    GpaService gpaService;

    @GetMapping
    public Iterable<Gpa> getGpas() {
        return gpaService.getGpas();
    }

    @PostMapping("/{studentId}") // http://localhost8080/gpas/12345
    public Gpa createGpa(@PathVariable Long studentId, @RequestBody Gpa gpa) {
        return gpaService.createGpa(studentId, gpa);
    }

    @PatchMapping
    public Gpa updateGpa(@RequestBody Gpa gpa) {
        return gpaService.updateGpa(gpa);
    }

    @DeleteMapping
    public HttpStatus deleteGpa(@PathVariable Long id) {
        return gpaService.deleteGpa(id);
    }
}
