# Genetic_Algorithm_For_Traveling_Thief_Problem

1. Cel ćwiczenia
Celem ćwiczenia było zapoznanie się z problemem mobilnego złodzieja (TTP – Travelling Thief Problem), a następnie zaimplementowanie algorytmu genetycznego, znajdującego jak najlepsze rozwiązanie problemu oraz analiza działania algorytmu (otrzymanych wyników), dla różnych wartości hiper parametrów.

2.	Opis zaimplementowanego algorytmu
Algorytm genetyczny został zaimplementowany w języku Java. W celu wczytania danych wykorzystywana jest klasa Loader, która posiada metodę przyjmującą nazwę pliku do przeanalizowania i zwracającą plik typu Config posiadający wszystkie potrzebne dane do działania algorytmu genetycznego. Metoda ta nie tylko ściąga dane z pliku, ale także odpowiednio je formatuje i przetwarza. Zajmuje się ona także uzyskaniem z wcześniej wczytanych danych dodatkowych informacji, takich jak lista obiektów typu City wraz z ich współrzędnymi i przedmiotami (klasa Item) znajdującymi się w nich oraz liczeniem macierzy odległości.
Główna część algorytmu znajduję się w klasie GeneticAlgorithm, która posiada obiekt klasy Population oraz zajmuje się selekcją osobników wybranym algorytmem. Mutowaniem i krzyżowaniem osobników zajmuje się obiekt klasy Population, a osobnika w populacji reprezentuje klasa Thief, posiadająca metody obliczające na podstawie planu drogi i algorytmu zachłannego wartość funkcji celu dla danego złodzieja.
W trakcie działania algorytmu prowadzone są statystyki w klasie Statistics, które następnie są analizowane przez klasę GeneticAlgorithmAnalizer, pełniącą funkcję zapisywania wyników dla różnych plików i hiper parametrów.
Wybraną metodą krzyżowania, była metoda OX. Jest to metoda inwersji – w obrębie dwóch losowo wybranych genów genotypu osobnika dokonuje się odwrócenia wszystkich genów. W zadaniu implementacja krzyżowania opiera się na krzyżowaniu osobników parami, tak jak są ustawieni w populacji (nie losowo wybrane osobniki). Pozwala to na otrzymanie pewności, że każdy osobnik w populacji otrzymał szanse na bycie skrzyżowanym i nie doprowadza to do krzyżowania tych samych osobników.
Mutowanie się osobników zostało zrealizowane za pomocą zamieniania się miejscami dwóch losowo (różnych od siebie) wybranych miejsc w genotypie (planie drogi) osobnika.
W zadaniu zaimplementowane zostały dwie metody selekcji: ruletka i turniej.

Wyniki planu drogi algorytmu genetycznego dla zooptymalizowanych parametrów:

Rozmiar populacji: pop_size = 150
Prawdopodobieństwo krzyżowania: Px = 0.7
Prawdopodobieństwo mutacji: Px = 0.05
Rozmiar turnieju: 5

     Oraz liczby generacji równej 100, 1 000 i 10 000:

a)	liczba generacji = 100

 
b)	Liczba generacji = 1 000


c)	Liczba generacji = 10 000
