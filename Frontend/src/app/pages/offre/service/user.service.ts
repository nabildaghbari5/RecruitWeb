import { Injectable } from '@angular/core';
import { Observable, first, lastValueFrom, map } from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    baseUrl() {
        return 'http://localhost:8081/api/user'
    }
    constructor(private http: HttpClient) {
    }


    findByRole(role: string): Observable<any> {
        let url = `${this.baseUrl()}/role/${role}`;
        return this.http.get<any>(url);
    }

    findById(id: number): Observable<any> {
        let url = `${this.baseUrl()}/${id}`; 
        return this.http.get<any>(url);
    }

    update(id: number, user: any): Observable<any> {
        const url = `${this.baseUrl()}/${id}`;
        return this.http.put<any>(url, user);
    }

    delete(id: number): Observable<void> {
        const url = `${this.baseUrl()}/${id}`;
        return this.http.delete<void>(url);
    }
}
