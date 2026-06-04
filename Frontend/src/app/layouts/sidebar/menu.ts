import { MenuItem } from "./menu.model";



export const MENU1 = {
   

    rhMenu: [
        {
            id: 1,
            label: 'MENU RH',
            isTitle: true
        },
        
        {
            id: 2,
            label: 'Tableau de bord',
            icon: 'fa-solid fa-chart-line',
            link: '/dashboard/statistique'
        },
         {
            id: 3,
            label: 'Recruteurs',
            icon: 'fa-solid fa-chart-line',
            link: '/offre/recruteur'
        },
        {
            id: 4,
            label: 'Secteur',
            icon: 'fa-solid fa-chart-line',
            link: '/offre/secteur'
        },
       

        {
            id: 3,
            label: 'Offres',
            icon: 'fa-solid fa-briefcase',
            link: '/offre/list-offre'
        }
        
    ],

    candidatMenu: [
        {
            id: 1,
            label: 'MENU CANDIDAT',
            isTitle: true
        },
        {
            id: 2,
            label: 'Announcement',
            icon: 'fa-solid fa-bullhorn',
            link: '/offre/Announcement_Actuel'
        },
        {
            id: 3,
            label: 'Mes candidatures',
            icon: 'fa-solid fa-briefcase',
            link: '/demandes/mycandidatures'
        }
    ]
};



export const MENU: MenuItem[] = [ ]