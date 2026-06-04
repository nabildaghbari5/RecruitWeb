import { Component, OnInit } from '@angular/core';
import { FormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../../service/user.service';

@Component({
  selector: 'app-modifier-recruteur',
  templateUrl: './modifier-recruteur.component.html',
  styleUrl: './modifier-recruteur.component.scss'
})
export class ModifierRecruteurComponent implements OnInit {
  signupForm!: UntypedFormGroup;
  recruteurId!: number;
  private recruteur: any;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      lastname: ['', Validators.required],
      firstname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      role: ['RECRUTEUR'],
    });

    this.route.params.subscribe(params => {
      this.recruteurId = +params['id'];
      this.chargerRecruteur(this.recruteurId);
    });
  }

  chargerRecruteur(id: number) {
    this.userService.findById(id).subscribe({
      next: (data) => {
        this.recruteur = data;
        this.signupForm.patchValue({
          firstname: data.firstname,
          lastname: data.lastname,
          email: data.email,
          role: data.role,
        });
      },
      error: (err) => {
        console.error('Erreur lors du chargement du recruteur :', err);
      }
    });
  }

  modifier() {
    if (this.signupForm.invalid) {
      return;
    }

    const formData = this.signupForm.value;
    const body = {
      firstname: formData.firstname,
      lastname: formData.lastname,
      email: formData.email,
      telephone: this.recruteur?.telephone,
      status: this.recruteur?.status,
      enabled: this.recruteur?.enabled,
      accountLocked: this.recruteur?.accountLocked,
    };

    this.userService.update(this.recruteurId, body).subscribe({
      next: () => {
        this.router.navigate(['/offre/recruteur']);
      },
      error: (err) => {
        console.error('Erreur lors de la modification :', err);
      }
    });
  }

  Anuller() {
    this.router.navigate(['/offre/recruteur']);
    this.signupForm.reset();
  }
}
