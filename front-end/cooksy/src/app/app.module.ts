import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule} from "@ng-bootstrap/ng-bootstrap";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductComponent } from './product/product.component';
import { HttpClientModule } from "@angular/common/http";
import { RecipeComponent} from "./recipe/recipe.component";
import { LogoComponent } from "./logo/logo.component";
import { LogoutComponent } from "./logout/logout.component";
import { FooterComponent } from './footer/footer.component';
import { MenuComponent } from './menu/menu.component';
import { NavbarComponent } from './navbar/navbar.component';
import { PaginationComponent } from './pagination/pagination.component';
import { SelectComponent } from './select/select.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';
import { BackArrowComponent } from './back-arrow/back-arrow.component';
import { ToPdfComponent } from './to-pdf/to-pdf.component';
import { EmailComponent } from './email/email.component';
import { LoginComponent } from './login/login.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    RecipeComponent,
    LogoComponent,
    LogoutComponent,
    FooterComponent,
    MenuComponent,
    NavbarComponent,
    PaginationComponent,
    SelectComponent,
    LoginComponent
    ShoppingListComponent,
    BackArrowComponent,
    ToPdfComponent,
    EmailComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    NgbModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
