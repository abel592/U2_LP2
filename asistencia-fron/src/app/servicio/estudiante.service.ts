import { Injectable } from '@angular/core';
import {GenericService} from './generic.service';
import {Estudiante} from '../modelos/Estudiante';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {BehaviorSubject, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EstudianteService extends GenericService<Estudiante>{

  private entidadSubject = new BehaviorSubject<Estudiante[]>([]);
  private messageChange: Subject<string> = new Subject<string>;

  constructor(protected override http:HttpClient) {
    super(http, `${environment.HOST}/estudiante`);
  }

  setEntidadChange(data: Estudiante[]) { this.entidadSubject.next(data);}
  getEntidadChange() { return this.entidadSubject.asObservable();}

  setMessageChange(data: string) { this.messageChange.next(data);}
  getMessageChange() { return this.messageChange.asObservable();}

}
