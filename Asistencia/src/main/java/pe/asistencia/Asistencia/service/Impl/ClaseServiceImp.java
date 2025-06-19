package pe.asistencia.Asistencia.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import pe.asistencia.Asistencia.model.Clase;

import pe.asistencia.Asistencia.repository.IClaseRepositorio;
import pe.asistencia.Asistencia.repository.ICrudGenericoRepositorio;

import pe.asistencia.Asistencia.service.IClaseService;

@Transactional
@Service

@RequiredArgsConstructor
public class ClaseServiceImp extends CrudGenericoServiceImp<Clase,Long>implements IClaseService {
    private final IClaseRepositorio claseRepositorio;
    @Override
    protected ICrudGenericoRepositorio<Clase, Long> getRepo() {
        return claseRepositorio;
    }
}