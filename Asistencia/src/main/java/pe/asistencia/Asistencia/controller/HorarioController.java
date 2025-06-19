package pe.asistencia.Asistencia.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.asistencia.Asistencia.model.Horario;
import pe.asistencia.Asistencia.service.IHorarioService;


import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/horario")
public class HorarioController {
    private final IHorarioService horarioService;
    @GetMapping
    public ResponseEntity<List<Horario>> findAll() {
        List<Horario> list = horarioService.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Horario> findById(@PathVariable("id") Long
                                                    id) {
        Horario obj = horarioService.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Horario dto) {
        Horario obj = horarioService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                obj.getIdHorario()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Horario> update(@PathVariable("id") Long
                                                  id, @RequestBody
                                          Horario dto) {
        dto.setIdHorario(id);
        Horario obj = horarioService.update(id, dto);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        horarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}