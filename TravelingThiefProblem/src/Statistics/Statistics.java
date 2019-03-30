package Genetic_Algorithm;

import java.util.ArrayList;

class Statistics {
    Thief best_of_all;
    Thief worst_of_all;

    private ArrayList<Float> best_fitnesses;
    private ArrayList<Float> avaranges_fitnesses;
    private ArrayList<Float> worst_fitnesses;

    Statistics(){
        best_fitnesses = new ArrayList<>();
        avaranges_fitnesses = new ArrayList<>();
        worst_fitnesses = new ArrayList<>();
    }

    void performStatistics(Population population){

        Thief current_best = population.getThieves().get(0);
        Thief current_worst = population.getThieves().get(0);
        float fitness_sum = 0;

        for(Thief thief: population.getThieves()){

            if(current_best.getFitness() < thief.getFitness())
                current_best = thief;

            if(current_worst.getFitness() > thief.getFitness())
                current_worst = thief;

            fitness_sum +=thief.getFitness();

        }

        best_fitnesses.add(current_best.getFitness());

        float avarange_fitness = fitness_sum/population.getPopulation_number();

        avaranges_fitnesses.add(avarange_fitness);

        worst_fitnesses.add(current_worst.getFitness());


        if(best_of_all == null)
            best_of_all = new Thief(current_best);

        if(best_of_all.getFitness() < current_best.getFitness())
            best_of_all = new Thief(current_best);

        if(worst_of_all == null)
            worst_of_all = new Thief(current_worst);

        if(worst_of_all.getFitness() > current_worst.getFitness())
            worst_of_all = new Thief(current_worst);
    }

    ArrayList<Float> getBest_fitnesses() {
        return best_fitnesses;
    }

    ArrayList<Float> getAvaranges_fitnesses() {
        return avaranges_fitnesses;
    }

    ArrayList<Float> getWorst_fitnesses() {
        return worst_fitnesses;
    }

//    Wykorzystywany przez algorytm selekcji ruletki
    float getCurrentWorstFitness(){
        return worst_fitnesses.get(worst_fitnesses.size() - 1);
    }

    Thief getBestOfAllThief(){
        return best_of_all;
    }

    Thief getWorst_of_all() {
        return worst_of_all;
    }

}
