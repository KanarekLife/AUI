import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenresListViewComponent } from './genres-list-view.component';

describe('GenresListViewComponent', () => {
  let component: GenresListViewComponent;
  let fixture: ComponentFixture<GenresListViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GenresListViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GenresListViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
