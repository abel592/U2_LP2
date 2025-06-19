import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { MaterialModule } from '../../material/material.module';
import { MatTableDataSource } from '@angular/material/table';
import { Estudiante } from '../../modelos/Estudiante';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { EstudianteService } from '../../servicio/estudiante.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-estudiante-main',
  standalone: true,
  imports: [RouterOutlet, MaterialModule, RouterLink],
  templateUrl: './estudiante-main.html',
  styleUrl: './estudiante-main.css'
})
export class EstudianteComponentMain implements OnInit {
  
  dataSource!: MatTableDataSource<Estudiante>;
  displayedColumns = [
    { def: 'idEstudiante', label: 'idCliente', hide: false },
    { def: 'nombres', label: 'Nombre', hide: false },
    { def: 'apellidos', label: 'Tipo cliente', hide: false },
    { def: 'dni', label: 'DNI', hide: false },
    { def: 'nivel', label: 'Telefono', hide: false },
    { def: 'accion', label: 'accion', hide: false },
  ];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(
    private estudianteService: EstudianteService,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.estudianteService.findAll().subscribe((data) => {
      this.estudianteService.setEntidadChange(data);
    });
    this.estudianteService.getEntidadChange().subscribe((data) => {
      this.createTable(data);
    });
    this.estudianteService
      .getMessageChange()
      .subscribe((data) =>
        this._snackBar.open(data, 'INFO', { duration: 2000 })
      );
  }

  createTable(estudiante: Estudiante[]) {
    this.dataSource = new MatTableDataSource(estudiante);
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
    this.estudianteService
      .delete(id)
      .pipe(switchMap(() => this.estudianteService.findAll()))
      .subscribe((res) => {
        this.estudianteService.setEntidadChange(res);
        this.estudianteService.setMessageChange('DELETED!');
      });
  }

}
