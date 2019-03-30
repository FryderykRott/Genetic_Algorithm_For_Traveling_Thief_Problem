package Genetic_Algorithm;

import java.util.ArrayList;

public class Knapsack {

    private ArrayList<Item>  items = new ArrayList<>();
    private float knapsack_cap;
    private float knapsack_weight = 0;
    private float knapsack_profit = 0;

    public Knapsack(float knapsack_cap){
        this.knapsack_cap = knapsack_cap;
    }

    public float getKnapsackProfit() {
        return knapsack_profit;
    }

    public boolean addItem(Item item){
        if ( item == null)
            return false;

        if( isPossibleToAddItem(item) ){
            items.add(item);
            knapsack_weight += item.getWeight();
            knapsack_profit += item.getProfit();

            return true;
        }

        return false;
    }

    void clearKnapsack(){
        items.clear();
        knapsack_weight = 0;
        knapsack_profit = 0;
    }

    private boolean isPossibleToAddItem(Item item){
        return knapsack_cap >= knapsack_weight + item.getWeight();
    }

    float getKnapsack_cap() {
        return knapsack_cap;
    }

    boolean isEmpty(){
        return items.isEmpty();
    }

    float getKnapsackWeight() {
        return knapsack_weight;
    }
}
