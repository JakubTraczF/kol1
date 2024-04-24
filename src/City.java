import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

public class City extends Polygon {
    private final Point center;
    private boolean port;
    private final Set<Resource> resources;
        private String name;
    public Point getCenter() {
        return center;
    }
    public String getName() {
        return name;
    }
    public void addResource(Resource resource) {
        resources.add(resource);
    }
    public void setPort(boolean port) {
        this.port = port;
    }
    public City(Point center, double wallLength, String cityName, Land land) {
        super(Arrays.asList(
                new Point(center.x - wallLength / 2, center.y - wallLength / 2),
                new Point(center.x + wallLength / 2, center.y - wallLength / 2),
                new Point(center.x + wallLength / 2, center.y + wallLength / 2),
                new Point(center.x - wallLength / 2, center.y + wallLength / 2)
        ));
        this.center = center;
        this.port = isPortCity(land);
        this.resources = new HashSet<>();
    }

    private boolean isPortCity(Land land) {
        for (Point vertex : super.getVertices()) {
            if (!land.inside(vertex)) {
                return true;
            }
        }
        return false;
    }

    public void addResourcesInRange(List<Resource> resourceList, double range) {
        for (Resource resource : resourceList) {
            if (isInRange(resource.getLocation(), range) && isValidResource(resource)) {
                resources.add(resource);
            }
        }
    }

    private boolean isInRange(Point location, double range) {
        double distance = Math.sqrt(Math.pow(location.x - center.x, 2) + Math.pow(location.y - center.y, 2));
        return distance <= range;
    }

    private boolean isValidResource(Resource resource) {
        return !((resource.getType() == Resource.Type.FISH) && !port);
    }

    public Set<Resource> getResources() {
        return resources;
    }
}
