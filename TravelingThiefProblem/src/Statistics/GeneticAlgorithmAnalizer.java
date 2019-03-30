package Genetic_Algorithm;

import java.util.ArrayList;

class GeneticAlgorithmAnalizer {

    private String[] filenames = new String[]{"hard_0", "hard_1", "hard_2", "hard_3", "hard_4" };
    private ArrayList<String> names_for_writing_file;
    private ArrayList<Statistics> final_stats_for_linear_charts;
    private ArrayList<stats_container>  final_stats_for_column_charts;

    void startAnalizing(){
//        Dla każdego pliku wykonać te same działania
        for (String filename : filenames) {
            String file_to_read = filename + ".ttp";
            Config config = Loader.getInstance().loadData(file_to_read);

            names_for_writing_file = new ArrayList<>();
            final_stats_for_linear_charts = new ArrayList<>();
            final_stats_for_column_charts = new ArrayList<>();

//            Do 10 times GA and write statistics
            analizeAndWriteToFile(config, filename);
        }
    }

    private void analizeAndWriteToFile(Config config, String filename){
//        Testy przeprowadzane dla różnych parametrów, najpierw różne rodzaje selecki

//        itd.
//        1. Wybór seleckji

        String[] names = new String[]{"_tournament_1", "_tournament_2" ,"_tournament_3" ,"_tournament_4", "_tournament_5", "_tournament_6", "_tournament_8", "_tournament_10", "_tournament_15", "_roulette"};
        int[] hiper_params = new int[]{1, 2, 3, 4, 5, 6, 8, 10, 15};
//        bestSelectionAnalize(config, filename, names, hiper_params);

//        2. wybór krzyzowania dla turnieju
        names = new String[]{"_crossover_0.1", "_crossover_0.3", "_crossover_0.5", "_crossover_0.8", "_crossover_0.9", "_crossover_1.0"};
        float[] hiper_params_ = new float[]{0.1f, 0.3f, 0.5f, 0.8f, 0.9f, 1f};
//        bestCrossoverAnalize(config, filename, names, hiper_params_);


//        3. wybór mutowania
        names = new String[]{"_mutation_0.01", "_mutation_0.05", "_mutation_0.07", "_mutation_0.1", "_mutation_0.15", "_mutation_0.2", "_mutation_0.3", "_mutation_0.5"};
        hiper_params_ = new float[]{0.01f, 0.05f, 0.07f, 0.1f, 0.15f, 0.2f, 0.3f, 0.5f};
//        bestMutationAnalize(config, filename, names, hiper_params_);


//        4.  Wpływ rozmiaru populacji
        names = new String[]{"_pop_numb_30", "_pop_numb_60", "_pop_numb_100", "_pop_numb_150", "_pop_numb_200", "_pop_numb_300", "_pop_numb_500","_pop_numb_800", "_pop_numb_1000"};
        hiper_params = new int[]{30, 60, 100, 150, 200, 300, 500, 800, 1000};
        bestPopNumbAnalize(config, filename, names, hiper_params);

//        bestSelectionAnalize(config, filename, names, hiper_params);

//        5. badanie skrajności
//        skrajności selekcji
        names = new String[]{"_cross_0.0", "cross_0.1", "cross_1"};
        hiper_params_ = new float[]{0.0f, 0.1f, 1f};
//        bestCrossoverAnalize(config, filename, names, hiper_params_);

//

//        skrajności krzyżowania
//        names = new String[]{"_cross_0.0", "mut_0.1_cross_0.1", "_mut_0.1_cross_0.0", "_pop_numb_150", "_pop_numb_200", "_pop_numb_300", "_pop_numb_500","_pop_numb_800", "_pop_numb_1000"};
//        hiper_params_ = new float[]{30, 60, 100, 150, 200, 300, 500, 800, 1000};


    }

    private void bestPopNumbAnalize(Config config, String filename, String[] names, int[] hiper_params_) {
        ParamsOfGeneticAlgorithm params = new ParamsOfGeneticAlgorithm(150,
                0.7f,
                1,
                GeneticAlgorithm.TOURNAMENT,
                180,
                6);
        ArrayList<Statistics> list_stats;


        for(int i = 0; i < names.length; i++){
            params.setPopulation_number(hiper_params_[i]);

            list_stats = getStatsFromTenStarts(config, params);

            String filename_ = filename + names[i];
            addStatsToWrite(list_stats, filename_);
        }

        finalWrite(filename);
    }

    private void bestMutationAnalize(Config config, String filename, String[] names, float[] hiper_params_) {
        ParamsOfGeneticAlgorithm params = new ParamsOfGeneticAlgorithm(100,
                0,
                1,
                GeneticAlgorithm.TOURNAMENT,
                180,
                5);
        ArrayList<Statistics> list_stats;


        for(int i = 0; i < names.length; i++){
            params.setMutation_rate(hiper_params_[i]);

            list_stats = getStatsFromTenStarts(config, params);

            String filename_ = filename + names[i];
            addStatsToWrite(list_stats, filename_);
        }

        finalWrite(filename);
    }

    private void bestCrossoverAnalize(Config config, String filename, String[] names, float[] hiper_params) {
        ParamsOfGeneticAlgorithm params = new ParamsOfGeneticAlgorithm(100,
                0.01f,
                0,
                GeneticAlgorithm.TOURNAMENT,
                180,
                7);
        ArrayList<Statistics> list_stats;


        for(int i = 0; i < names.length; i++){
            params.setCrossover_rate(hiper_params[i]);

            list_stats = getStatsFromTenStarts(config, params);

            String filename_ = filename + names[i];
            addStatsToWrite(list_stats, filename_);
        }

        finalWrite(filename);

    }

    private void bestSelectionAnalize(Config config, String filename, String[] names, int[] hiper_params) {

        ParamsOfGeneticAlgorithm params = new ParamsOfGeneticAlgorithm(100,
                0.01f,
                0.7f,
                GeneticAlgorithm.TOURNAMENT,
                180,
                5);
        ArrayList<Statistics> list_stats;


        for(int i = 0; i < names.length; i++){
            if(i == names.length - 1)
                params.setSelection_type(GeneticAlgorithm.ROULEETE);
            else
                params.setTour(hiper_params[i]);

            list_stats = getStatsFromTenStarts(config, params);

            String filename_ = filename + names[i];
            addStatsToWrite(list_stats, filename_);
        }

        finalWrite(filename);
    }

    private void finalWrite(String filename) {

//        linear
        CSVWriter.writeStatsForLinear(filename, names_for_writing_file, final_stats_for_linear_charts);

//        column
        CSVWriter.writeStatsForCollumn(filename, names_for_writing_file, final_stats_for_column_charts);

    }


    private void addStatsToWrite(ArrayList<Statistics> list_stats, String filename) {
        names_for_writing_file.add(filename);

//        Dla Wykresów liniowych
        Statistics stats_average = calculateAverageStats(list_stats);
        final_stats_for_linear_charts.add(stats_average);

//        Dla wykresów słupokowych

        float[] fitness_av_stand_devia = calculateStandardDeviation(list_stats);
        Statistics stats_with_best_results = findBestOfAll(list_stats);

        final_stats_for_column_charts.add(new stats_container(fitness_av_stand_devia, stats_with_best_results));

    }

    private Statistics calculateAverageStats(ArrayList<Statistics> list_stats) {
        Statistics stats_average = new Statistics();

        ArrayList<Float> best_finesses_average = stats_average.getBest_fitnesses();
        ArrayList<Float> arranges_finesses_average = stats_average.getAvaranges_fitnesses();
        ArrayList<Float> worst_finesses_average = stats_average.getWorst_fitnesses();

        float average_number_bests;
        float average_number_average;
        float average_number_worsts;

        int size = list_stats.get(0).getBest_fitnesses().size();

        for(int i = 0; i < size; i++){
            average_number_bests =  calculateAverageNumberOfBestsFromListOfStatsOnPosition(list_stats, i);
            average_number_average = calculateAverageNumberOfAverageFromListOfStatsOnPosition(list_stats, i);
            average_number_worsts = calculateAverageNumberOfWorstsFromListOfStatsOnPosition(list_stats, i);

            best_finesses_average.add(average_number_bests);
            arranges_finesses_average.add(average_number_average);
            worst_finesses_average.add(average_number_worsts);
        }

        return stats_average;
    }


    private float calculateAverageNumberOfBestsFromListOfStatsOnPosition(ArrayList<Statistics> list_stats, int index) {

        float sum = 0;
        for (Statistics list_stat : list_stats) {
            sum += list_stat.getBest_fitnesses().get(index);
        }

        int size_of_population = list_stats.size();

        return sum / size_of_population;
    }

    private float calculateAverageNumberOfAverageFromListOfStatsOnPosition(ArrayList<Statistics> list_stats, int index) {
        float sum = 0;
        for (Statistics list_stat : list_stats) {
            sum += list_stat.getAvaranges_fitnesses().get(index);
        }

        int size_of_population = list_stats.size();

        return sum / size_of_population;
    }

    private float calculateAverageNumberOfWorstsFromListOfStatsOnPosition(ArrayList<Statistics> list_stats, int index) {
        float sum = 0;
        for (Statistics list_stat : list_stats) {
            sum += list_stat.getWorst_fitnesses().get(index);
        }

        int size_of_population = list_stats.size();

        return sum / size_of_population;
    }

    private ArrayList<Statistics> getStatsFromTenStarts(Config config, ParamsOfGeneticAlgorithm params) {
        ArrayList<Statistics> list_stats = new ArrayList<>();

        Statistics stats;
        for(int i = 0; i < 10; i++){
            GeneticAlgorithm ga = new GeneticAlgorithm(config,
                    params.getPopulation_number(),
                    params.getMutation_rate(),
                    params.getCrossover_rate(),
                    params.getSelection_type(),
                    params.getNumber_of_generation(),
                    params.getTour());
            stats = ga.start();
            list_stats.add(stats);
        }

        return list_stats;
    }

    private float[] calculateStandardDeviation( ArrayList<Statistics> list_stats ){

        float sum_best = 0;
        float sum_worst = 0;
        for(Statistics stats: list_stats){
            sum_best += stats.getBestOfAllThief().getFitness();
            sum_worst += stats.getWorst_of_all().getFitness();
        }

        int size = list_stats.size();
        float average_best = sum_best / size;
        float average_worst = sum_worst / size;

        float nominator_best = 0;

        for(Statistics stats: list_stats){
            nominator_best += Math.pow(stats.getBestOfAllThief().getFitness() - average_best, 2);
        }

        float nominator_worst = 0;
        for(Statistics stats: list_stats){
            nominator_worst += Math.pow(stats.getBestOfAllThief().getFitness() - average_best, 2);
        }

        return new float[]{average_best,(float) Math.sqrt(nominator_best/size), average_worst, (float) Math.sqrt(nominator_worst/size)};
    }

    private Statistics findBestOfAll(ArrayList<Statistics> list_stats){

        Statistics best_stats = list_stats.get(0);

        for(Statistics stats: list_stats){
            if(best_stats.getBestOfAllThief().getFitness() < stats.getBestOfAllThief().getFitness())
                best_stats = stats;
        }

        return best_stats;
    }

    private class ParamsOfGeneticAlgorithm {
        int population_number;
        float mutation_rate;
        float crossover_rate;
        int selection_type;
        int number_of_generation;
        int tour;

        ParamsOfGeneticAlgorithm(int population_number, float mutation_rate, float crossover_rate, int selecion_type, int number_of_generation, int tour) {
            this.population_number = population_number;
            this.mutation_rate = mutation_rate;
            this.crossover_rate = crossover_rate;
            this.selection_type = selecion_type;
            this.number_of_generation = number_of_generation;
            this.tour = tour;
        }

        int getPopulation_number() {
            return population_number;
        }

        float getMutation_rate() {
            return mutation_rate;
        }

        float getCrossover_rate() {
            return crossover_rate;
        }

        int getSelection_type() {
            return selection_type;
        }

        int getNumber_of_generation() {
            return number_of_generation;
        }

        int getTour() {
            return tour;
        }

        public void setPopulation_number(int population_number) {
            this.population_number = population_number;
        }

        public void setMutation_rate(float mutation_rate) {
            this.mutation_rate = mutation_rate;
        }

        public void setCrossover_rate(float crossover_rate) {
            this.crossover_rate = crossover_rate;
        }

        public void setSelection_type(int selection_type) {
            this.selection_type = selection_type;
        }

        public void setNumber_of_generation(int number_of_generation) {
            this.number_of_generation = number_of_generation;
        }

        public void setTour(int tour) {
            this.tour = tour;
        }
    }

    class stats_container{
        float[] fitness_av_stand_devia_list;
        Statistics stats_with_best_results_list;

        stats_container(float[] fitness_av_stand_devia_list, Statistics stats_with_best_results_list) {
            this.fitness_av_stand_devia_list = fitness_av_stand_devia_list;
            this.stats_with_best_results_list = stats_with_best_results_list;
        }

        public float[] getFitness_av_stand_devia_list() {
            return fitness_av_stand_devia_list;
        }

        public Statistics getStats_with_best_results_list() {
            return stats_with_best_results_list;
        }
    }
}
