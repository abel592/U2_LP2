import { EstudianteService } from './../../../servicio/estudiante.service';
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
import { Estudiante } from '../../../modelos/Estudiante';
import { switchMap } from 'rxjs';
@Component({
  selector: 'app-form-estudiante',
  standalone: true,
  imports: [MaterialModule, FormsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './form-estudiante.html',
  styleUrl: './form-estudiante.css'
})
export class FormEstudiante implements OnInit{

  
  form!: FormGroup;
  isEdit: boolean = false;
  id!: number;

  constructor(
    private estudianteService: EstudianteService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      idEstudiante: new FormControl(null), // null por defecto, no 0

      nombres: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(120),
      ]),


      dni: new FormControl('', [
        Validators.required,
        Validators.pattern('^[0-9]{1,20}$'), // números, máximo 20 dígitos
      ]),

      apellidos: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(120),
      ]),

      

      nivel: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(20),
      ]),
    });

    this.route.params.subscribe((params) => {
      this.id = Number(params['id']);
      this.isEdit = !isNaN(this.id); // Si no es un número, no es edición
      if (this.isEdit) {
        this.initForm();
      }
    });
  }

  initForm() {
    if (this.isEdit) {
      this.estudianteService.findById(this.id).subscribe((data) => {
        this.form.setValue({
          idEstudiante: data.idEstudiante,
          nombres: data.nombres,
          apellidos: data.apellidos,
          dni: data.dni,
          nivel: data.nivel,
          
        });
      });
    }
  }

  operate() {
    const estudiante: Estudiante = new Estudiante(
      this.isEdit ? this.id : null, // solo enviar ID si es edición
      this.form.value['nombres'],
      this.form.value['apellidos'],
      this.form.value['dni'],
      this.form.value['nivel'],

    );

    if (this.isEdit) {
      this.estudianteService.update(this.id, estudiante).subscribe(() => {
        this.estudianteService.findAll().subscribe((data) => {
          this.estudianteService.setEntidadChange(data);
          this.estudianteService.setMessageChange('Actualizado correctamente');
          this.router.navigate(['pages/cliente']);
        });
      });
    } else {
      this.estudianteService
        .save(estudiante)
        .pipe(switchMap(() => this.estudianteService.findAll()))
        .subscribe((data) => {
          this.estudianteService.setEntidadChange(data);
          this.estudianteService.setMessageChange('Creado correctamente');
          this.router.navigate(['pages/cliente']);
        });
    }
  }

  get f() {
    return this.form.controls;
  }

}
