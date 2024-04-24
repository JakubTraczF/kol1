import java.util.ArrayList;
import java.util.List;

public class Land extends Polygon {
    private List<City> cities;

    public Land(List<Point> lista) {
        super(lista);
        this.cities = new ArrayList<>();
    }

    public void addCity(City city) {
        if (inside(city.getCenter())) {
            cities.add(city);
        } else {
            throw new RuntimeException("City " + city.getName() + " center is not on the land.");
        }
    }

}
