package pe.asistencia.Asistencia.mappers;

import org.mapstruct.Mapper;
import pe.asistencia.Asistencia.dtos.EstudianteDTO;
import pe.asistencia.Asistencia.model.Estudiante;

@Mapper(componentModel = "spring")
public interface EstudianteMapper  extends GenericMapper<EstudianteDTO, Estudiante>{
}
