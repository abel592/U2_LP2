package pe.asistencia.Asistencia.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.asistencia.Asistencia.model.ReporteAsistencia;
import pe.asistencia.Asistencia.service.IReporteAsistenciaService;


import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reporteAsistencia")
public class ReporteAsistenciaController {
    private final IReporteAsistenciaService reporteAsistenciaService;
    @GetMapping
    public ResponseEntity<List<ReporteAsistencia>> findAll() {
        List<ReporteAsistencia> list = reporteAsistenciaService.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReporteAsistencia> findById(@PathVariable("id") Long
                                                         id) {
        ReporteAsistencia obj = reporteAsistenciaService.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ReporteAsistencia dto) {
        ReporteAsistencia obj = reporteAsistenciaService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                obj.getIdReporte()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReporteAsistencia> update(@PathVariable("id") Long
                                                       id, @RequestBody
    ReporteAsistencia dto) {
        dto.setIdReporte(id);
        ReporteAsistencia obj = reporteAsistenciaService.update(id, dto);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        reporteAsistenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}