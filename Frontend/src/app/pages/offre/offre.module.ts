import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AjouterOffreComponent } from './page/ajouter-offre/ajouter-offre.component';
import { ListOffreComponent } from './page/list-offre/list-offre.component';
import { ModifierOffreComponent } from './page/modifier-offre/modifier-offre.component';
import { OffreRoutingModule } from './offre-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AnnouncementCandidatComponent } from './page/announcement-candidat/announcement-candidat.component';
import { SecteurComponent } from './page/secteurs/secteur/secteur.component';
import { AjouterSecteurComponent } from './page/secteurs/ajouter-secteur/ajouter-secteur.component';
import { ModifierSecteurComponent } from './page/secteurs/modifier-secteur/modifier-secteur.component';
import { AjouterRecruteurComponent } from './page/recruteur/ajouter-recruteur/ajouter-recruteur.component';
import { ModifierRecruteurComponent } from './page/recruteur/modifier-recruteur/modifier-recruteur.component';
import { ListRecruteurComponent } from './page/recruteur/list-recruteur/list-recruteur.component';



@NgModule({
  declarations: [
    AjouterOffreComponent,
    ListOffreComponent,
    ModifierOffreComponent,
    AnnouncementCandidatComponent,
    SecteurComponent,
    AjouterSecteurComponent,
    ModifierSecteurComponent,
    AjouterRecruteurComponent,
    ModifierRecruteurComponent,
    ListRecruteurComponent
  ],
  imports: [
    CommonModule,
    OffreRoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class OffreModule { }
