import { Component } from '@angular/core';
import { SecteurService } from '../../../service/secteur.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../../service/user.service';

@Component({
  selector: 'app-ajouter-secteur',
  templateUrl: './ajouter-secteur.component.html',
  styleUrl: './ajouter-secteur.component.scss'
})
export class AjouterSecteurComponent {
  secteur!: FormGroup;
  recruteur: any
  constructor(
    private secteurService: SecteurService,
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
  ) { }


  ngOnInit(): void {
    this.findAllRecruteur();
    this.secteur = this.formBuilder.group({
      name: ['', Validators.required],
      recruteurId: ['', Validators.required]
    })

  }

  findAllRecruteur() {
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

  save() {
    const formData: any = this.secteur.value;
   const recruteurId = formData.recruteurId;
    // Mode ajout
    this.secteurService.create(recruteurId , formData).subscribe({ 
      next: () => {
        this.router.navigate(["/offre/secteur"]);
        this.secteur.reset();
      },
      error: error => {
        console.log("Erreur lors de la création de l'abonnement", error);
      }
    });

  }

  Anuller() {
    this.router.navigate(["/offre/secteur"]);
    this.secteur.reset();
  }


}
