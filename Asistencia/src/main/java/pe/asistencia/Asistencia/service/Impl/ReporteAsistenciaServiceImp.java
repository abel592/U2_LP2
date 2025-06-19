package pe.asistencia.Asistencia.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.asistencia.Asistencia.model.Asistencia;
import pe.asistencia.Asistencia.model.ReporteAsistencia;
import pe.asistencia.Asistencia.repository.IAsitenciaRepositorio;
import pe.asistencia.Asistencia.repository.ICrudGenericoRepositorio;
import pe.asistencia.Asistencia.repository.IReporteAsistencia;
import pe.asistencia.Asistencia.service.IAsistenciaService;
import pe.asistencia.Asistencia.service.IReporteAsistenciaService;

@Transactional
@Service

@RequiredArgsConstructor
public class ReporteAsistenciaServiceImp extends CrudGenericoServiceImp<ReporteAsistencia,Long>
        implements IReporteAsistenciaService {
    private final IReporteAsistencia reporteAsistencia;
    @Override
    protected ICrudGenericoRepositorio<ReporteAsistencia, Long> getRepo() {
        return reporteAsistencia;
    }
}