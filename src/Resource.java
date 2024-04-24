public class Resource {
    public enum Type {
        COAL, WOOD, FISH
    }

    private final Point location;
    private final Type type;

    public Resource(Point location, Type type) {
        this.location = location;
        this.type = type;
    }

    public Point getLocation() {
        return location;
    }

    public Type getType() {
        return type;
    }
}
