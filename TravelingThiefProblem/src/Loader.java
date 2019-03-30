package Genetic_Algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Loader {
    private static Loader ourInstance = new Loader();

    static Loader getInstance() {
        return ourInstance;
    }

    private static final int MAGIC_NUMBER = 49;

    private Loader() { }

    Config loadData(String filename){

        ArrayList<String> parameters = new ArrayList<>();
        ArrayList<String> node_coords = new ArrayList<>();
        ArrayList<String> items = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File( filename));

            // Pomijamy dwie pierwsze linijki
            scanner.nextLine();
            scanner.nextLine();

            // Zaczynamy parsowanie
            int magic_counter = 0;
            String line;
            while (scanner.hasNextLine()) {

                line = scanner.nextLine();

                String[] words_of_line = line.split("\t");

                if(magic_counter < 6) {
                    parameters.add(words_of_line[1]);
                }
                else if(magic_counter >= MAGIC_NUMBER ) {

                    if(words_of_line.length == 3){
                        addWordsToList(node_coords, words_of_line);
                    }else if(words_of_line.length == 4){
                        addWordsToList(items, words_of_line);
                    }

                }else {
                    scanner.nextLine();
                    magic_counter = MAGIC_NUMBER;
                }

                magic_counter++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return configBuilder(parameters, node_coords, items);
    }

    private Config configBuilder(ArrayList<String> parameters, ArrayList<String> node_coords, ArrayList<String> items) {
        Config config = new Config();

        int dim = Integer.parseInt(parameters.get(0));
        config.dim = dim;
        config.item_num = Integer.parseInt(parameters.get(1));
        config.knapsack_cap = Integer.parseInt(parameters.get(2));

        config.v_min = Float.parseFloat(parameters.get(3));
        config.v_max = Float.parseFloat(parameters.get(4));
        config.rent_ratio = Float.parseFloat(parameters.get(5));

        config.dist_matrix = calculateDistances(node_coords, dim);
        config.cities = makeCitiesWithItems(parseArrayToFloatArray(items), node_coords, dim);

        return config;
    }

    private ArrayList<City> makeCitiesWithItems(ArrayList<Integer> item_section, ArrayList<String> node_coords, int dim) {
        ArrayList<City> cities = new ArrayList<>(dim);

        for(int i = 0; i < dim; i++){
            float x = Float.parseFloat(node_coords.get(2*i));
            float y = Float.parseFloat(node_coords.get(2*i + 1));

            cities.add(new City(x, y));
        }

        int city_index;
        for(int i = 0; i < item_section.size(); i = i + 3){


            Item item = new Item(item_section.get(i), item_section.get(i + 1));
            city_index = item_section.get(i + 2) - 1;

            cities.get(city_index).addItem(item);
        }

        for ( City city: cities)
            city.sortItems();

        return cities;
    }

    private Float[][] calculateDistances(ArrayList<String> node_coords, int dim) {
        Float[][] distances_matrix = new Float[dim][dim];

        for(int i = 0; i < dim - 1; i++){
            for(int j = i+1; j < dim; j++){

                float x0 = Float.parseFloat(node_coords.get(2*i));
                float y0 = Float.parseFloat(node_coords.get(2*i + 1));

                float x1 = Float.parseFloat(node_coords.get(2*j));
                float y1 = Float.parseFloat(node_coords.get(2*j + 1));

                double dist = Math.sqrt(Math.pow((x0 - x1) ,2) + Math.pow((y0 - y1) ,2));

                distances_matrix[i][j] = (float) dist;
                distances_matrix[j][i] = (float) dist;
            }
        }
        return distances_matrix;
    }

    private ArrayList<Integer> parseArrayToFloatArray(ArrayList<String> items) {
        ArrayList<Integer> integer_date = new ArrayList<>();

        for (String item : items) {
            integer_date.add(Integer.parseInt(item));
        }

        return integer_date;
    }

    // Dodaje do arraylisty wszysktie elementy tablicy words pomijajać pierwszy element
    private void addWordsToList(ArrayList list, String[] words){
        for(int i = 1; i < words.length; i++){
            list.add(words[i]);
        }
    }

}

