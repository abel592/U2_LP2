package pe.asistencia.Asistencia.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import pe.asistencia.Asistencia.model.Docente;

import pe.asistencia.Asistencia.repository.ICrudGenericoRepositorio;
import pe.asistencia.Asistencia.repository.IDocenteRepositorio;

import pe.asistencia.Asistencia.service.IDocenteService;

@Transactional
@Service

@RequiredArgsConstructor
public class DocenteServiceImp extends CrudGenericoServiceImp<Docente,Long>implements IDocenteService {
    private final IDocenteRepositorio docenteRepositorio;
    @Override
    protected ICrudGenericoRepositorio<Docente, Long> getRepo() {
        return docenteRepositorio;
    }
}