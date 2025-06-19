package pe.asistencia.Asistencia.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import pe.asistencia.Asistencia.model.EstudianteClase;
import pe.asistencia.Asistencia.repository.ICrudGenericoRepositorio;
import pe.asistencia.Asistencia.repository.IEstudianteClaseRepositorio;
import pe.asistencia.Asistencia.service.IEstudianteClaseService;

@Transactional
@Service

@RequiredArgsConstructor
public class EstudianteClaseServiceImp extends CrudGenericoServiceImp<EstudianteClase,Long>
        implements IEstudianteClaseService {
    private final IEstudianteClaseRepositorio estudianteClaseRepositorio;
    @Override
    protected ICrudGenericoRepositorio<EstudianteClase, Long> getRepo() {
        return estudianteClaseRepositorio;
    }
}