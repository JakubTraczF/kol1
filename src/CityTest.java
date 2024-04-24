import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CityTest {

    public static void main(String[] args) {
        Land land = new Land(List.of(
                new Point(0, 0),
                new Point(10, 0),
                new Point(10, 10),
                new Point(0, 10)
        ));

        testAddCoalToCity(land);
        testAddWoodOutOfRange(land);
        testAddFishToPortCity(land);
        testAddFishToNonPortCity(land);
    }

    static void testAddCoalToCity(Land land) {
        Point cityCenter = new Point(0, 0);
        City city = new City(cityCenter, 10, "CityA", land);
        Resource coal = new Resource(cityCenter, Resource.Type.COAL);
        city.addResource(coal);
        if (city.getResources().contains(coal)) {
            System.out.println("Test dodania węgla do miasta: Sukces");
        } else {
            System.out.println("Test dodania węgla do miasta: Niepowodzenie");
        }
    }

    static void testAddWoodOutOfRange(Land land) {
        Point cityCenter = new Point(0, 0);
        City city = new City(cityCenter, 10, "CityA", land);
        Resource wood = new Resource(new Point(20, 20), Resource.Type.WOOD);
        city.addResource(wood);
        if (!city.getResources().contains(wood)) {
            System.out.println("Test dodania drewna spoza zasięgu miasta: Sukces");
        } else {
            System.out.println("Test dodania drewna spoza zasięgu miasta: Niepowodzenie");
        }
    }

    static void testAddFishToPortCity(Land land) {
        Point cityCenter = new Point(0, 0);
        City portCity = new City(cityCenter, 10, "PortCity", land);
        portCity.setPort(true);
        Resource fish = new Resource(cityCenter, Resource.Type.FISH);
        portCity.addResource(fish);
        if (portCity.getResources().contains(fish)) {
            System.out.println("Test dodania ryb do miasta portowego: Sukces");
        } else {
            System.out.println("Test dodania ryb do miasta portowego: Niepowodzenie");
        }
    }

    static void testAddFishToNonPortCity(Land land) {
        Point cityCenter = new Point(0, 0);
        City city = new City(cityCenter, 10, "CityA", land);
        Resource fish = new Resource(cityCenter, Resource.Type.FISH);
        city.addResource(fish);
        if (!city.getResources().contains(fish)) {
            System.out.println("Test dodania ryb do miasta nieportowego: Sukces");
        } else {
            System.out.println("Test dodania ryb do miasta nieportowego: Niepowodzenie");
        }
    }
}
