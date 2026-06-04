import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServEntrepriseService {

  constructor(private http: HttpClient) { }

  baseUrl(){
    return 'http://localhost:8081/api/service' 
  }

  save(id: number|null, data: any, recruiterId?: number): Observable<any> {
    if (id) {
      return this.update(id, data);
    }
    if (recruiterId == null) {
      throw new Error('L\'identifiant du recruteur est requis pour créer une offre');
    }
    return this.create(recruiterId, data);
  }

  create(recruiterId: number, data: any): Observable<any> {
    const url = `${this.baseUrl()}/recruiter/${recruiterId}`;
    return this.http.post<any>(url, data);
  }

  update(id: number, any: any): Observable<any> {
    let url = `${this.baseUrl()}/${id}`;
    return this.http.put<any>(url, any);
  }

  importCSV(formData: FormData): Observable<void> {
    let url = this.baseUrl() + '/import';

    let headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    const options = { headers };

    return this.http.post<void>(url, formData, options);
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
