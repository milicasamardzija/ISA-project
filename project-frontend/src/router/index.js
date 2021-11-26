import { createRouter, createWebHistory } from 'vue-router';

import BoatsStartPage from '../views/BoatsStartPage'
import CottagesStartPage from '../views/CottagesStartPage'
import AdventuresStartPage from '../views/AdventuresStartPage'
import ClientAllCottages from '../views/ClientAllCottages'
import ClientAllBoats from '../views/ClientAllBoats'
import ClientAllAdventures from '../views/ClientAllAdventures'

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
    }
]

const router = createRouter({
    history: createWebHistory(process.env
    .BASE_URL),
    routes,
})

export default router