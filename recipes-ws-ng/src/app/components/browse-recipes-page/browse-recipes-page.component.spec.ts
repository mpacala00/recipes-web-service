import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseRecipesPageComponent } from './browse-recipes-page.component';

describe('BrowseRecipesPageComponent', () => {
  let component: BrowseRecipesPageComponent;
  let fixture: ComponentFixture<BrowseRecipesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BrowseRecipesPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BrowseRecipesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
