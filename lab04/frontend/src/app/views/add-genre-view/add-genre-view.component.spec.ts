import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddGenreViewComponent } from './add-genre-view.component';

describe('AddGenreViewComponent', () => {
  let component: AddGenreViewComponent;
  let fixture: ComponentFixture<AddGenreViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddGenreViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddGenreViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
