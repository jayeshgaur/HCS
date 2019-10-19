import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import{FormsModule} from '@angular/forms';
import {GetCentersComponent} from './_components/app.getcenterscomponent';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule
        
    ],
    declarations: [
        AppComponent, GetCentersComponent
   	],
    providers: [ ],
    bootstrap: [AppComponent]
})

export class AppModule { }