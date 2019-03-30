package Genetic_Algorithm;
import java.util.ArrayList;
import java.util.Comparator;

class City {

    private ArrayList<Item> city_items;
    private static int next_item = 0;
    private float x_coords;
    private float y_coords;

    private final static Comparator<Item> item_comparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return Integer.compare(o1.getProfit(), o2.getProfit());
        }
    };

    City(float x, float y){
        city_items = new ArrayList<>();

        x_coords = x;
        y_coords = y;
    }

    void addItem(Item item){
        city_items.add(item);
    }


    Item getNextItem(){
        if(city_items.isEmpty())
            return null;
        if(next_item == city_items.size())
            return null;

        return city_items.get(next_item++);
    }

    void sortItems(){
        city_items.sort(item_comparator);
    }

    void clear(){
        next_item = 0;
    }

    float getX_coords() {
        return x_coords;
    }

    float getY_coords() {
        return y_coords;
    }
}
