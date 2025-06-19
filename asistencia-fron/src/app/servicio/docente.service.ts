import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { Docente } from '../modelos/Docentes';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DocenteService extends GenericService<Docente> {

  private entidadSubject = new BehaviorSubject<Docente[]>([]);
  private messageChange: Subject<string> = new Subject<string>();

  constructor(protected override http: HttpClient) {
    super(http, `${environment.HOST}/docente`);
  }

  setEntidadChange(data: Docente[]) { this.entidadSubject.next(data); }
  getEntidadChange() { return this.entidadSubject.asObservable(); }

  setMessageChange(data: string) { this.messageChange.next(data); }
  getMessageChange() { return this.messageChange.asObservable(); }

}
