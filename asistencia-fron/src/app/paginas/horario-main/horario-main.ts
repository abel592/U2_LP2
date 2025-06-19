import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { MaterialModule } from '../../material/material.module';
import { MatTableDataSource } from '@angular/material/table';
import { Horarios } from '../../modelos/horario';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { HorarioService } from '../../servicio/horario.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-horario-main',
  standalone: true,
  imports: [RouterOutlet, MaterialModule, RouterLink],
  templateUrl: './horario-main.html',
  styleUrl: './horario-main.css'
})
export class HorarioComponentMain implements OnInit {

  dataSource!: MatTableDataSource<Horarios>;
  displayedColumns = [
    { def: 'idHorario', label: 'ID Horario', hide: false },
    { def: 'diaSemana', label: 'Día', hide: false },
    { def: 'horaInicio', label: 'Hora Inicio', hide: false },
    { def: 'horaFin', label: 'Hora Fin', hide: false },
    { def: 'accion', label: 'Acción', hide: false },
  ];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private horarioService: HorarioService,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.horarioService.findAll().subscribe((data) => {
      this.horarioService.setEntidadChange(data);
    });
    this.horarioService.getEntidadChange().subscribe((data) => {
      this.createTable(data);
    });
    this.horarioService
      .getMessageChange()
      .subscribe((data) =>
        this._snackBar.open(data, 'INFO', { duration: 2000 })
      );
  }

  createTable(horarios: Horarios[]) {
    this.dataSource = new MatTableDataSource(horarios);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  getDisplayedColumns() {
    return this.displayedColumns.filter((d) => !d.hide).map((d) => d.def);
  }

  applyFilter(filter: any) {
    this.dataSource.filter = filter.target.value.trim().toLowerCase();
  }

  delete(id: number) {
    this.horarioService
      .delete(id)
      .pipe(switchMap(() => this.horarioService.findAll()))
      .subscribe((res) => {
        this.horarioService.setEntidadChange(res);
        this.horarioService.setMessageChange('DELETED!');
      });
  }
}
