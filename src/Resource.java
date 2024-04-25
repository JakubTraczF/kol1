public class Resource { // Deklaracja klasy Resource.
    public enum Type { // Deklaracja enumeracji Type.
        COAL, // Typ zasobu: węgiel.
        WOOD, // Typ zasobu: drewno.
        FISH // Typ zasobu: ryby.
    }

    private final Point location; // Deklaracja pola przechowującego lokalizację zasobu.
    private final Type type; // Deklaracja pola przechowującego typ zasobu.

    public Resource(Point location, Type type) { // Konstruktor klasy Resource.
        this.location = location; // Inicjalizacja pola location lokalizacją zasobu.
        this.type = type; // Inicjalizacja pola type typem zasobu.
    }

    public Point getLocation() { // Metoda zwracająca lokalizację zasobu.
        return location; // Zwraca lokalizację zasobu.
    }

    public Type getType() { // Metoda zwracająca typ zasobu.
        return type; // Zwraca typ zasobu.
    }
}
