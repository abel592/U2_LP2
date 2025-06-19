package pe.asistencia.Asistencia.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.asistencia.Asistencia.model.Asistencia;
import pe.asistencia.Asistencia.repository.IAsitenciaRepositorio;
import pe.asistencia.Asistencia.repository.ICrudGenericoRepositorio;
import pe.asistencia.Asistencia.service.IAsistenciaService;
@Transactional
@Service

@RequiredArgsConstructor
public class AsistenciaServiceImp extends CrudGenericoServiceImp<Asistencia,Long>implements IAsistenciaService {
    private final IAsitenciaRepositorio asitenciaRepositorio;
    @Override
    protected ICrudGenericoRepositorio<Asistencia, Long> getRepo() {
        return asitenciaRepositorio;
    }
}
