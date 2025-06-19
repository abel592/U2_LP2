import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { Horarios } from '../modelos/horario';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HorarioService extends GenericService<Horarios> {

  private entidadSubject = new BehaviorSubject<Horarios[]>([]);
  private messageChange: Subject<string> = new Subject<string>();

  constructor(protected override http: HttpClient) {
    super(http, `${environment.HOST}/horario`);
  }

  setEntidadChange(data: Horarios[]) { this.entidadSubject.next(data); }
  getEntidadChange() { return this.entidadSubject.asObservable(); }

  setMessageChange(data: string) { this.messageChange.next(data); }
  getMessageChange() { return this.messageChange.asObservable(); }

}
