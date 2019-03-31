# Genetic_Algorithm_For_Traveling_Thief_Problem

1. Cel ćwiczenia

Celem ćwiczenia było zapoznanie się z problemem mobilnego złodzieja (TTP – Travelling Thief Problem), a następnie zaimplementowanie algorytmu genetycznego, znajdującego jak najlepsze rozwiązanie problemu oraz analiza działania algorytmu (otrzymanych wyników), dla różnych wartości hiper parametrów.

2. Opis Problemu

Problem Mobilnego Złodzieja (Travelling Thief Problem, TTP)

W problemie TTP, roboczo dla potrzeb zajęć nazywany „Problemem Mobilnego Złodzieja”
złączone są dwa ważne problemy NP-trudne – Problem komiwojażera (ang. Travelling Salesman
Problem, TSP) oraz problem plecakowy (ang. Knapsack Problem, KNP). W skrócie problem polega
na tym, że złodziej odwiedza n lokalizacji i z nich wybiera przedmioty. W złodziejskim fachu
ważne jest aby najmniej się nachodzić i zdobyć najcenniejsze przedmioty. Mamy jednak
ograniczenia, główne: plecak jednak ma ograniczoną pojemność, a przenoszenie przedmiotów to
ciężki kawałek chleba.

W problemie TTP wprowadzono dodatkowe parametry, które uzależniają od siebie jego
poszczególne elementy. To sprawia, że znalezienie ich optimów niezależnie nie jest jednoznaczne
ze znalezieniem optimum dla całego problemu.
Bardziej formalnie: problem dzielimy na problemy TSP i KNP.
Sub-Problem 1 – Problem Komiwojażera (TSP)
Problem Komiwojażera składa się z n miast oraz macierzy odległości pomiędzy tymi miastami (w
naszym przypadku macierz jest symetryczna, co oznacza, że odległość z miasta i do miasta j jest
taka sama jak odległość z miasta j do miasta i). Celem jest znalezienie najkrótszej trasy, która
odwiedzi wszystkie miasta dokładnie raz:

Sub-Problem 2 – Problem Plecakowy (KNP)

Problem Plecakowy składa się ze zbioru m przedmiotów oraz plecaka. Każdy przedmiot opisany
jest wagą (wi) oraz wartością (pi), natomiast plecak ma swoją ograniczoną pojemność (maksymalną
dopuszczalną wagę). Problem polega na wybraniu takich przedmiotów do plecaka, żeby
zmaksymalizować jego wartość nie przekraczając przy tym jego pojemności.

Problem Mobilnego Złodzieja TTP

Problem TTP opiera się zatem na liście n miast oraz odległości między nimi. Dodatkowo istnieje m
przedmiotów opisanych wagą (wi) oraz wartością (pi). Złodziej może odwiedzić każde miasto
(lokalizację) jeden raz i z każdego zabrać pewne przedmioty. Uwaga! nie każdy przedmiot jest
dostępny w każdym mieście - dostępność przedmiotów jest częścią definicji konkretnej instancji
problemu. Rozwiązanie TTP składa się z trasy oraz planu wyboru przedmiotów. Warto zaznaczyć,
że przedmiot można podnieść tylko przy wejściu do miasta. Oznacza to, że złodziej na końcu
swojej podróży wraca do miasta, z którego wyruszył, natomiast nie może już podnieść w nim
przedmiotów.

Funkcja oceny maksymalizuje całkowity zysk złodzieja - sumę wartości przedmiotów. Jednakże
wprowadzone są dodatkowe parametry, które tworzą zależność pomiędzy komponentami problemu.
Prędkość podróży jest zmienna i jest zależna od zapełnienia plecaka w myśl zasady, że trudniej
wędrować z cięższym plecakiem. 

Celem problemu TTP jest maksymalizacja:
G( x , y )=g ( y )−f ( x , y ),

gdzie x to trasa, y to plan wyboru przedmiotów, g(y) to suma wartości wybranych przedmiotów w
plecaku, a f(x, y) to całkowity czas podróży z trasy x z uwzględnieniem przedmiotów z y.
Uwaga: Literatura podaje dodatkowy parametr R, który oznacza koszt „wynajmu” plecaka

3. Opis zaimplementowanego algorytmu

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
