export class Horarios {
  constructor(
    public idHorario: number | null,
    public diaSemana: string,
    public horaInicio: string,
    public horaFin: string
  ) {}
}
