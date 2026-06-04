import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../../service/user.service';

@Component({
  selector: 'app-list-recruteur',

  templateUrl: './list-recruteur.component.html',
  styleUrl: './list-recruteur.component.scss'
})
export class ListRecruteurComponent implements OnInit {
  recruteur:any
  
  constructor(
     private userService: UserService,
     private router: Router,
   ) {}
 
   ngOnInit(): void {
     this.findAll();

   }
 
findAll() {
  this.userService.findByRole('RECRUTEUR').subscribe({
    next: (response) => {
      this.recruteur = response;
      
      console.log(response);
    },
    error: (err) => {
      console.error(err);
    }
  });
}




  edit(id:number){
   this.router.navigate(["/offre/modifier_recruteur", id]);
  }

  delete(id: number) {
    if (!confirm('Voulez-vous vraiment supprimer ce recruteur ?')) {
      return;
    }

    this.userService.delete(id).subscribe({
      next: () => {
        this.findAll();
      },
      error: (err) => {
        console.error('Erreur lors de la suppression du recruteur :', err);
        const message = err?.error?.message
          || err?.error
          || 'Impossible de supprimer ce recruteur.';
        alert(typeof message === 'string' ? message : 'Impossible de supprimer ce recruteur.');
      }
    });
  }
}
