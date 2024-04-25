import java.util.ArrayList; // Importuje klasę ArrayList z pakietu java.util, która reprezentuje dynamiczną tablicę.
import java.util.List; // Importuje klasę List z pakietu java.util, która reprezentuje kolekcję uporządkowanych elementów, do których można odwoływać się przez ich indeksy.

public class Main { // Deklaracja klasy Main.
    public static void main(String[] args) { // Deklaracja metody main.

        List<Point> points = new ArrayList<>(); // Inicjalizacja listy points przechowującej obiekty typu Point.
        points.add(new Point(0, 0)); // Dodanie punktu (0,0) do listy.
        points.add(new Point(4, 0)); // Dodanie punktu (4,0) do listy.
        points.add(new Point(4, 4)); // Dodanie punktu (4,4) do listy.
        points.add(new Point(0, 4)); // Dodanie punktu (0,4) do listy.
        Polygon polygon = new Polygon(points); // Inicjalizacja obiektu polygon reprezentującego wielokąt na podstawie punktów.

        // Tworzenie punktu
        Point point = new Point(2, 2); // Inicjalizacja punktu (2,2).
        Land land = new Land(points); // Inicjalizacja obiektu land reprezentującego obszar ziemi na podstawie punktów wielokąta.
        City city = new City(point, 0, "Ferun", land); // Inicjalizacja obiektu city reprezentującego miasto na obszarze ziemi.

        land.addCity(city); // Dodanie miasta do obszaru ziemi.

        // Sprawdzenie czy punkt leży w wielokącie
        boolean result = polygon.inside(point); // Wywołanie metody inside z klasy Polygon w celu sprawdzenia czy punkt leży wewnątrz wielokąta.

        // Wyświetlenie wyniku testu
        if (result) { // Jeśli punkt leży wewnątrz wielokąta:
            System.out.println("Punkt znajduje się wewnątrz wielokąta.");
        } else { // W przeciwnym razie:
            System.out.println("Punkt znajduje się na zewnątrz wielokąta.");
        }
    }
}
