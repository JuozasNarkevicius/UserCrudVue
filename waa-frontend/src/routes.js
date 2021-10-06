import NewPerson from "./components/NewPerson"
import Persons from "./components/Persons"
import EditPerson from "./components/EditPerson"

export const routes=[
    {path: '/newperson', component: NewPerson},
    {path: '/editperson/:id', component: EditPerson},
    {path: '/', component: Persons}
]