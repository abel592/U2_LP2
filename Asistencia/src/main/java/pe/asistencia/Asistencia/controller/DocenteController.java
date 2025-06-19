package pe.asistencia.Asistencia.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.asistencia.Asistencia.dtos.DocenteDTO;
import pe.asistencia.Asistencia.exception.CustomErrorResponse;
import pe.asistencia.Asistencia.mappers.DocenteMapper;
import pe.asistencia.Asistencia.model.Docente;
import pe.asistencia.Asistencia.service.IDocenteService;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/docente")
public class DocenteController {

    private final IDocenteService docenteService;
    private final DocenteMapper docenteMapper;

    @GetMapping
    public ResponseEntity<List<DocenteDTO>> findAll() {
        List<DocenteDTO> list = docenteMapper.toDTOs(docenteService.findAll());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> findById(@PathVariable("id") Long id) {
        Docente obj = docenteService.findById(id);
        return ResponseEntity.ok(docenteMapper.toDTO(obj));
    }

    @PostMapping
    public ResponseEntity<CustomErrorResponse> save(@Valid @RequestBody DocenteDTO dto) {
        Docente obj = docenteService.save(docenteMapper.toEntity(dto));
        return ResponseEntity.ok(new CustomErrorResponse(200, LocalDateTime.now(), (obj != null ? "true" : "false"), String.valueOf(obj.getIdDocente())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> update(@Valid @PathVariable("id") Long id, @RequestBody DocenteDTO dto) {
        dto.setIdDocente(id);
        Docente obj = docenteService.update(id, docenteMapper.toEntity(dto));
        return ResponseEntity.ok(docenteMapper.toDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomErrorResponse> delete(@PathVariable("id") Long id) {
        CustomErrorResponse operacion = docenteService.delete(id);
        return ResponseEntity.ok(operacion);
    }
}
