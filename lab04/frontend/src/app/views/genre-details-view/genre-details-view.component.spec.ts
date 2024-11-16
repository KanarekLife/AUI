import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenreDetailsViewComponent } from './genre-details-view.component';

describe('GenreDetailsViewComponent', () => {
  let component: GenreDetailsViewComponent;
  let fixture: ComponentFixture<GenreDetailsViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GenreDetailsViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GenreDetailsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
