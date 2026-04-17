import { MenuItem } from "./menu.model";



export const MENU1 = {
    adminMenu: [
        {
            id: 1,
            label: 'MENUITEMS.MENU.TEXT',
            isTitle: true
        },
        {
            id: 2,
            label: 'dashboard',
            icon: 'bx bx-line-chart',
            link: '/analytics',
        },
        {
            id: 3,
            label: 'Les activités',
            icon: 'bxs bxs-heart',
            link: '/users/groups',
        },
        {
            id: 4,
            label: 'Les coachs',
            icon: 'bx bxs-user',
            link: '/users/coachs',
        },
        {
            id: 5,
            label: 'Les adhérents actifs',
            icon: 'bx bxs-user',
            link: '/users/user-externe',
        },
        {
            id: 6,
            label: 'Abonnements expirés',
            icon: 'bx bxs-user',
            link: '/users/abonnement_expirés',
        },
    ],

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
            label: 'Offres',
            icon: 'fa-solid fa-briefcase',
            link: '/offre/list-offre'
        },
        {
            id: 4,
            label: 'Candidatures',
            icon: 'fa-solid fa-file-alt',
            link: '/demandes/candidatures'
        },
        {
            id: 5,
            label: 'Entretiens',
            icon: 'fa-solid fa-user-tie',
            link: '/entretiens/list_entretien'
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