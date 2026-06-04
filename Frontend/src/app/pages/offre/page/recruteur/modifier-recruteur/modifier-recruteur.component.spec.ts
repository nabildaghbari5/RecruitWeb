import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierRecruteurComponent } from './modifier-recruteur.component';

describe('ModifierRecruteurComponent', () => {
  let component: ModifierRecruteurComponent;
  let fixture: ComponentFixture<ModifierRecruteurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModifierRecruteurComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ModifierRecruteurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
