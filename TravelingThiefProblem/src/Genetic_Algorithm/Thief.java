package Genetic_Algorithm;

import java.util.ArrayList;
import java.util.Collections;

class Thief {

    private ArrayList<Integer> road_plan;
    private Population population_parent;
    private Knapsack knapsack;

    private float fitness;

    Thief(Population population_parent){
        this.population_parent = population_parent;
        generateRandomRoad(population_parent.getDim());

        initiate();
    }

    Thief(Thief thief){
        this.population_parent = thief.population_parent;
        road_plan = new ArrayList<>(thief.road_plan);

        initiate();
    }

    private void initiate(){
        knapsack = new Knapsack(population_parent.getKnapsack_cap());
        calculateFitnessFunction();
    }

    private void generateRandomRoad(int dim){
        road_plan = new ArrayList<>(dim);

        for(int i = 0; i < dim; i++){
            road_plan.add(i);
        }
        Collections.shuffle(road_plan);
    }

     void calculateFitnessFunction(){

        knapsack.clearKnapsack();

        Float v_max = population_parent.getVMax();
        Float v_min = population_parent.getVMin();
        ArrayList<City> cities = population_parent.getCities();

        int total_time = stealingTime(v_max, v_min, cities);

        fitness = knapsack.getKnapsackProfit() - total_time;
    }

    //      Algorytm chciwy do znalezienia przedmiotów w miastach
    private int stealingTime(float v_max, float v_min, ArrayList<City> cities){

        float total_time = 0;
//        printRoadPlan();
        Integer current_city_index;
        Integer previous_city_index;

        for (int i = 1; i < road_plan.size(); i++) {

            current_city_index = road_plan.get(i);
            previous_city_index = road_plan.get(i - 1);

            if(i-1 != 0)
                pickUpItemFromCity(previous_city_index, cities);

            total_time += calculateTime(v_max, v_min, previous_city_index, current_city_index);
        }

        int last_city = road_plan.size() - 1;

        total_time += calculateTime(v_max, v_min,road_plan.get(0), road_plan.get(last_city));
        pickUpItemFromCity( road_plan.get(0), cities);

        return (int) Math.ceil((double)total_time);

    }

    private float calculateTime(float v_max, float v_min, Integer previous_city_index, Integer current_city_index) {

        float current_v = v_max - (knapsack.getKnapsackWeight()) * (v_max - v_min) / knapsack.getKnapsack_cap();
        float distance = calculateDistance(previous_city_index, current_city_index);

        return distance / current_v;
    }

    private void pickUpItemFromCity(Integer current_city_index, ArrayList<City> cities ) {

        City current_city = cities.get(current_city_index);
        current_city.clear();

        Item next_Item = current_city.getNextItem();

        while ( !knapsack.addItem(next_Item)){
            next_Item = current_city.getNextItem();

            if(next_Item == null){
                return;
            }

        }

    }

    private float calculateDistance(int index_from, int index_to){
        return population_parent.getDist_matrix()[index_from][index_to];
    }


    void mutate(){
        int size = road_plan.size();
        int random_index_1 = (int) (Math.random() * size);
        int random_index_2 = (int) (Math.random() * size);

        while (random_index_1 == random_index_2)
            random_index_2 = (int) (Math.random() * size);

        swap(random_index_1, random_index_2);


    }

    private void swap(int index_1, int index_2){
        int temporary_holder = road_plan.get(index_1);
        road_plan.set(index_1, road_plan.get(index_2));
        road_plan.set(index_2, temporary_holder);
    }

    void crossover(Thief thief){
        int size = road_plan.size();
        int number1 = (int) (Math.random() * size);
        int number2 = (int) (Math.random() * size);

        final int start = Math.min(number1, number2);
        final int end = Math.max(number1, number2);

        final ArrayList<Integer> child1 = new ArrayList<>(road_plan.subList(start, end));
        final ArrayList<Integer> child2 = new ArrayList<>(thief.getRoadPlan().subList(start, end));

        int currentCityIndex = 0;
        int currentCityInTour1 = 0;
        int currentCityInTour2 = 0;

        for (int i = 0; i < size; i++) {
            currentCityIndex = (end + i) % size;

            currentCityInTour1 = road_plan.get(currentCityIndex);
            currentCityInTour2 = thief.getRoadPlan().get(currentCityIndex);

            if (!child1.contains(currentCityInTour2)) {
                child1.add(currentCityInTour2);
            }

            if (!child2.contains(currentCityInTour1)) {
                child2.add(currentCityInTour1);
            }

        }

        road_plan = child1;
        thief.road_plan = child2;

    }

    ArrayList<Integer> getRoadPlan() {
        return road_plan;
    }

    void printRoadPlan(){
        for (Integer integer : road_plan) {
            System.out.print(integer);
        }
    }

    float getFitness() {
        return fitness;
    }

    void setFitness(float fitness) {
        this.fitness = fitness;
    }
}
