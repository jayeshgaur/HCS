import {Component, OnInit} from '@angular/core';
import { HcsService } from '../_service/app.hcsservice';
import { AppointmentModel } from '../_model/app.appointmentmodel';
import { saveAs } from 'file-saver';
import { Router } from '@angular/router';


@Component({
    selector: 'viewappointment',
    templateUrl: '../_html/app.viewappointment.html'
})

export class ViewAppointment implements OnInit{

    appointmentList:AppointmentModel[]=[];


    constructor(private service:HcsService, private router:Router){

    }

    ngOnInit(){
        if(!(sessionStorage.getItem('userRole') === "ROLE_Customer")){
          this.router.navigate(['/home']);
        }
        this.getList();
    }

    getList(){   
        this.service.getUserAppointments(sessionStorage.getItem('userId')).subscribe(
            (appointmentList:AppointmentModel[]) => 
            (this.appointmentList = appointmentList),
            error => alert(error.error)
            );
    }

    downloadExcel(){
      this.service.download(sessionStorage.getItem('userId')).subscribe(
        response => {
          var blob = new Blob([response], {type: 'application/xlsx'});
          
          var filename = 'appointments.xlsx';
          saveAs(blob,filename);        
        } ,
      error=>{
        alert("Some error");
      }
      );
    }
    


}