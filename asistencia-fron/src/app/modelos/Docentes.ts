export class Docente {
  constructor(
    public idDocente: number | null,
    public nombres: string,
    public correo: string,
    public nivel: string,
    public usuario: string,
    public contrasena: string,
    public rol: string
  ) {}
}