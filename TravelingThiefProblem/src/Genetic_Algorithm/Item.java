package Genetic_Algorithm;

public class Item {

    private int profit;
    private int weight;
    private float ratio;

    public Item(int profit, int weight) {
        this.profit = profit;
        this.weight = weight;
        ratio = ((float) profit)/((float)weight);
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public float getRatio() {
        return ratio;
    }
}
