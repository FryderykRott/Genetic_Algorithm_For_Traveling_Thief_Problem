package Genetic_Algorithm;
import java.util.ArrayList;

class GeneticAlgorithm {

    static final int TOURNAMENT = 0;
    static final int ROULEETE = 1;

    private Population population;

    private int population_number;
    private float mutation_rate;
    private float crossover_rate;
    private int SELECTION_TYPE;
    private int generations;

    private Config config;
    private Statistics statistics;

    private int tour;

    //    parametr tour moze byc null jeśli typ selekcji to ruletka
    GeneticAlgorithm(Config config, int population_number, float mutation_rate, float crossover_rate, int SELECTION_TYPE, int generations, int tour){
        this.config = config;
        this.population_number = population_number;
        this.mutation_rate = mutation_rate;
        this.crossover_rate = crossover_rate;
        this.SELECTION_TYPE = SELECTION_TYPE;
        this.generations = generations;
        this.tour = tour;
        statistics = new Statistics();
    }

    Statistics start(){
        int current_generation = 0;

        initiate();
        while ( populationAssessment(current_generation) ){

            selection();
            population.crossoverPopulation();
            population.mutatePopulation();

            printStatistics(Integer.toString(current_generation));
            current_generation++;
        }

        return statistics;
    }

    private void selection(){
        if(SELECTION_TYPE == TOURNAMENT)
            population = tournamentSelection(tour);
        else if(SELECTION_TYPE == ROULEETE)
            population = rouletteSelection();
    }
    private void initiate(){
        population = new Population(config, population_number,  mutation_rate, crossover_rate);
        population.ganarateRandomPopulation();
    }

    private Population tournamentSelection(int tour) {
        Population new_population = new Population(population);

        ArrayList<Integer> indexes = new ArrayList<>();

        Thief best_thief;
        Thief new_thief;

        while(new_population.getThieves().size() != population_number){
            generateRandomIndexes(indexes, tour);
            best_thief = findBestThief(indexes);

            new_thief = new Thief(best_thief);

            new_population.getThieves().add(new_thief);
        }

        return new_population;
    }


    private void generateRandomIndexes(ArrayList<Integer> indexes, int tour){
        if(!indexes.isEmpty())
            indexes.clear();

        int index;
        for(int i = 0; i < tour; i++){
            index = (int) (Math.random() * population_number);
            indexes.add(index);
        }
    }

    private Thief findBestThief(ArrayList<Integer> indexes) {

        int best_thief_index = indexes.get(0);
        float best_thief_fitness = population.getThieves().get(best_thief_index).getFitness();

        int current_thief_index = 0;
        float current_thief_fitness = 0f;

        for(int i = 1; i < indexes.size(); i++){
            current_thief_index = indexes.get(i);
            current_thief_fitness = population.getThieves().get(current_thief_index).getFitness();

            if( best_thief_fitness < current_thief_fitness){
                best_thief_index = current_thief_index;
                best_thief_fitness = current_thief_fitness;
            }
        }

        return population.getThieves().get(best_thief_index);
    }

    private Population rouletteSelection(){

        Population new_population = new Population(population);

        shiftFitnessByWorstFitness();

        int sum = 0;
        for(Thief thief: population.getThieves()){
            sum += thief.getFitness();
        }

        Thief selected_thief;
        while(new_population.getThieves().size() != population_number){
            selected_thief = selectMemberUsingRouletteWheel(sum);

            new_population.getThieves().add(new Thief(selected_thief));
        }

        return new_population;
    }

    private void shiftFitnessByWorstFitness() {
        float worst_fitness = population.getThieves().get(0).getFitness();
        for (Thief thief: population.getThieves())
            if(worst_fitness > thief.getFitness())
                worst_fitness = thief.getFitness();

        worst_fitness = Math.abs(worst_fitness);

        for (Thief thief: population.getThieves())
            thief.setFitness(thief.getFitness() + worst_fitness);
    }

    private Thief selectMemberUsingRouletteWheel(int sum){

        float rnd = (float) ( Math.random() * sum );
        float partial_sum = 0;

        for(Thief thief: population.getThieves()){
            partial_sum += thief.getFitness();
            if( partial_sum >= rnd)
                return thief;
        }

        return null;
    }

    private void calculateFitnessOfPopulation(Population population){
        for (Thief thief: population.getThieves())
            thief.calculateFitnessFunction();

    }
    private boolean populationAssessment(int current_generation){
        calculateFitnessOfPopulation(population);
        if(current_generation != 0){
            statistics.performStatistics(population);
        }

        return current_generation < generations;
    }

    private void printStatistics(String gen){
        if(Integer.parseInt(gen) != 0)
        System.out.println(gen + ": TheBest: " + (int) statistics.best_of_all.getFitness()
                + " , CurBest: " + statistics.getBest_fitnesses().get(statistics.getBest_fitnesses().size()-1).intValue());
    }

}
