import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { MaterialModule } from '../../material/material.module';
import { MatTableDataSource } from '@angular/material/table';
import { Docente } from '../../modelos/Docentes';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { DocenteService } from '../../servicio/docente.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { switchMap } from 'rxjs';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-docente-main',
  standalone: true,
  imports: [RouterOutlet, MaterialModule, RouterLink],
  templateUrl: './docente-main.html',
  styleUrl: './docente-main.css'
})
export class DocenteComponentMain implements OnInit {
operate() {
throw new Error('Method not implemented.');
}
  
  dataSource!: MatTableDataSource<Docente>;
  displayedColumns = [
    { def: 'idDocente', label: 'ID Docente', hide: false },
    { def: 'nombres', label: 'Nombre', hide: false },
    { def: 'correo', label: 'Correo', hide: false },
    { def: 'nivel', label: 'Nivel', hide: false },
    { def: 'usuario', label: 'Usuario', hide: false },
    { def: 'rol', label: 'Rol', hide: false },
    { def: 'accion', label: 'Acción', hide: false },
  ];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private docenteService: DocenteService,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.docenteService.findAll().subscribe((data) => {
      this.docenteService.setEntidadChange(data);
    });
    this.docenteService.getEntidadChange().subscribe((data) => {
      this.createTable(data);
    });
    this.docenteService
      .getMessageChange()
      .subscribe((data) =>
        this._snackBar.open(data, 'INFO', { duration: 2000 })
      );
  }

  createTable(docente: Docente[]) {
    this.dataSource = new MatTableDataSource(docente);
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
    this.docenteService
      .delete(id)
      .pipe(switchMap(() => this.docenteService.findAll()))
      .subscribe((res) => {
        this.docenteService.setEntidadChange(res);
        this.docenteService.setMessageChange('DELETED!');
      });
  }
}
