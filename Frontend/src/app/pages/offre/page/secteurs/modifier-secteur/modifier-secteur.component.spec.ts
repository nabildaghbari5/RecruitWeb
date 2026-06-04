import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierSecteurComponent } from './modifier-secteur.component';

describe('ModifierSecteurComponent', () => {
  let component: ModifierSecteurComponent;
  let fixture: ComponentFixture<ModifierSecteurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModifierSecteurComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ModifierSecteurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
