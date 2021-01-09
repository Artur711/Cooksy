import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  meatIsCollapsed = true;
  vegetablesIsCollapsed = true;
  additionalIsCollapsed = true;
  promotionIsCollapsed = true;

  constructor() { }

  ngOnInit(): void {
  }

  meatCollapsed(): void {
    this.meatIsCollapsed = !this.meatIsCollapsed;
    this.vegetablesIsCollapsed = true;
    this.additionalIsCollapsed = true;
    this.promotionIsCollapsed = true;
  }

  vegetablesCollapsed(): void {
    this.meatIsCollapsed = true;
    this.vegetablesIsCollapsed = !this.vegetablesIsCollapsed;
    this.additionalIsCollapsed = true;
    this.promotionIsCollapsed = true;
  }

  additionalCollapsed(): void {
    this.meatIsCollapsed = true;
    this.vegetablesIsCollapsed = true;
    this.additionalIsCollapsed = !this.additionalIsCollapsed;
    this.promotionIsCollapsed = true;
  }

  promotionCollapsed(): void {
    this.meatIsCollapsed = true;
    this.vegetablesIsCollapsed = true;
    this.additionalIsCollapsed = true;
    this.promotionIsCollapsed = !this.promotionIsCollapsed;
  }
}
