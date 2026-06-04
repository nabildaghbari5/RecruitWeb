import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SecteurService {

  constructor(private http: HttpClient) { }

  baseUrl(){
    return 'http://localhost:8081/api/secteur' 
  }



  create(recruteurId:number ,secteur: any): Observable<any> {
    let url =`${this.baseUrl()}/${recruteurId}`;
    return this.http.post<any>(url, secteur);
  }

  update(id: number, recruteurId:number ,  any: any): Observable<any> {
    let url = `${this.baseUrl()}/${id}/${recruteurId}`;
    return this.http.put<any>(url, any);
  }

 

  findById(id: number): Observable<any> {
    let url = `${this.baseUrl()}/${id}`;
    return this.http.get<any>(url);
  }

  findAll(): Observable<Array<any>> {
    let url = this.baseUrl();
    return this.http.get<Array<any>>(url);
  }


  delete(id: number): Observable<boolean> {
    let url = `${this.baseUrl()}/${id}`;
    return this.http.delete<boolean>(url);
  }
}