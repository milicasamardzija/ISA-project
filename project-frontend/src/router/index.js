import { createRouter, createWebHistory } from 'vue-router';

import BoatsStartPage from '../views/BoatsStartPage'
import CottagesStartPage from '../views/CottagesStartPage'
import AdventuresStartPage from '../views/AdventuresStartPage'

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
    }
]

const router = createRouter({
    history: createWebHistory(process.env
    .BASE_URL),
    routes,
})

export default router