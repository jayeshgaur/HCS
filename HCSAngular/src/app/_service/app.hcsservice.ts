import { Injectable } from '@angular/core';
import { HttpClient,HttpParams } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { CenterModel } from '../_model/app.centermodel';
import { AppointmentModel } from '../_model/app.appointmentmodel';

@Injectable({
    providedIn: 'root'
})
export class HcsService {
    constructor(private myhttp: HttpClient) {}

    getCenters(){   
       return this.myhttp.get("http://localhost:9123/getCenters");
    }

    getTests(centerId:any){
        let params = new HttpParams();
        params = params.append('centerId', centerId);
        return this.myhttp.get('http://localhost:9123/getTests',{params:params});
    }

    addAppointment(appointment:any){
        
        return this.myhttp.post('http://localhost:9123/addAppointment', appointment);
    }

    getUserAppointments(userId:any){
        let params = new HttpParams();
        params = params.append('userId', userId);
        return this.myhttp.get('http://localhost:9123/viewAppointments',{params:params});
    }

}