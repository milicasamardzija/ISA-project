import { createRouter, createWebHistory } from 'vue-router';

import BoatsStartPage from '../views/startPage/BoatsStartPage'
import CottagesStartPage from '../views/startPage/CottagesStartPage'
import AdventuresStartPage from '../views/startPage/AdventuresStartPage'
import ClientAllCottages from '../views/client/ClientAllCottages'
import ClientAllBoats from '../views/client/ClientAllBoats'
import ClientAllAdventures from '../views/client/ClientAllAdventures'
import ScheduledReservation from '../views/client/reservations/ScheduledReservation'
import ReservationHistory from '../views/client/reservations/ReservationHistory'
import HomePageInProfil from '../views/cottageOwner/HomePageInProfil'
import MyCottages from '../views/cottageOwner/MyCottages'
import MyProfile from '../views/cottageOwner/MyProfile'
import Statistic from '../views/cottageOwner/Statistic'
import EditProfile from '../views/cottageOwner/EditProfile'
import NewCottage from '../views/cottageOwner/NewCottage'
import AllRegistrationRequests from '../views/administrator/AllRegistrationRequests'
import MyService from '../views/fishingInstructor/MyService'
import AdventurePage from '../views/adventure/AdventurePage'

const routes = [
    {
        path: '/',
        name: 'BoatsStartPage',
        component: BoatsStartPage
    },
    {
        path: '/cottages',
        name: 'CottageStartPage',
        component: CottagesStartPage
    },
    {
        path: '/adventures',
        name: 'AdventuresStartPage',
        component: AdventuresStartPage
    },
    {
        path: '/client',
        name: 'ClientAllCottages',
        component: ClientAllCottages
    },
    {
        path: '/adventurePage',
        name: 'AdventurePage',
        component: AdventurePage
    },    
    {
        path: '/administrator',
        name: 'AllRegistrationRequests',
        component: AllRegistrationRequests
    },
    {
        path: '/fishingInstructor',
        name: 'MyService',
        component: MyService
    },
    {
        path: '/clientBoats',
        name: 'ClientAllBoats',
        component: ClientAllBoats
    },
    {
        path: '/clientAdventures',
        name: 'ClientAllAdventures',
        component: ClientAllAdventures
    },
    {
        path: '/clientScheduledReservations',
        name: 'ScheduledReservation',
        component: ScheduledReservation
    },
    {
        path: '/clientReservationHistory',
        name: 'ReservationHistory',
        component: ReservationHistory
    },
    {
        path: '/cottageOwnerHomePage',
        name: 'HomePageInProfil',
        component: HomePageInProfil
    },
    {
        path: '/myCottages',
        name: 'MyCottages',
        component: MyCottages
    },
    {
        path: '/myProfile',
        name: 'MyProfile',
        component: MyProfile
    },
    {
        path: '/editProfile',
        name: 'EditProfile',
        component: EditProfile
    },
    {
        path: '/statistic',
        name: 'Statistic',
        component: Statistic
    },
    {
        path: '/newCottage',
        name: 'NewCottage',
        component: NewCottage
    }
]


const router = createRouter({
    history: createWebHistory(process.env
    .BASE_URL),
    routes,
})

export default router