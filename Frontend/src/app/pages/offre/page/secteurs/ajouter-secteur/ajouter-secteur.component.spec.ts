import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjouterSecteurComponent } from './ajouter-secteur.component';

describe('AjouterSecteurComponent', () => {
  let component: AjouterSecteurComponent;
  let fixture: ComponentFixture<AjouterSecteurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AjouterSecteurComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AjouterSecteurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
