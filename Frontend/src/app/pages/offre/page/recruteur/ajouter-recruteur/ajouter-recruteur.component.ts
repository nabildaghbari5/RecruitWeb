import { Component, OnInit } from '@angular/core';
import { FormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/account/authentification/auth.service';

@Component({
  selector: 'app-ajouter-recruteur',
  templateUrl: './ajouter-recruteur.component.html',
  styleUrl: './ajouter-recruteur.component.scss'
})
export class AjouterRecruteurComponent implements OnInit {
  signupForm!: UntypedFormGroup;

  constructor(
    private router: Router,
    private authEntreprise: AuthService,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      lastname: ['', Validators.required],
      firstname: ['', Validators.required],
      email: ['', [Validators.required]],
      password: ['', [Validators.required]],
      role: ['RECRUTEUR'],
    }); 
  }


  ajouter() {
    const formData = this.signupForm.value;
    this.authEntreprise.register(formData).subscribe({
      next: (data) => {
        console.log(data);
        this.router.navigate(["/offre/recruteur"]);
      },
      error: (err) => {
        console.error('Erreur lors de l\'inscription :', err);
      }
    });
  }

  Anuller() {
    this.router.navigate(["/offre/recruteur"]);
    this.signupForm.reset();
  }
}
