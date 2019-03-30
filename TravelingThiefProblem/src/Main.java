package Genetic_Algorithm;

public class Main {

    public static void main(String[] args) {

//        1. Żeby zbadać działanie programu należy podać odpowiednią ścieżkę do pliku i wszystko będzie działać
//        2. Po włączeniu w terminalu będą pojawiać się parametry śledzenia algorytmu

//       TODO trzeba podać pełną ścieżkę do pliku z parametrami potrzebnymi do działania algorytmu
//       Pliki dostępne są w folderze pod nazwą DateFiles. Możliwe opcje:
//          - trivial_0, trivial_1
//          - easy_x
//          - medium_x
//          - hard_x,  gdzie (x = {0,1,2,3,4})

        String filename = "D:\\Desktop\\Work\\SI\\DateFiles\\hard_3.ttp";
        Config config = Loader.getInstance().loadData(filename);

//        Funkcja start zwraca po zakończeniu przebiegu wszystkich generacji obiekt klasy Statistics z najważniejszymi wynikami
        GeneticAlgorithm ga = new GeneticAlgorithm(config, 150, 0.05f, 1f, GeneticAlgorithm.TOURNAMENT,  1000, 5);
        Statistics stats = ga.start();

//        TODO Aby zapisać należy podać pełną ścieżkę do pliku który chcemy zapisać
//        zapiszę się w ten sposób najlepsza znaleziony plan drogi złodzieja
        CSVWriter.writeRoad(config, stats.best_of_all.getRoadPlan(), "C:\\przykład\\TODO\\test.csv");

//        Zablokowany obiekt poniżej służy do analizowania
//        GeneticAlgorithmAnalizer gaa = new GeneticAlgorithmAnalizer();
//        gaa.startAnalizing();
    }
}
