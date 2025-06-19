package pe.asistencia.Asistencia.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class EstudianteDTO {

    private Long idEstudiante;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 120, message = "Los nombres no deben exceder 120 caracteres")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 120, message = "Los apellidos no deben exceder 120 caracteres")
    private String apellidos;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(max = 10, message = "El DNI no debe exceder 10 caracteres")
    @Pattern(regexp = "\\d{8,10}", message = "El DNI debe contener entre 8 y 10 dígitos")
    private String dni;

    @NotBlank(message = "El nivel es obligatorio")
    @Size(max = 20, message = "El nivel no debe exceder 20 caracteres")
    private String nivel;
}
