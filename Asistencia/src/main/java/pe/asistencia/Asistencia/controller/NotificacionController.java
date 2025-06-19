package pe.asistencia.Asistencia.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.asistencia.Asistencia.model.Notificacion;
import pe.asistencia.Asistencia.service.INotificacionService;


import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notificacion")
public class NotificacionController {
    private final INotificacionService notificacionService;
    @GetMapping
    public ResponseEntity<List<Notificacion>> findAll() {
        List<Notificacion> list = notificacionService.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> findById(@PathVariable("id") Long
                                                    id) {
        Notificacion obj = notificacionService.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Notificacion dto) {
        Notificacion obj = notificacionService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                obj.getIdNotificacion()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> update(@PathVariable("id") Long
                                                  id, @RequestBody
    Notificacion dto) {
        dto.setIdNotificacion(id);
        Notificacion obj = notificacionService.update(id, dto);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        notificacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}