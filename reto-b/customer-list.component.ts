import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-customer-list',
  standalone: true,
  template: `
    <div>
      <h2>Customers</h2>
      <input placeholder="Search by name" (input)="onSearch($event)">
      <select (change)="onStatus($event)">
        <option value="">All</option>
        <option value="ACTIVE">ACTIVE</option>
        <option value="INACTIVE">INACTIVE</option>
      </select>
      <button (click)="load()">Reload</button>
      <table>
        <tr><th>Id</th><th>Name</th><th>Status</th><th>Accounts</th></tr>
        <tr *ngFor="let c of customers">
          <td>{{c.id}}</td>
          <td>{{c.name}}</td>
          <td>{{c.status}}</td>
          <td>
            <span *ngFor="let a of c.accounts">{{a}} </span>
          </td>
        </tr>
      </table>
      <div *ngIf="loading">Loading...</div>
    </div>
  `
})
export class CustomerListComponent {
  http = inject(HttpClient);
  customers: any[] = []; // ANY :(
  loading = false;
  filterName: any = '';   // ANY :(
  status: any = '';       // ANY :(

  constructor() {
    // side-effect en constructor
    this.load();
  }

  onSearch(e: any) { // ANY y acceso directo a target
    this.filterName = e.target.value;
  }

  onStatus(e: any) { // ANY
    this.status = e.target.value;
  }

  load() {
    this.loading = true;
    // Mezcla de lógica de UI, negocio y transporte
    const url = '/api/customers/search?name=' + (this.filterName || '') + '&status=' + (this.status || '');
    this.http.get(url).subscribe({
      next: (data: any) => {
        // Asignación directa, sin modelado ni mapeo
        this.customers = (data && data.items) ? data.items : [];
        // Lógica de presentación acá mismo
        for (let i=0; i<this.customers.length; i++) {
          if (!this.customers[i].accounts) this.customers[i].accounts = [];
          // “Normalización” improvisada
          if (this.customers[i].status === 'A') this.customers[i].status = 'ACTIVE';
          if (this.customers[i].status === 'I') this.customers[i].status = 'INACTIVE';
        }
        this.loading = false;
      },
      error: (err) => {
        console.log('error', err);
        this.loading = false;
      }
    });
  }
}
