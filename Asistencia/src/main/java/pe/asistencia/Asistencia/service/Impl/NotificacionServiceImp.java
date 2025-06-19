package pe.asistencia.Asistencia.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.asistencia.Asistencia.model.Asistencia;
import pe.asistencia.Asistencia.model.Notificacion;
import pe.asistencia.Asistencia.repository.IAsitenciaRepositorio;
import pe.asistencia.Asistencia.repository.ICrudGenericoRepositorio;
import pe.asistencia.Asistencia.repository.INotificacionRepositorio;
import pe.asistencia.Asistencia.service.IAsistenciaService;
import pe.asistencia.Asistencia.service.INotificacionService;

@Transactional
@Service

@RequiredArgsConstructor
public class NotificacionServiceImp extends CrudGenericoServiceImp<Notificacion,Long>
        implements INotificacionService {
    private final INotificacionRepositorio notificacionRepositorio;
    @Override
    protected ICrudGenericoRepositorio<Notificacion, Long> getRepo() {
        return notificacionRepositorio;
    }
}