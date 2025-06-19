import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../../material/material.module';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { DocenteService } from '../../../servicio/docente.service';
import { Docente } from '../../../modelos/Docentes';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-form-docente',
  standalone: true,
  imports: [MaterialModule, FormsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './form-docente.html',
  styleUrl: './form-docente.css'
})
export class FormDocente implements OnInit {

  form!: FormGroup;
  isEdit: boolean = false;
  id!: number;

  constructor(
    private docenteService: DocenteService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      idDocente: new FormControl(null),
      nombres: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(120),
      ]),
      correo: new FormControl('', [
        Validators.required,
        Validators.email,
      ]),
      nivel: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(20),
      ]),
      usuario: new FormControl('', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50),
      ]),
      contrasena: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
      ]),
      rol: new FormControl('', [
        Validators.required
      ]),
    });

    this.route.params.subscribe((params) => {
      this.id = Number(params['id']);
      this.isEdit = !isNaN(this.id);
      if (this.isEdit) {
        this.initForm();
      }
    });
  }

  initForm() {
    this.docenteService.findById(this.id).subscribe((data) => {
      this.form.setValue({
        idDocente: data.idDocente,
        nombres: data.nombres,
        correo: data.correo,
        nivel: data.nivel,
        usuario: data.usuario,
        contrasena: data.contrasena,
        rol: data.rol
      });
    });
  }

  operate() {
    const docente: Docente = new Docente(
      this.isEdit ? this.id : null,
      this.form.value['nombres'],
      this.form.value['correo'],
      this.form.value['nivel'],
      this.form.value['usuario'],
      this.form.value['contrasena'],
      this.form.value['rol']
    );

    if (this.isEdit) {
      this.docenteService.update(this.id, docente).subscribe(() => {
        this.docenteService.findAll().subscribe((data) => {
          this.docenteService.setEntidadChange(data);
          this.docenteService.setMessageChange('Actualizado correctamente');
          this.router.navigate(['pages/docente']);
        });
      });
    } else {
      this.docenteService
        .save(docente)
        .pipe(switchMap(() => this.docenteService.findAll()))
        .subscribe((data) => {
          this.docenteService.setEntidadChange(data);
          this.docenteService.setMessageChange('Creado correctamente');
          this.router.navigate(['pages/docente']);
        });
    }
  }

  get f() {
    return this.form.controls;
  }
}
