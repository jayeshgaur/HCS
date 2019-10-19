import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import{FormsModule} from '@angular/forms';
import {GetCentersComponent} from './_components/app.getcenterscomponent';
import { HttpClientModule } from '@angular/common/http';
import {GetTestsComponent} from './_components/app.gettestscomponent';
import { AddAppointmentComponent } from './_components/app.addappointment';
import { Routes, RouterModule } from '@angular/router'


const myRoute: Routes = [
    { path: '', redirectTo:'home', pathMatch:'full'},
    { path: '', component: }

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        RouterModule.forRoot(myRoute)
        
    ],
    declarations: [
        AppComponent, GetCentersComponent,GetTestsComponent, AddAppointmentComponent
   	],
    providers: [ ],
    bootstrap: [AppComponent]
})

export class AppModule { }