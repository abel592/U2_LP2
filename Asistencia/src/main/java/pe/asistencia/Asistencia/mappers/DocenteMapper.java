package pe.asistencia.Asistencia.mappers;

import org.mapstruct.Mapper;
import pe.asistencia.Asistencia.dtos.DocenteDTO;
import pe.asistencia.Asistencia.model.Docente;

@Mapper(componentModel = "spring")
public interface DocenteMapper extends GenericMapper<DocenteDTO, Docente> {
}
