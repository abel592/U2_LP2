package pe.asistencia.Asistencia.mappers;

import org.mapstruct.Mapper;
import pe.asistencia.Asistencia.dtos.HorarioDTO;
import pe.asistencia.Asistencia.model.Horario;

@Mapper(componentModel = "spring")

public interface HorariosMapper extends GenericMapper<HorarioDTO, Horario>{
}
