package pe.asistencia.Asistencia.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.asistencia.Asistencia.model.Estudiante;
import pe.asistencia.Asistencia.repository.ICrudGenericoRepositorio;
import pe.asistencia.Asistencia.repository.IEstudianteRepositorio;
import pe.asistencia.Asistencia.service.IEstudianteService;

@Transactional
@Service

@RequiredArgsConstructor
public class EstudianteServiceImp extends CrudGenericoServiceImp<Estudiante,Long>implements IEstudianteService {
    private final IEstudianteRepositorio estudianteRepositorio;
    @Override
    protected ICrudGenericoRepositorio<Estudiante, Long> getRepo() {
        return estudianteRepositorio;
    }
}