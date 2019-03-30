package Genetic_Algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

class CSVWriter {

    static void writeRoad(Config config, ArrayList<Integer> road, String filename) {

        try (PrintWriter writer = new PrintWriter(new File(filename + "_Stats_.csv"))) {

            StringBuilder stringBuilder = new StringBuilder();
            DecimalFormat formatter = new DecimalFormat("#.00", DecimalFormatSymbols.getInstance(Locale.ITALY));
            stringBuilder.append("id");
            stringBuilder.append(';');
            stringBuilder.append("x");
            stringBuilder.append(';');
            stringBuilder.append("y");
            stringBuilder.append('\n');

            ArrayList<City> cities = config.getCities();


            City current_city;
            for (Integer integer : road) {
                current_city = cities.get(integer);

                stringBuilder.append(integer);
                stringBuilder.append(';');
                stringBuilder.append(formatter.format(current_city.getX_coords()));
                stringBuilder.append(';');
                stringBuilder.append(formatter.format(current_city.getY_coords()));
                stringBuilder.append('\n');
            }

            writer.write(stringBuilder.toString());
            System.out.println("done: " + filename);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static void writeStatsForCollumn(String filename, ArrayList<String> names_for_writing_file, ArrayList<GeneticAlgorithmAnalizer.stats_container> stats) {

        try (PrintWriter writer = new PrintWriter(new File(filename + "_Column.csv"))) {

            StringBuilder stringBuilder = new StringBuilder();
            DecimalFormat formatter = new DecimalFormat("#.00", DecimalFormatSymbols.getInstance(Locale.ITALY));

            //            teraz dla ułatwienia w odpowiedniej kolejnosci i ilosci tytuły

            for (String s : names_for_writing_file) {
                stringBuilder.append(';');
                stringBuilder.append(';');
                stringBuilder.append(s);
                stringBuilder.append(';');
            }
            stringBuilder.append('\n');
            stringBuilder.append('\n');

            stringBuilder.append("Best Of All");
            stringBuilder.append(';');
            stringBuilder.append("Aver Best");
            stringBuilder.append(';');
//                stringBuilder.append("Standard Devation Best");
//                stringBuilder.append(';');
            stringBuilder.append("Aver Worst");
            stringBuilder.append(';');
//                stringBuilder.append("Standard Devation Worst");
//                stringBuilder.append(';');
            stringBuilder.append(';');
            stringBuilder.append('\n');

            for (GeneticAlgorithmAnalizer.stats_container stat : stats) {
                stringBuilder.append(formatter.format(stat.getStats_with_best_results_list().getBestOfAllThief().getFitness()));
                stringBuilder.append(';');
                stringBuilder.append(formatter.format(stat.getFitness_av_stand_devia_list()[0]));
                stringBuilder.append(';');
//                stringBuilder.append(formatter.format(stat.getFitness_av_stand_devia_list()[1]));
//                stringBuilder.append(';');
                stringBuilder.append(formatter.format(stat.getFitness_av_stand_devia_list()[2]));
                stringBuilder.append(';');
//                stringBuilder.append(formatter.format(stat.getFitness_av_stand_devia_list()[3]));
//                stringBuilder.append(';');
                stringBuilder.append(';');

                stringBuilder.append('\n');
            }

            writer.write(stringBuilder.toString());
            System.out.println("done: " + filename + "_Best_Road_Stats.csv");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    static void writeStatsForLinear(String filename, ArrayList<String> names_for_writing_file, ArrayList<Statistics> stats) {
        try (PrintWriter writer = new PrintWriter(new File(filename + "_Linear.csv"))) {

            StringBuilder stringBuilder = new StringBuilder();
            DecimalFormat formatter = new DecimalFormat("#.00", DecimalFormatSymbols.getInstance(Locale.ITALY));

//            teraz dla ułatwienia w odpowiedniej kolejnosci i ilosci tytuły

            for (int i = 0; i < stats.size(); i++) {
                stringBuilder.append("Generation");
                stringBuilder.append(';');
                stringBuilder.append("Bests");
                stringBuilder.append(';');
                stringBuilder.append("Averange");
                stringBuilder.append(';');
                stringBuilder.append("Worst");
                stringBuilder.append(';');
                stringBuilder.append(';');
            }
            stringBuilder.append('\n');

            boolean flag = true;
            for (int i = 0; i < stats.get(0).getBest_fitnesses().size(); i++) {
                for (Statistics stat : stats) {
                    stringBuilder.append(i);
                    stringBuilder.append(';');
                    stringBuilder.append(formatter.format(stat.getBest_fitnesses().get(i)));
                    stringBuilder.append(';');
                    stringBuilder.append(formatter.format(stat.getAvaranges_fitnesses().get(i)));
                    stringBuilder.append(';');
                    stringBuilder.append(formatter.format(stat.getWorst_fitnesses().get(i)));
                    stringBuilder.append(';');
                    stringBuilder.append(';');

                }

                if(flag){
                    for (String s : names_for_writing_file) {
                        stringBuilder.append(';');
                        stringBuilder.append(';');
                        stringBuilder.append(s);
                        stringBuilder.append(';');
                    }
                    flag = false;

                }
                stringBuilder.append('\n');
            }

            writer.write(stringBuilder.toString());
            System.out.println("done: " + filename + "_Generation_Stats");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}
