package Genetic_Algorithm;

import java.util.ArrayList;

public class Config {

    int dim;
    int item_num;
    int knapsack_cap;
    float v_min;
    float v_max;
    float rent_ratio;
    Float[][] dist_matrix;
    ArrayList<City> cities;

    public int getDim() {
        return dim;
    }

    public int getItem_num() {
        return item_num;
    }

    public int getKnapsack_cap() {
        return knapsack_cap;
    }

    public float getV_min() {
        return v_min;
    }

    public float getV_max() {
        return v_max;
    }

    public float getRent_ratio() {
        return rent_ratio;
    }

    public Float[][] getDist_matrix() {
        return dist_matrix;
    }

    public ArrayList<City> getCities() {
        return cities;
    }


}
