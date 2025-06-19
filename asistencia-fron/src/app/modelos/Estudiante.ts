export class Estudiante {

  constructor(
    public idEstudiante: number | null,
    public nombres: string,
    public apellidos: string,
    public dni: string,
    public nivel: string
  ) {}
}
