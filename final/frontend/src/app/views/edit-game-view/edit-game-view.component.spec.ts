import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditGameViewComponent } from './edit-game-view.component';

describe('EditGameViewComponent', () => {
  let component: EditGameViewComponent;
  let fixture: ComponentFixture<EditGameViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditGameViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditGameViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
