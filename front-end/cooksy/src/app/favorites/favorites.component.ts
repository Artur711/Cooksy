import { Component, OnInit } from '@angular/core';
import {FavoritesService} from "../service/favorites.service";
import {FavoriteDto} from "../model/dto";

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit {
  favorites: FavoriteDto[] = [];
  totalRecords = 1;
  page = 1;

  constructor(private favoritesService: FavoritesService) { }

  ngOnInit(): void {
    this.favoritesService.getFavorites$()
      .subscribe(favorites => {
        this.favorites = favorites;
        this.totalRecords = favorites.length;
      }
    )
  }
}
