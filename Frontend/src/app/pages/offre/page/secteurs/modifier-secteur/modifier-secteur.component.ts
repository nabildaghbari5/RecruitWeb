import { Component, OnInit } from '@angular/core';
import { SecteurService } from '../../../service/secteur.service';
import { UserService } from '../../../service/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-modifier-secteur',
  templateUrl: './modifier-secteur.component.html',
  styleUrl: './modifier-secteur.component.scss'
})
export class ModifierSecteurComponent implements OnInit {

  secterurId!: number;

  secteur!: FormGroup;

  recruteur: any[] = [];

  constructor(
    private secteurService: SecteurService,
    private userService: UserService,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {

    this.secteur = this.formBuilder.group({
      name: ['', Validators.required],
      recruteurId: ['', Validators.required]
    });

    this.findAllRecruteur();

    this.route.params.subscribe(params => {

      this.secterurId = +params['id'];

      this.chargerSecteur(this.secterurId);

    });

  }

  findAllRecruteur() {

    this.userService.findByRole('RECRUTEUR').subscribe({

      next: (response) => {

        this.recruteur = response;

      },

      error: (err) => {
        console.error(err);
      }

    });

  }

  chargerSecteur(id: number) {

    this.secteurService.findById(id).subscribe({

      next: (data: any) => {

        this.secteur.patchValue({

          name: data.name,

          recruteurId: data.recruteur?.id

        });

      }

    });

  }

  update() {

    const formData = this.secteur.value;

    const recruteurId = formData.recruteurId;

    const body = {
      name: formData.name
    };

    this.secteurService
      .update(this.secterurId, recruteurId, body)
      .subscribe({

        next: () => {

          this.router.navigate(["/offre/secteur"]);

          this.secteur.reset();

        },

        error: error => {
          console.error(error);
        }

      });

  }

  Anuller() {

    this.router.navigate(["/offre/secteur"]);

    this.secteur.reset();

  }

}