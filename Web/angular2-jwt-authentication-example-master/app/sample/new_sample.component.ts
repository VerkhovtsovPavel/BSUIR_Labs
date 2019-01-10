import {
  Component, Input, ElementRef, AfterViewInit, ViewChild
} from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';

import { SampleService } from '../services/sample.service';
import { Sample } from '../models/sample';
import { AlertService } from '../services/alert.service';

import 'rxjs/add/observable/fromEvent';
import 'rxjs/add/operator/takeUntil';
import 'rxjs/add/operator/pairwise';
import 'rxjs/add/operator/switchMap';

@Component({
    templateUrl: 'app/sample/new_sample.component.html',
    styleUrls: ['app/sample/new_sample.component.css']
})
export class CanvasComponent implements AfterViewInit {

  loading = false;

   constructor(
     private router: Router,
     private sampleService: SampleService,
     private alertService: AlertService) { }

  @ViewChild('canvas') public canvas: ElementRef;

  @Input() public width = 800;
  @Input() public height = 800;

  private cx: CanvasRenderingContext2D;

  private sample: Sample = new Sample();

  public ngAfterViewInit() {
    const canvasEl: HTMLCanvasElement = this.canvas.nativeElement;
    this.cx = canvasEl.getContext('2d');

    canvasEl.width = this.width;
    canvasEl.height = this.height;

    this.cx.lineWidth = 3;
    this.cx.lineCap = 'round';
    this.cx.strokeStyle = '#000';

    this.captureEvents(canvasEl);
  }

  clear() {
       this.cx.clearRect(0, 0, this.cx.canvas.width, this.cx.canvas.height);
       this.sample = new Sample();
  }

   save() {
     this.loading = true;
     this.sampleService.addSample(this.sample)
            .subscribe(
                (result) => {
                    if (result != undefined) {
                        this.router.navigate(['/home']);
                        this.alertService.success('Sample saved successfuly');
                    } else {
                        this.alertService.error('Saving failed');
                        this.loading = false;
                    }
                },
                (error) => {
                        this.alertService.error('Saving failed');
                        this.loading = false;
                });
    }
  
  private captureEvents(canvasEl: HTMLCanvasElement) {
    Observable
      .fromEvent(canvasEl, 'mousedown')
      .switchMap((e) => {
        return Observable
          .fromEvent(canvasEl, 'mousemove')
          .takeUntil(Observable.fromEvent(canvasEl, 'mouseup'))
          .pairwise()
      })
      .subscribe(
        (res: [MouseEvent, MouseEvent]) => {
          const rect = canvasEl.getBoundingClientRect();
    
          const prevPos = {
            x: res[0].clientX - rect.left,
            y: res[0].clientY - rect.top
          };
    
          const currentPos = {
            x: res[1].clientX - rect.left,
            y: res[1].clientY - rect.top
          };

          this.sample.xs.push(currentPos.x)
          this.sample.ys.push(currentPos.y)
          this.sample.es.push(0)
    
          this.drawOnCanvas(prevPos, currentPos);
      },
      (error) => {},
      () => {this.sample.es.push(1)});
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