package pe.asistencia.Asistencia.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.asistencia.Asistencia.model.Docente.Rol;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocenteDTO {

    private Long idDocente;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 120, message = "Los nombres no deben exceder 120 caracteres")
    private String nombres;

    @Email(message = "El correo debe ser válido")
    @Size(max = 100, message = "El correo no debe exceder 100 caracteres")
    private String correo;

    @NotBlank(message = "El nivel es obligatorio")
    @Size(max = 20, message = "El nivel no debe exceder 20 caracteres")
    private String nivel;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 50, message = "El nombre de usuario no debe exceder 50 caracteres")
    private String usuario;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 255, message = "La contraseña debe tener entre 6 y 255 caracteres")
    private String contrasena;

    @NotNull(message = "El rol es obligatorio (ADMIN o DOCENTE)")
    private Rol rol;
}
