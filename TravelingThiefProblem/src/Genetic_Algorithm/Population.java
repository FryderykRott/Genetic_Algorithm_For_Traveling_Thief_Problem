package Genetic_Algorithm;

import java.util.ArrayList;


class Population {

    private Config config;

    private int dim;

    private int population_number;
    private int knapsack_cap;
    private ArrayList<City> cities;
    private Float[][] dist_matrix;
    private float v_max;
    private float v_min;

    private ArrayList<Thief> thieves;

    private float mutation_rate;
    private float crossover_rate;

    Population(Config config, int population_number, float mutation_rate, float crossover_rate){
        initilaze(config, population_number, mutation_rate, crossover_rate);
    }

    Population(Population population){
        initilaze(population.config, population.population_number, population.mutation_rate, population.crossover_rate);
    }

    private void initilaze(Config config, int population_number, float mutation_rate, float crossover_rate){
        this.config = config;
        this.dim = config.getDim();
        this.population_number = population_number;
        this.knapsack_cap = config.getKnapsack_cap();
        this.cities = config.getCities();
        this.dist_matrix = config.getDist_matrix();
        this.v_max = config.getV_max();
        this.v_min = config.getV_min();

        this.mutation_rate = mutation_rate;
        this.crossover_rate = crossover_rate;

        thieves = new ArrayList<>(dim);
    }

    void ganarateRandomPopulation(){
        for(int i = 0; i < population_number; i++)
            thieves.add(new Thief(this));
    }

    void mutatePopulation(){
        for(Thief thief: thieves){
            if(Math.random() <= mutation_rate)
                thief.mutate();
        }
    }

    void crossoverPopulation(){

        Thief thief;
        Thief thief2;
        double rate = 0;
        for (int i = 0; i < thieves.size() - 1; i = i+2) {
            rate = Math.random();
            if ( rate <= crossover_rate) {

                thief = thieves.get(i);
                thief2 = thieves.get(i+1);

                thief.crossover(thief2);
            }
        }

        if(thieves.size()%2 == 1){
            thief = thieves.get(thieves.size());
            thief2 = thieves.get(0);

            thief.crossover(thief2);
        }
    }

    Float[][] getDist_matrix() {
        return dist_matrix;
    }

    int getDim() {
        return dim;
    }

    int getKnapsack_cap() {
        return knapsack_cap;
    }

    ArrayList<City> getCities() {
        return cities;
    }

    float getVMax() {
        return v_max;
    }

    float getVMin() {
        return v_min;
    }

    ArrayList<Thief> getThieves() {
        return thieves;
    }

    public Config getConfig() {
        return config;
    }

    public int getPopulation_number() {
        return population_number;
    }

}
