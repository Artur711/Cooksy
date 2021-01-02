import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductComponent } from './product/product.component';
import { HttpClient, HttpClientModule } from "@angular/common/http";
import { RecipeComponent} from "./recipe/recipe.component";
import { LogoComponent } from "./logo/logo.component";
import { LogoutComponent } from "./logout/logout.component";
import { FooterComponent } from './footer/footer.component';
import { MenuComponent } from './menu/menu.component';
import { NavbarComponent } from './navbar/navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    RecipeComponent,
    LogoComponent,
    LogoutComponent,
    FooterComponent,
    MenuComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
