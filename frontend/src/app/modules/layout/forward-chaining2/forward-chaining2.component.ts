import { Component, Input } from '@angular/core';
import { ProductDTO } from '../../../dto/Product';
import { ProblemsLifestyleDTO } from '../problems-lifestyle-input/problems-lifestyle-input.component';
import { UserService } from '../../user/user.service';
import { RecommendationService } from '../../../service/recommendation.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-forward-chaining2',
  templateUrl: './forward-chaining2.component.html',
  styleUrl: './forward-chaining2.component.css'
})
export class ForwardChaining2Component {

  @Input() problemsLifestyleDTO: ProblemsLifestyleDTO | undefined;

  skinProblems: string[] = [];
  lifestyleHabits: string[] = [];
  allergens: string[] = [];

  constructor(private userService: UserService, private recommendationsService: RecommendationService){}

  loading = true;
  loadingMessage = 'Getting ready to make most suitable recommendations...';
  recommendations: ProductDTO[] = [];

  ngOnInit() {
    const state = history.state;
    const problemsLifestyleDTO = state.problemsLifestyleDTO;
    this.problemsLifestyleDTO = problemsLifestyleDTO;

    if (problemsLifestyleDTO) {
      this.skinProblems = (problemsLifestyleDTO.skinProblems || []).map((problem: string) => this.formatEnumString(problem));
      this.lifestyleHabits = (problemsLifestyleDTO.lifestyleHabits || []).map((habit: string) => this.formatEnumString(habit));
      this.userService.getUserAllergens().subscribe((allergens) =>{
       this.allergens = allergens;
       });
    }
    this.simulateProcess();
  }

  simulateProcess() {
    setTimeout(() => {
      this.loadingMessage = 'Making recommendations based on your skin problem(s)...';
      setTimeout(() => {
        this.loadingMessage = 'Filtering out products that contain your allergens...';
        setTimeout(() => {
          this.loadingMessage = 'Calculating based on your lifestyle habit(s)...';
          setTimeout(() => {
            this.loading = false;
            this.generateRecommendations();
          }, 1000);
        }, 1000);
      }, 1000);
    }, 1000);
  }

  generateRecommendations() {
    if (!this.problemsLifestyleDTO) {
      console.error('ProblemsLifestyleDTO is not defined');
      this.problemsLifestyleDTO = {
        skinProblems: [],
        lifestyleHabits: []
      };
    }
    this.recommendationsService.recommendProductsBasedOnSkinProblemsAndHabits(this.problemsLifestyleDTO).subscribe({
      next: (response) =>{
        console.log(response);
        this.recommendations = response;
      },
      error: (error) => {
        if (error instanceof HttpErrorResponse){
          console.log(error.error)
        }
      }, 

    })
  }

  private formatEnumString(enumString: string): string {
    return enumString.toLowerCase().replace(/_/g, ' ').replace(/\b\w/g, char => char.toUpperCase());
  }

}
