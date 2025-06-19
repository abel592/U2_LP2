package pe.asistencia.Asistencia.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.asistencia.Asistencia.model.Asistencia;
import pe.asistencia.Asistencia.service.IAsistenciaService;


import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/asistencia")
public class AsistenciaController {
    private final IAsistenciaService asistenciaService;
    @GetMapping
    public ResponseEntity<List<Asistencia>> findAll() {
        List<Asistencia> list = asistenciaService.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> findById(@PathVariable("id") Long
                                                      id) {
        Asistencia obj = asistenciaService.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Asistencia dto) {
        Asistencia obj = asistenciaService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                obj.getIdAsistencia()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> update(@PathVariable("id") Long
                                                    id, @RequestBody
                                            Asistencia dto) {
        dto.setIdAsistencia(id);
        Asistencia obj = asistenciaService.update(id, dto);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        asistenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}