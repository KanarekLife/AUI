import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddGameViewComponent } from './add-game-view.component';

describe('AddGameViewComponent', () => {
  let component: AddGameViewComponent;
  let fixture: ComponentFixture<AddGameViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddGameViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddGameViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
