import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AjouterOffreComponent } from './page/ajouter-offre/ajouter-offre.component';
import { ListOffreComponent } from './page/list-offre/list-offre.component';
import { ModifierOffreComponent } from './page/modifier-offre/modifier-offre.component';
import { AnnouncementCandidatComponent } from './page/announcement-candidat/announcement-candidat.component';
import { EnvoyerDemandeComponent } from '../demandes/pages/envoyer-demande/envoyer-demande.component';
import { SecteurComponent } from './page/secteurs/secteur/secteur.component';
import { AjouterSecteurComponent } from './page/secteurs/ajouter-secteur/ajouter-secteur.component';
import { ModifierSecteurComponent } from './page/secteurs/modifier-secteur/modifier-secteur.component';
import { ListRecruteurComponent } from './page/recruteur/list-recruteur/list-recruteur.component';
import { AjouterRecruteurComponent } from './page/recruteur/ajouter-recruteur/ajouter-recruteur.component';
import { ModifierRecruteurComponent } from './page/recruteur/modifier-recruteur/modifier-recruteur.component';
// configuration router
const routes: Routes = [

  {
    path:"secteur",
    component:SecteurComponent
  },
   {
    path:"ajouter_secteur",
    component:AjouterSecteurComponent
  },
   {
    path:"modifier_secteur/:id",
    component:ModifierSecteurComponent
  },


  {
    path:"recruteur",
    component:ListRecruteurComponent
  },
   {
    path:"ajouter_recruteur",
    component:AjouterRecruteurComponent
  },
   {
    path:"modifier_recruteur/:id",
    component:ModifierRecruteurComponent
  },

  


  {
    path:"ajouter",
    component:AjouterOffreComponent
  },
  {
    path:"list-offre",
    component:ListOffreComponent
  },
  {
    path:"modifier/:id",
    component:ModifierOffreComponent
  },
  {
    path:'Announcement_Actuel',
    component:AnnouncementCandidatComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OffreRoutingModule { }
