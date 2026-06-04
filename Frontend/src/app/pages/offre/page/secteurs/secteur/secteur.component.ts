import { Component, OnInit } from '@angular/core';
import { SecteurService } from '../../../service/secteur.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-secteur',
  templateUrl: './secteur.component.html',
  styleUrl: './secteur.component.scss'
})
export class SecteurComponent implements OnInit {
  secteur:any
  constructor(
     private secteurService: SecteurService,
     private router: Router
   ) {}
 
   ngOnInit(): void {
     this.findAllSecteur();
   }
 
   // Récupérer toutes les offres et initialiser la liste filtrée
   findAllSecteur() {
     this.secteurService.findAll().subscribe({
       next: (data) => {
         this.secteur = data;
       },
       error: (err) => {
         console.error('Erreur lors de la récupération des offres :', err);
       }
     });
   }

    // Rediriger vers la page d'édition
  editOffer(id: number) {
 
  }


  editSecteur(id:number){
   this.router.navigate(["/offre/modifier_secteur", id]);
  }

  deleteSecteur(id:number){
     this.secteurService.delete(id).subscribe({
      next: () => {
        this.findAllSecteur();
      },
      error: (err) => {
        console.error('Erreur lors de la suppression de l\'offre :', err);
      }
    });
  }
}
