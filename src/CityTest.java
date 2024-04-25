import java.util.ArrayList; // Importuje klasę ArrayList z pakietu java.util, która reprezentuje listę dynamiczną.
import java.util.List; // Importuje klasę List z pakietu java.util, która reprezentuje kolekcję uporządkowanych elementów, do których można odwoływać się przez ich indeksy.
import java.util.Set; // Importuje klasę Set z pakietu java.util, która reprezentuje zbiór unikalnych elementów.

public class CityTest { // Deklaracja klasy CityTest.

    public static void main(String[] args) { // Metoda główna main.
        Land land = new Land(List.of( // Tworzenie obiektu land z wykorzystaniem listy punktów reprezentujących granice ziemi.
                new Point(0, 0), // Punkt 0,0.
                new Point(10, 0), // Punkt 10,0.
                new Point(10, 10), // Punkt 10,10.
                new Point(0, 10) // Punkt 0,10.
        ));

        testAddCoalToCity(land); // Wywołanie metody testowej testAddCoalToCity z przekazaniem obiektu land.
        testAddWoodOutOfRange(land); // Wywołanie metody testowej testAddWoodOutOfRange z przekazaniem obiektu land.
        testAddFishToPortCity(land); // Wywołanie metody testowej testAddFishToPortCity z przekazaniem obiektu land.
        testAddFishToNonPortCity(land); // Wywołanie metody testowej testAddFishToNonPortCity z przekazaniem obiektu land.
    }

    static void testAddCoalToCity(Land land) { // Metoda testująca dodawanie węgla do miasta.
        Point cityCenter = new Point(0, 0); // Tworzenie punktu reprezentującego centrum miasta.
        City city = new City(cityCenter, 10, "CityA", land); // Tworzenie obiektu city reprezentującego miasto.
        Resource coal = new Resource(cityCenter, Resource.Type.COAL); // Tworzenie zasobu węgla w centrum miasta.
        city.addResource(coal); // Dodawanie zasobu węgla do miasta.
        if (city.getResources().contains(coal)) { // Sprawdzenie, czy zasób węgla został dodany do miasta.
            System.out.println("Test dodania węgla do miasta: Sukces"); // Wyświetlenie komunikatu o sukcesie.
        } else {
            System.out.println("Test dodania węgla do miasta: Niepowodzenie"); // Wyświetlenie komunikatu o niepowodzeniu.
        }
    }

    static void testAddWoodOutOfRange(Land land) { // Metoda testująca dodawanie drewna spoza zasięgu miasta.
        Point cityCenter = new Point(0, 0); // Tworzenie punktu reprezentującego centrum miasta.
        City city = new City(cityCenter, 10, "CityA", land); // Tworzenie obiektu city reprezentującego miasto.
        Resource wood = new Resource(new Point(20, 20), Resource.Type.WOOD); // Tworzenie zasobu drewna poza zasięgiem miasta.
        city.addResource(wood); // Dodawanie zasobu drewna do miasta.
        if (!city.getResources().contains(wood)) { // Sprawdzenie, czy zasób drewna nie został dodany do miasta.
            System.out.println("Test dodania drewna spoza zasięgu miasta: Sukces"); // Wyświetlenie komunikatu o sukcesie.
        } else {
            System.out.println("Test dodania drewna spoza zasięgu miasta: Niepowodzenie"); // Wyświetlenie komunikatu o niepowodzeniu.
        }
    }

    static void testAddFishToPortCity(Land land) { // Metoda testująca dodawanie ryb do miasta portowego.
        Point cityCenter = new Point(0, 0); // Tworzenie punktu reprezentującego centrum miasta.
        City portCity = new City(cityCenter, 10, "PortCity", land); // Tworzenie obiektu portCity reprezentującego miasto portowe.
        portCity.setPort(true); // Ustawienie miasta jako portowego.
        Resource fish = new Resource(cityCenter, Resource.Type.FISH); // Tworzenie zasobu ryb w centrum miasta.
        portCity.addResource(fish); // Dodawanie zasobu ryb do miasta portowego.
        if (portCity.getResources().contains(fish)) { // Sprawdzenie, czy zasób ryb został dodany do miasta portowego.
            System.out.println("Test dodania ryb do miasta portowego: Sukces"); // Wyświetlenie komunikatu o sukcesie.
        } else {
            System.out.println("Test dodania ryb do miasta portowego: Niepowodzenie"); // Wyświetlenie komunikatu o niepowodzeniu.
        }
    }

    static void testAddFishToNonPortCity(Land land) { // Metoda testująca dodawanie ryb do miasta nieportowego.
        Point cityCenter = new Point(0, 0); // Tworzenie punktu reprezentującego centrum miasta.
        City city = new City(cityCenter, 10, "CityA", land); // Tworzenie obiektu city reprezentującego miasto.
        Resource fish = new Resource(cityCenter, Resource.Type.FISH); // Tworzenie zasobu ryb w centrum miasta.
        city.addResource(fish); // Dodawanie zasobu ryb do miasta nieportowego.
        if (!city.getResources().contains(fish)) { // Sprawdzenie, czy zasób ryb nie został dodany do miasta nieportowego.
            System.out.println("Test dodania ryb do miasta nieportowego: Sukces"); // Wyświetlenie komunikatu o sukcesie.
        } else {
            System.out.println("Test dodania ryb do miasta nieportowego: Niepowodzenie"); // Wyświetlenie komunikatu o niepowodzeniu.
        }
    }
}
