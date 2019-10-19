import { Injectable } from '@angular/core';
import { HttpClient,HttpParams } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { CenterModel } from '../_model/app.centermodel';

@Injectable({
    providedIn: 'root'
})
export class HcsService {
    constructor(private myhttp: HttpClient) {}

    getCenters(){   
       return this.myhttp.get("http://localhost:9123/getCenters");
    }

    getTests(center:CenterModel){
        let params = new HttpParams();
        params = params.append('centerId', center.centerId);
        return this.myhttp.get('http://localhost:9123/getTests',{params:params});
    }

}