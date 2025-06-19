package pe.asistencia.Asistencia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "col_asistencia")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Long idAsistencia;

    @ManyToOne
    @JoinColumn(name = "id_est_clase", nullable = false, foreignKey = @ForeignKey(name = "FK_ESTCLASE_ASISTENCIA"))
    private EstudianteClase estudianteClase;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "estado", nullable = false, length = 10)
    private String estado;

    @Column(name = "justificacion", length = 255)
    private String justificacion;





}