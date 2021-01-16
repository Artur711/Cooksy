import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule} from '@ng-bootstrap/ng-bootstrap';
// @ts-ignore
import { NgxPaginationModule} from "ngx-pagination";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductComponent } from './product/product.component';
import { HttpClientModule } from "@angular/common/http";
import { RecipeComponent} from "./recipe/recipe.component";
import { MenuComponent } from './menu/menu.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SelectComponent } from './select/select.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RecipeDetailsComponent } from './recipe-details/recipe-details.component';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';
import { BackArrowComponent } from './back-arrow/back-arrow.component';
import { ToPdfComponent } from './to-pdf/to-pdf.component';
import { EmailComponent } from './email/email.component';
import { LoginComponent } from './login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { FrontPageComponent } from './front-page/front-page.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { FavoritesComponent } from './favorites/favorites.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    RecipeComponent,
    MenuComponent,
    NavbarComponent,
    SelectComponent,
    RecipeDetailsComponent,
    LoginComponent,
    ShoppingListComponent,
    BackArrowComponent,
    ToPdfComponent,
    EmailComponent,
    LoginComponent,
    FrontPageComponent,
    RegisterComponent,
    HomeComponent,
    FavoritesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    NgbModule,
    NgxPaginationModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
