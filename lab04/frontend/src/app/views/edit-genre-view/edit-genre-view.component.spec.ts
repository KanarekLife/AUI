import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditGenreViewComponent } from './edit-genre-view.component';

describe('EditGenreViewComponent', () => {
  let component: EditGenreViewComponent;
  let fixture: ComponentFixture<EditGenreViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditGenreViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditGenreViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
