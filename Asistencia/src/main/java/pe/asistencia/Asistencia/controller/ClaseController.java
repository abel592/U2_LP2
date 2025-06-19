package pe.asistencia.Asistencia.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.asistencia.Asistencia.model.Clase;
import pe.asistencia.Asistencia.service.IClaseService;


import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clase")
public class ClaseController {
    private final IClaseService claseService;
    @GetMapping
    public ResponseEntity<List<Clase>> findAll() {
        List<Clase> list = claseService.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Clase> findById(@PathVariable("id") Long
                                                       id) {
        Clase obj = claseService.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Clase dto) {
        Clase obj = claseService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                obj.getIdClase()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Clase> update(@PathVariable("id") Long
                                                     id, @RequestBody
                                             Clase dto) {
        dto.setIdClase(id);
        Clase obj = claseService.update(id, dto);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        claseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}