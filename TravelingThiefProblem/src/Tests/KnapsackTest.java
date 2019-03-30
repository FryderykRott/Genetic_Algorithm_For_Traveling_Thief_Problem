package Genetic_Algorithm.Tests;

import Genetic_Algorithm.Item;
import Genetic_Algorithm.Knapsack;

import static org.junit.jupiter.api.Assertions.*;

class KnapsackTest {

    private Item item1 = new Item(300, 30);
    private Item item2 = new Item(210, 60);
    private Item item3 = new Item(50, 30);

    @org.junit.jupiter.api.Test
    void isPossibleToAddItem(){
        Knapsack knapsack = new Knapsack(100f);

//        Total weight: 30, maximum 100
        assertTrue(knapsack.addItem(item1));

//        Total weight: 90, maximum 100
        assertTrue(knapsack.addItem(item2));

//        Total weight: 120, maximum 100
        assertFalse(knapsack.addItem(item3));
    }

    @org.junit.jupiter.api.Test
    void getKnapsackProfit() {
        Knapsack knapsack = new Knapsack(100f);
        knapsack.addItem(item1);
        knapsack.addItem(item2);

//        Total weight expected 300 + 210 = 510
        assertEquals(item1.getProfit() + item2.getProfit(), knapsack.getKnapsackProfit());
    }

    @org.junit.jupiter.api.Test
    void addItem() {
        Knapsack knapsack = new Knapsack(100f);

//        Total weight: 30, maximum 100
        assertTrue(knapsack.addItem(item1));

//        Total weight: 90, maximum 100
        assertTrue(knapsack.addItem(item2));

//        Total weight: 120, maximum 100
        assertFalse(knapsack.addItem(item3));
    }

    @org.junit.jupiter.api.Test
    void clearKnapsack() {
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
    }
}