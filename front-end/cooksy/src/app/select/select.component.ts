import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  collapsed = true;
  meatIsCollapsed = true;
  vegetablesIsCollapsed = true;
  additionalIsCollapsed = true;
  promotionIsCollapsed = true;

  constructor() { }

  ngOnInit(): void { }

  meatCollapsed(): void {
    this.collapsedAll(this.meatIsCollapsed);
    this.meatIsCollapsed = !this.collapsed;
  }

  vegetablesCollapsed(): void {
    this.collapsedAll(this.vegetablesIsCollapsed);
    this.vegetablesIsCollapsed = !this.collapsed;
  }

  additionalCollapsed(): void {
    this.collapsedAll(this.additionalIsCollapsed);
    this.additionalIsCollapsed = !this.collapsed;
  }

  promotionCollapsed(): void {
    this.collapsedAll(this.promotionIsCollapsed);
    this.promotionIsCollapsed = !this.collapsed;
  }

  private collapsedAll(collapsed: boolean): void {
    this.collapsed = collapsed;
    this.meatIsCollapsed = true;
    this.vegetablesIsCollapsed = true;
    this.additionalIsCollapsed = true;
    this.promotionIsCollapsed = true;
  }
}
