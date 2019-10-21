import { Component, OnInit } from "@angular/core";
import {HcsService} from '../_service/app.hcsservice';
import { CenterModel } from "../_model/app.centermodel";
@Component({
    selector:'deletecenter',
    templateUrl:'../_html/app.deletecenter.html'

})
export class DeleteCenterComponent implements OnInit
{
    
    centerList:CenterModel[]=[];
    public popoverTitle:string='Delete Confirmation';
    public popoverMessage:string="Do you really want to delete the center";
    public confirmClicked:boolean=false;
    public cancelClicked:boolean=false;
    constructor(private service:HcsService){
     }
     ngOnInit(): void{
        this.service.getCenters().subscribe((data:CenterModel[])=>this.centerList=data);

     }
        deleteCenter(centerId:any):any
        { 
        this.service.deleteCenter(centerId).subscribe(
          
        );
        }
}