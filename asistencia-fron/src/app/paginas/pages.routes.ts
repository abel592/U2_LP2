import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EstudianteComponentMain } from './estudiante-main/estudiante-main';
import { FormEstudiante } from './estudiante-main/form-estudiante/form-estudiante';
import { DocenteComponentMain } from './docente-main/docente-main';
import { FormDocente } from './docente-main/form-docente/form-docente'; // 👈 IMPORTACIÓN AGREGADA
import { HorarioComponentMain } from './horario-main/horario-main';
import { FormHorario } from './horario-main/form-horario/form-horario';

export const pagesRoutes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  {
    path: 'estudiante',
    component: EstudianteComponentMain,
    children: [
      { path: 'new', component: FormEstudiante },
      { path: 'edit/:id', component: FormEstudiante },
    ]
  },
  {
    path: 'docente',
    component: DocenteComponentMain,
    children: [
      { path: 'new', component: FormDocente },
      { path: 'edit/:id', component: FormDocente },
    ]
  }, 
   {
    path: 'horario',
    component: HorarioComponentMain,
    children: [
      { path: 'new', component: FormHorario },
      { path: 'edit/:id', component: FormHorario },
    ]
   }
];
