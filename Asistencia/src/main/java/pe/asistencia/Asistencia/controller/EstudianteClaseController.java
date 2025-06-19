package pe.asistencia.Asistencia.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.asistencia.Asistencia.model.EstudianteClase;
import pe.asistencia.Asistencia.service.IEstudianteClaseService;
import pe.asistencia.Asistencia.service.IEstudianteService;


import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estudianteClase")
public class EstudianteClaseController {
    private final IEstudianteClaseService estudianteClaseService;
    @GetMapping
    public ResponseEntity<List<EstudianteClase>> findAll() {
        List<EstudianteClase> list = estudianteClaseService.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteClase> findById(@PathVariable("id") Long
                                                    id) {
        EstudianteClase obj = estudianteClaseService.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody EstudianteClase dto) {
        EstudianteClase obj = estudianteClaseService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                obj.getIdEstClase()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteClase> update(@PathVariable("id") Long
                                                  id, @RequestBody
    EstudianteClase dto) {
        dto.setIdEstClase(id);
        EstudianteClase obj = estudianteClaseService.update(id, dto);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        estudianteClaseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}