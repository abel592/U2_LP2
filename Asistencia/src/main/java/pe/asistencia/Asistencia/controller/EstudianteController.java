package pe.asistencia.Asistencia.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.asistencia.Asistencia.dtos.EstudianteDTO;
import pe.asistencia.Asistencia.exception.CustomErrorResponse;
import pe.asistencia.Asistencia.mappers.EstudianteMapper;
import pe.asistencia.Asistencia.model.Estudiante;
import pe.asistencia.Asistencia.service.IEstudianteService;


import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")

@RequestMapping("/estudiante")
public class EstudianteController {
    private final IEstudianteService estudianteService;
    private final EstudianteMapper estudianteMapper;



    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> findAll() {
        List<EstudianteDTO> list = estudianteMapper.toDTOs(estudianteService.findAll());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> findById(@PathVariable("id") Long id) {
        Estudiante obj = estudianteService.findById(id);
        return ResponseEntity.ok(estudianteMapper.toDTO(obj));
    }

    @PostMapping
    public ResponseEntity<CustomErrorResponse> save(@Valid @RequestBody EstudianteDTO dto) {
        Estudiante obj = estudianteService.save(estudianteMapper.toEntity(dto));
        return ResponseEntity.ok(new CustomErrorResponse(200, LocalDateTime.now(), (obj!=null?"true":"false"), String.valueOf(obj.getIdEstudiante())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> update(@Valid @PathVariable("id") Long id, @RequestBody EstudianteDTO dto) {
        dto.setIdEstudiante(id);
        Estudiante obj = estudianteService.update(id, estudianteMapper.toEntity(dto));
        return ResponseEntity.ok(estudianteMapper.toDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomErrorResponse> delete(@PathVariable("id") Long id) {
        CustomErrorResponse operacion= estudianteService.delete(id);
        return ResponseEntity.ok(operacion);
    }
}