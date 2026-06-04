import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServEntrepriseService } from '../../service/serv-entreprise.service';
import { UserService } from '../../service/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-modifier-offre',
  templateUrl: './modifier-offre.component.html',
  styleUrls: ['./modifier-offre.component.scss']
})
export class ModifierOffreComponent implements OnInit {
  offreId!: number;
  serviceEntrepriseForm!: FormGroup;
  recruteurs: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private offreService: ServEntrepriseService,
    private userService: UserService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.serviceEntrepriseForm = this.formBuilder.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      technologies: ['', Validators.required],
      endDate: ['', Validators.required],
      publicationDate: ['', Validators.required],
      recruteurId: ['', Validators.required],
    });

    this.loadRecruteurs();

    this.route.params.subscribe(params => {
      this.offreId = +params['id'];
      this.chargerOffre(this.offreId);
    });
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

  private formatDateForInput(date: string | null | undefined): string {
    if (!date) {
      return '';
    }
    return date.substring(0, 10);
  }

  chargerOffre(id: number): void {
    this.offreService.findById(id).subscribe({
      next: (data) => {
        this.serviceEntrepriseForm.patchValue({
          title: data.title,
          description: data.description,
          technologies: data.technologies,
          publicationDate: this.formatDateForInput(data.publicationDate),
          endDate: this.formatDateForInput(data.endDate),
          recruteurId: data.recruiterId,
        });
      },
      error: (err) => {
        console.error('Erreur lors du chargement de l\'offre', err);
      }
    });
  }

  modiferOffre(): void {
    if (this.serviceEntrepriseForm.invalid) {
      this.serviceEntrepriseForm.markAllAsTouched();
      return;
    }

    const formData = this.serviceEntrepriseForm.value;
    const payload = {
      title: formData.title,
      description: formData.description,
      technologies: formData.technologies,
      publicationDate: formData.publicationDate,
      endDate: formData.endDate,
      recruiterId: formData.recruteurId,
    };

    this.offreService.save(this.offreId, payload).subscribe({
      next: () => {
        this.router.navigate(['/offre/list-offre']);
      },
      error: (error) => {
        console.error('Erreur lors de la modification de l\'offre', error);
      }
    });
  }

  Anuller(): void {
    this.router.navigate(['/offre/list-offre']);
  }
}