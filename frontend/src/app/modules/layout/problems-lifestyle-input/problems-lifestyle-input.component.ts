import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-problems-lifestyle-input',
  templateUrl: './problems-lifestyle-input.component.html',
  styleUrl: './problems-lifestyle-input.component.css'
})
export class ProblemsLifestyleInputComponent implements OnInit {
  habitsForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.habitsForm = this.formBuilder.group({
      PROBLEM_ACNE: false,
      PROBLEM_SPOTS: false,
      PROBLEM_SCARS: false,
      PROBLEM_ENLARGED_PORES: false,
      PROBLEM_REDNESS: false,
      PROBLEM_ITCHINESS: false,
      PROBLEM_WRINKLES: false,
      PROBLEM_FRECKLES: false,
      PROBLEM_BLACKHEADS: false,
      PROBLEM_LACK_OF_FIRMNESS: false,
      PROBLEM_ECZEMA: false,
      HABIT_UNHEALTHY_DIET: false,
      HABIT_SMOKING: false,
      HABIT_ALCOHOL_CONSUMPTION: false,
      HABIT_SUN_EXPOSURE: false,
      HABIT_LACK_OF_SLEEP: false,
      HABIT_STRESS: false,
      HABIT_INTENSE_PHYSICAL_ACTIVITY: false,
      HABIT_HORMONAL_CHANGES: false,
    });
  }

  submitForm(): void {
    const selectedHabits = this.habitsForm.value;
    const selectedHabitKeys = Object.keys(selectedHabits).filter(key => key.startsWith('HABIT_'));
    const habitsSelected = selectedHabitKeys.some(key => selectedHabits[key]);
  
    // Provera da li su selektovani problemi
    const selectedProblemKeys = Object.keys(selectedHabits).filter(key => key.startsWith('PROBLEM_'));
    const problemsSelected = selectedProblemKeys.some(key => selectedHabits[key]);

    const habitsWithoutPrefix = selectedHabitKeys.map(key => selectedHabits[key] ? key.substring(6) : null).filter((value): value is string => value !== null); // Uklanja 'HABIT_' samo ako je vrednost true
    const problemsWithoutPrefix = selectedProblemKeys.map(key => selectedHabits[key] ? key.substring(8) : null).filter((value): value is string => value !== null); // Uklanja 'PROBLEM_' samo ako je vrednost true

    const problemsLifestyleDTO: ProblemsLifestyleDTO = {
      skinProblems: problemsWithoutPrefix,
      lifestyleHabits: habitsWithoutPrefix
    };

    console.log(problemsLifestyleDTO);



    this.router.navigate(['/recommendation-process-second'], { state: { problemsLifestyleDTO } });
  }
}


export interface ProblemsLifestyleDTO{
  skinProblems: string[];
  lifestyleHabits: string[];
}