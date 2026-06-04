import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ServEntrepriseService } from '../../service/serv-entreprise.service';
import { UserService } from '../../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ajouter-offre',
  templateUrl: './ajouter-offre.component.html', 
  styleUrls: ['./ajouter-offre.component.scss']
})
export class AjouterOffreComponent implements OnInit {

  serviceEntrepriseForm!:FormGroup;
  recruteurs: any[] = [];

  constructor(
    private serviceService: ServEntrepriseService,
    private formBuilder:FormBuilder,
    private userService: UserService,
    private router :Router
  ) { }

  
  ngOnInit(): void {
    this.loadRecruteurs();
    this.serviceEntrepriseForm=this.formBuilder.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      technologies: ['', Validators.required],
      endDate: ['', [Validators.required]],
      publicationDate: ['',  [Validators.required]],
      recruteurId: ['', Validators.required],
    })

  }

  loadRecruteurs(): void {
    this.userService.findByRole('RECRUTEUR').subscribe({
      next: (response) => {
        this.recruteurs = response;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des recruteurs', err);
      }
    });
  }
 

  saveOffre() {
   

    const formData: any = this.serviceEntrepriseForm.value;
    const recruiterId = formData.recruteurId;

    if (!recruiterId) {
      console.error('Veuillez sélectionner un recruteur');
      return;
    }

    this.serviceService.save(null, formData, recruiterId).subscribe({
      next: () => {
        this.router.navigate(['/offre/list-offre']);
        this.serviceEntrepriseForm.reset();
      },
      error: error => {
        console.log('Erreur lors de la création de l\'offre', error);
      }
    });
  }
  
  Anuller(){
    this.router.navigate(["/offre/list-offre"]);
    this.serviceEntrepriseForm.reset();   
  }


 
}

