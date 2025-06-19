package pe.asistencia.Asistencia.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.asistencia.Asistencia.model.Horario;
import pe.asistencia.Asistencia.repository.ICrudGenericoRepositorio;
import pe.asistencia.Asistencia.repository.IHorarioRepositorio;
import pe.asistencia.Asistencia.service.IHorarioService;

@Transactional
@Service

@RequiredArgsConstructor
public class HorarioServiceImp extends CrudGenericoServiceImp<Horario,Long>implements IHorarioService {
    private final IHorarioRepositorio horarioRepositorio;
    @Override
    protected ICrudGenericoRepositorio<Horario, Long> getRepo() {
        return horarioRepositorio;
    }
}