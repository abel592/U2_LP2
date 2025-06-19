import {Component, inject, OnInit} from '@angular/core';
import {MaterialModule} from "../../material/material.module";
import {RouterLink, RouterLinkActive, RouterOutlet} from "@angular/router";

//import {ThemeService} from '../../service/theme.service';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [MaterialModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})

export class LayoutComponent implements OnInit{
  //themeService=inject(ThemeService);
  constructor(

  ){}

  logout(){
  }

 ngOnInit(): void {
 }

}
