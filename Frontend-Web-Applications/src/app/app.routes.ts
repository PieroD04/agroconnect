import { Routes } from '@angular/router';

import { RegisterCageComponent } from "./management/components/register-cage/register-cage.component";
import { RegisterExpensesComponent } from "./management/components/register-expenses/register-expenses.component";
import { RegisterCuyComponent } from "./management/components/register-cuy/register-cuy.component";
import { RegisterResourcesComponent} from "./management/components/register-resources/register-resources.component";


import {MyFarmViewComponent} from "./management/components/my-farm-view/my-farm-view.component";
import {MyFarmResourceManagementComponent} from "./management/components/my-farm-resource-management/my-farm-resource-management.component"
import {MyFarmExpensesManagementComponent} from "./management/components/my-farm-expenses-management/my-farm-expenses-management.component";

import {NotificationsViewComponent} from "./appointment/pages/notifications-view/notifications-view.component";
import {CageListComponent} from "./management/pages/cage-list/cage-list.component";
import {CageEditorComponent} from "./management/pages/cage-editor/cage-editor.component";
import {AnimalListComponent} from "./management/pages/animal-list/animal-list.component";
import {AnimalInformationComponent} from "./management/pages/animal-information/animal-information.component";
import {ClientsViewComponent} from "./appointment/pages/clients-view/clients-view.component";
import {MyPublicationsComponent} from "./publication/pages/my-publications/my-publications.component";
import {NewPublicationComponent} from "./publication/pages/new-publication/new-publication.component";
import {PublicationDetailComponent} from "./publication/pages/publication-detail/publication-detail.component";
import {PublicationsViewComponent} from "./publication/pages/publications-view/publications-view.component";

import {ViewAdvisorsSearchComponent} from "./appointment/pages/view-advisors-search/view-advisors-search.component";
import {ViewMyAdvisorsComponent} from "./appointment/components/view-my-advisors/view-my-advisors.component";
import {ViewAdvisorAboutusComponent} from "./appointment/components/view-advisor-aboutus/view-advisor-aboutus.component";
import {ViewReserveAppointmentComponent} from "./appointment/components/view-reserve-appointment/view-reserve-appointment.component";
import {ReviewComponent} from "./appointment/components/review/review.component";
import {LoginComponent} from "./iam/pages/login/login.component";
import {ClientDetailComponent} from "./appointment/components/client-detail/client-detail.component";

import {EditExpenseComponent} from "./management/components/edit-expense/edit-expense.component";
import {EditResourceComponent} from "./management/components/edit-resource/edit-resource.component";

import {SignupComponent} from "./iam/pages/signup/signup.component";
import {SignupBreederComponent} from "./iam/pages/signup-breeder/signup-breeder.component";
import {SignupAdvisorComponent} from "./iam/pages/signup-advisor/signup-advisor.component";

import {ListAvailabilityScheduleComponent} from "./appointment/pages/list-availability-schedule/list-availability-schedule.component";
import {AddAvailabilityScheduleComponent} from "./appointment/components/add-availability-schedule/add-availability-schedule.component";

import {CalendarComponent} from "./appointment/pages/calendar/calendar.component";

export const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'registro', component: SignupComponent},
  {path: 'registro/criador', component: SignupBreederComponent},
  {path: 'registro/asesor', component: SignupAdvisorComponent},
  {path: 'criador/mi-granja', component: MyFarmViewComponent},
  {path: 'criador/mi-granja/recursos', component: MyFarmResourceManagementComponent},
  {path: 'criador/mi-granja/recursos/editar/:id', component: EditResourceComponent},
  {path: 'criador/mi-granja/gastos', component: MyFarmExpensesManagementComponent},
  {path: 'criador/mi-granja/gastos/editar/:id', component: EditExpenseComponent},
  {path: 'criador/buscar-asesor', component: ViewAdvisorsSearchComponent},
  {path: 'criador/mis-asesores', component: ViewMyAdvisorsComponent},
  {path: 'criador/asesor-info/:id', component: ViewAdvisorAboutusComponent},
  {path: 'criador/asesor-info/:id/reservar-cita', component: ViewReserveAppointmentComponent},
  {path: 'criador/mis-asesores/:id', component: ReviewComponent},
  {path: 'criador/mis-animales', component: CageListComponent},
  {path: 'criador/mis-animales/:id', component: AnimalListComponent},
  {path: 'criador/mis-animales/:cageid/informacion/:id', component: AnimalInformationComponent},
  {path: 'criador/mis-animales/editar/:id', component: CageEditorComponent},
  {path: 'criador/registro/jaula', component: RegisterCageComponent},
  {path: 'criador/registro/gasto', component: RegisterExpensesComponent},
  {path: 'criador/mis-animales/:cageid/registro-animal', component: RegisterCuyComponent},
  {path: 'criador/registro/recurso', component: RegisterResourcesComponent},
  {path: 'criador/publicaciones', component: PublicationsViewComponent},
  {path: 'criador/notificaciones', component: NotificationsViewComponent},
  {path: 'asesor/clientes', component: ClientsViewComponent },
  {path: 'asesor/clientes/:id', component: ClientDetailComponent},
  {path: 'asesor/mis-publicaciones', component: MyPublicationsComponent },
  {path: 'asesor/nueva-publicacion', component: NewPublicationComponent },
  {path: 'asesor/mis-publicaciones/:id', component: PublicationDetailComponent},
  {path: 'asesor/notificaciones', component: NotificationsViewComponent},
  {path: 'criador/calendario', component: CalendarComponent},
  {path: 'asesor/calendario', component: CalendarComponent},
  {path: 'asesor/horarios', component: ListAvailabilityScheduleComponent},
  {path: 'asesor/horarios/agregar', component: AddAvailabilityScheduleComponent}
];
