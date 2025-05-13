import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Docente } from '../models/docente.interface';

@Injectable({
  providedIn: 'root'
})
export class DocenteService {
  private apiUrl = `${environment.apiUrl}/docentes`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<Docente[]> {
    return this.http.get<Docente[]>(this.apiUrl);
  }

  getById(id: number): Observable<Docente> {
    return this.http.get<Docente>(`${this.apiUrl}/${id}`);
  }

  create(docente: Docente): Observable<Docente> {
    return this.http.post<Docente>(this.apiUrl, docente);
  }

  update(id: number, docente: Docente): Observable<Docente> {
    return this.http.put<Docente>(`${this.apiUrl}/${id}`, docente);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}