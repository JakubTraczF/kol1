import java.util.ArrayList;
import java.util.List;

public class Land extends Polygon {
    private List<City> cities;

    public Land(List<Point> lista) {
        super(lista); // Wywołanie konstruktora klasy bazowej Polygon z listą punktów.
        this.cities = new ArrayList<>(); // Inicjalizacja listy cities przechowującej obiekty typu City.
}

public void addCity(City city) { // Metoda dodająca miasto do obszaru ziemi.
    if (inside(city.getCenter())) { // Sprawdzenie, czy centrum miasta znajduje się wewnątrz obszaru ziemi.
        cities.add(city); // Dodanie miasta do listy cities.
    } else { // Jeśli centrum miasta nie znajduje się wewnątrz obszaru ziemi:
        throw new RuntimeException("City " + city.getName() + " center is not on the land."); // Rzucenie wyjątku RuntimeException informującego, że centrum miasta nie znajduje się na ziemi.
    }
}
}
