import {
  Component, Input, ElementRef, AfterViewInit, ViewChild
} from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';

import { SampleService } from '../services/sample.service';
import { ActionService } from '../services/action.service';
import { Sample } from '../models/sample';
import { Result } from '../models/result';
import { AlertService } from '../services/alert.service';

import 'rxjs/add/observable/fromEvent';
import 'rxjs/add/operator/takeUntil';
import 'rxjs/add/operator/pairwise';
import 'rxjs/add/operator/switchMap';

@Component({
    templateUrl: 'app/sample/view_sample.component.html',
    styleUrls: ['app/sample/view_sample.component.css']
})
export class ViewCanvasComponent implements AfterViewInit {

  loading = false;

   constructor(
     private router: Router,
     private sampleService: SampleService,
     private actionService: ActionService,
     private alertService: AlertService) { }

  @ViewChild('canvas') public canvas: ElementRef;

  @Input() public width = 800;
  @Input() public height = 800;

  private cx: CanvasRenderingContext2D;

  private sample: Sample = null
  cyberResult: Boolean = undefined;
  psychoNeuroResult: Result = new Result();


  public ngAfterViewInit() {
    const canvasEl: HTMLCanvasElement = this.canvas.nativeElement;
    this.cx = canvasEl.getContext('2d');

    canvasEl.width = this.width;
    canvasEl.height = this.height;

    this.cx.lineWidth = 3;
    this.cx.lineCap = 'round';
    this.cx.strokeStyle = '#000';

    let id = window.location.pathname.substring(15)

    console.log(this.sampleService.samples)

    this.sample = this.sampleService.samples.filter(sample => sample.id === id)[0];

    this.draw(this.sample)
  }

   psycho() {
     this.loading = true;
     this.actionService.getPsyco(this.sample)
            .subscribe(
                (result) => {
                    if (result != undefined) {
                        this.psychoNeuroResult = result
                        this.alertService.success('Processing successfuly');
                    } else {
                        this.alertService.error('Processing failed');
                        this.loading = false;
                    }
                },
                (error) => {
                        this.alertService.error('Processing failed');
                        this.loading = false;
                });
    }

   nuero() {
     this.loading = true;
     this.actionService.getMed(this.sample)
            .subscribe(
                (result) => {
                    if (result != undefined) {
                        this.psychoNeuroResult = result
                        this.alertService.success('Processing successfuly');
                    } else {
                        this.alertService.error('Processing failed');
                        this.loading = false;
                    }
                },
                (error) => {
                        this.alertService.error('Processing failed');
                        this.loading = false;
                });
    }

   auth() {
     this.loading = true;
     this.actionService.getCybetsec(this.sample)
            .subscribe(
                (result) => {
                    if (result != undefined) {
                        this.cyberResult = result
                        this.alertService.success('Processing successfuly');
                    } else {
                        this.alertService.error('Processing failed');
                        this.loading = false;
                    }
                },
                (error) => {
                        this.alertService.error('Processing failed');
                        this.loading = false;
                });
    }
  
  draw(sample: Sample) {
     for(var _i = 0; _i < sample.x.length-1; _i++) {
         this.drawOnCanvas({x: sample.x[_i], y: sample.y[_i]} , {x: sample.x[_i+1], y: sample.y[_i+1]});
     }  
  }

  private drawOnCanvas(prevPos: { x: number, y: number }, currentPos: { x: number, y: number }) {
    if (!this.cx) { return; }

    this.cx.beginPath();

    if (prevPos) {
      this.cx.moveTo(prevPos.x, prevPos.y); // from
      this.cx.lineTo(currentPos.x, currentPos.y);
      this.cx.stroke();
    }
  }
}