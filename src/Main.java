import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(4, 0));
        points.add(new Point(4, 4));
        points.add(new Point(0, 4));
        Polygon polygon = new Polygon(points);

        // Tworzenie punktu
        Point point = new Point(2, 2);
        Land land = new Land(points);
        City City = new City(point,0, "Ferun",land);

        land.addCity(City);
        // Sprawdzenie czy punkt leży w wielokącie
        boolean result = polygon.inside(point);

        // Wyświetlenie wyniku testu
        if (result) {
            System.out.println("Punkt znajduje się wewnątrz wielokąta.");
        } else {
            System.out.println("Punkt znajduje się na zewnątrz wielokąta.");
        }
    }
}