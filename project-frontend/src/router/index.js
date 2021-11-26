import { createRouter, createWebHistory } from 'vue-router';

import BoatsStartPage from '../views/startPage/BoatsStartPage'
import CottagesStartPage from '../views/startPage/CottagesStartPage'
import AdventuresStartPage from '../views/startPage/AdventuresStartPage'
import ClientAllCottages from '../views/client/ClientAllCottages'
import ClientAllBoats from '../views/client/ClientAllBoats'
import ClientAllAdventures from '../views/client/ClientAllAdventures'
import ScheduledReservation from '../views/client/reservations/ScheduledReservation'

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
    }
]

const router = createRouter({
    history: createWebHistory(process.env
    .BASE_URL),
    routes,
})

export default router