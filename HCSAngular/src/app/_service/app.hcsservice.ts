import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { CenterModel } from '../_model/app.centermodel';
import { AppointmentModel } from '../_model/app.appointmentmodel';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class HcsService {

    constructor(private myhttp: HttpClient) { }

    getCenters() {
        return this.myhttp.get("http://localhost:9123/getCenters");
    }

    getTests(centerId: any) {
        let params = new HttpParams();
        params = params.append('centerId', centerId);
        return this.myhttp.get('http://localhost:9123/getTests', { params: params });
    }

    addAppointment(appointment: any) {

        return this.myhttp.post('http://localhost:9123/addAppointment', appointment);
    }

    getUserAppointments(userId: any) {
        let params = new HttpParams();
        params = params.append('userId', userId);
        return this.myhttp.get('http://localhost:9123/viewAppointments', { params: params });
    }

    getAppointments(centerId: any) {
        let params = new HttpParams();
        params = params.append('centerId', centerId);
        return this.myhttp.get('http://localhost:9123/pendingAppointments', { params: params });
    }

    approveAppointment(appointmentId: any) {
        let params = new HttpParams();
        params = params.append('appointmentId', appointmentId);
        return this.myhttp.post("http://localhost:9123/approveAppointment?appointmentId" + appointmentId, params);
    }

    addCenter(data: any) {
        return this.myhttp.post("http://localhost:9123/addCenter",data);
    }

    addTest(centerId:any,data:any)
    {
        let params = new HttpParams();
        params = params.append('centerId', centerId);
        params = params.append('testName', data.testName);
        return this.myhttp.post("http://localhost:9123/addTest",params);
    }

    deleteCenter(centerId: any) {
        // let params: URLSearchParams = new URLSearchParams();
        // params.set('appid', StaticSettings.API_KEY);
        return this.myhttp.delete("http://localhost:9123/removeCenter?centerId="+centerId);
     }

     deleteTest(centerId:any,testId:any)
    {
        return this.myhttp.delete("http://localhost:9123/removeTest?centerId="+centerId+"&testId="+testId);
    }

    download(userId:any): Observable<Blob>{
        return this.myhttp.get("http://localhost:9123/download?userId="+userId, {'responseType':"blob"});
    }



    authenticate(username, password) {
        if (username === "test@hcs.com" && password === "password") {
            sessionStorage.setItem('username', username)
            return true;
        } else {
            return false;
        }
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem('username')
        console.log(!(user === null))
        return !(user === null)
    }

    logOut() {
        alert("Remove")
        sessionStorage.removeItem('username')
    }

    
}