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
import { HorarioService } from '../../../servicio/horario.service';
import { Horarios } from '../../../modelos/horario';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-form-horario',
  standalone: true,
  imports: [MaterialModule, FormsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './form-horario.html',
  styleUrl: './form-horario.css'
})
export class FormHorario implements OnInit {

  form!: FormGroup;
  isEdit: boolean = false;
  id!: number;

  constructor(
    private horarioService: HorarioService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      idHorario: new FormControl(null),
      diaSemana: new FormControl('', [
        Validators.required,
        Validators.maxLength(20)
      ]),
      horaInicio: new FormControl('', Validators.required),
      horaFin: new FormControl('', Validators.required),
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
    this.horarioService.findById(this.id).subscribe((data) => {
      this.form.setValue({
        idHorario: data.idHorario,
        diaSemana: data.diaSemana,
        horaInicio: data.horaInicio,
        horaFin: data.horaFin
      });
    });
  }

  operate() {
    const horario: Horarios = new Horarios(
      this.isEdit ? this.id : null,
      this.form.value['diaSemana'],
      this.form.value['horaInicio'],
      this.form.value['horaFin']
    );

    if (this.isEdit) {
      this.horarioService.update(this.id, horario).subscribe(() => {
        this.horarioService.findAll().subscribe((data) => {
          this.horarioService.setEntidadChange(data);
          this.horarioService.setMessageChange('Actualizado correctamente');
          this.router.navigate(['pages/horario']);
        });
      });
    } else {
      this.horarioService
        .save(horario)
        .pipe(switchMap(() => this.horarioService.findAll()))
        .subscribe((data) => {
          this.horarioService.setEntidadChange(data);
          this.horarioService.setMessageChange('Creado correctamente');
          this.router.navigate(['pages/horario']);
        });
    }
  }

  get f() {
    return this.form.controls;
  }
}
