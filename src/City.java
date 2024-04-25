import java.util.HashSet; // Importuje klasę HashSet z pakietu java.util, która reprezentuje zbiór unikalnych elementów.
import java.util.List; // Importuje klasę List z pakietu java.util, która reprezentuje kolekcję uporządkowanych elementów, do których można odwoływać się przez ich indeksy.
import java.util.Set; // Importuje klasę Set z pakietu java.util, która reprezentuje zbiór unikalnych elementów.
import java.util.Arrays; // Importuje klasę Arrays z pakietu java.util, która zawiera różne metody do manipulowania tablicami.

public class City extends Polygon { // Deklaracja klasy City dziedziczącej po klasie Polygon.
    private final Point center; // Deklaracja pola przechowującego centrum miasta.
    private boolean port; // Deklaracja pola informującego, czy miasto jest portem.
    private final Set<Resource> resources; // Deklaracja zbioru przechowującego zasoby miasta.
    private String name; // Deklaracja pola przechowującego nazwę miasta.

    public Point getCenter() { // Metoda zwracająca centrum miasta.
        return center; // Zwraca centrum miasta.
    }

    public String getName() { // Metoda zwracająca nazwę miasta.
        return name; // Zwraca nazwę miasta.
    }

    public void addResource(Resource resource) { // Metoda dodająca zasób do miasta.
        resources.add(resource); // Dodaje zasób do zbioru zasobów miasta.
    }

    public void setPort(boolean port) { // Metoda ustawiająca informację, czy miasto jest portem.
        this.port = port; // Ustawia wartość pola port na podaną wartość.
    }

    public City(Point center, double wallLength, String cityName, Land land) { // Konstruktor klasy City.
        super(Arrays.asList( // Wywołanie konstruktora klasy bazowej Polygon z listą punktów wierzchołków.
                new Point(center.x - wallLength / 2, center.y - wallLength / 2), // Utworzenie punktu reprezentującego lewy dolny wierzchołek miasta.
                new Point(center.x + wallLength / 2, center.y - wallLength / 2), // Utworzenie punktu reprezentującego prawy dolny wierzchołek miasta.
                new Point(center.x + wallLength / 2, center.y + wallLength / 2), // Utworzenie punktu reprezentującego prawy górny wierzchołek miasta.
                new Point(center.x - wallLength / 2, center.y + wallLength / 2) // Utworzenie punktu reprezentującego lewy górny wierzchołek miasta.
        ));
        this.center = center; // Przypisanie centrum miasta.
        this.port = isPortCity(land); // Ustalenie, czy miasto jest portem na podstawie analizy położenia na ziemi.
        this.resources = new HashSet<>(); // Inicjalizacja zbioru zasobów miasta.
    }

    private boolean isPortCity(Land land) { // Metoda sprawdzająca, czy miasto jest portem.
        for (Point vertex : super.getVertices()) { // Iteracja po wierzchołkach miasta.
            if (!land.inside(vertex)) { // Jeśli wierzchołek miasta znajduje się poza obszarem ziemi:
                return true; // Zwraca true, oznaczając, że miasto jest portem.
            }
        }
        return false; // W przeciwnym przypadku zwraca false.
    }

    public void addResourcesInRange(List<Resource> resourceList, double range) { // Metoda dodająca zasoby znajdujące się w zasięgu miasta.
        for (Resource resource : resourceList) { // Iteracja po liście zasobów.
            if (isInRange(resource.getLocation(), range) && isValidResource(resource)) { // Jeśli zasób jest w zasięgu miasta i jest ważny:
                resources.add(resource); // Dodaje zasób do zbioru zasobów miasta.
            }
        }
    }

    private boolean isInRange(Point location, double range) { // Metoda sprawdzająca, czy dany punkt znajduje się w zasięgu miasta.
        double distance = Math.sqrt(Math.pow(location.x - center.x, 2) + Math.pow(location.y - center.y, 2)); // Obliczenie odległości między punktem a centrum miasta.
        return distance <= range; // Zwraca true, jeśli odległość mieści się w zasięgu, w przeciwnym razie false.
    }

    private boolean isValidResource(Resource resource) { // Metoda sprawdzająca, czy zasób jest ważny dla miasta.
        return !((resource.getType() == Resource.Type.FISH) && !port); // Zwraca true, jeśli zasób jest rybą i miasto jest portem, w przeciwnym razie false.
    }

    public Set<Resource> getResources() { // Metoda zwracająca zbiór zasobów miasta.
        return resources; // Zwraca zbiór zasobów miasta.
    }
}
