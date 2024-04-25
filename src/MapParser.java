import com.fasterxml.jackson.annotation.JsonProperty; // Importuje adnotację JsonProperty z pakietu com.fasterxml.jackson.annotation.
import com.fasterxml.jackson.dataformat.xml.XmlMapper; // Importuje klasę XmlMapper z pakietu com.fasterxml.jackson.dataformat.xml, która służy do mapowania obiektów na format XML.
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper; // Importuje adnotację JacksonXmlElementWrapper z pakietu com.fasterxml.jackson.dataformat.xml.annotation, która jest używana do zdefiniowania wrappera XML dla elementów kolekcji.
import java.io.File; // Importuje klasę File z pakietu java.io, która reprezentuje abstrakcyjną ścieżkę do pliku i katalogów w systemie plików.
import java.io.IOException; // Importuje klasę IOException z pakietu java.io, która jest bazowym wyjątkiem dla operacji wejścia/wyjścia.
import java.util.ArrayList; // Importuje klasę ArrayList z pakietu java.util, która reprezentuje listę dynamiczną.
import java.util.List; // Importuje klasę List z pakietu java.util, która reprezentuje kolekcję uporządkowanych elementów, do których można odwoływać się przez ich indeksy.
import java.util.Map; // Importuje klasę Map z pakietu java.util, która reprezentuje odwzorowanie klucza i wartości.
import java.util.Set; // Importuje klasę Set z pakietu java.util, która reprezentuje zbiór unikalnych elementów.

public class MapParser { // Deklaracja klasy MapParser.

    static public final class Svg { // Deklaracja statycznej klasy wewnętrznej Svg.
        @JacksonXmlElementWrapper(useWrapping = false) // Adnotacja wskazująca na to, że nie należy używać wrappera XML dla elementów kolekcji.
        @JsonProperty("rect") // Adnotacja wskazująca, że pole rects ma być mapowane z pola "rect" w formacie JSON/XML.
        private List<Map<String, String>> rects; // Deklaracja listy rects przechowującej mapy parametrów dla elementów "rect".

        @JacksonXmlElementWrapper(useWrapping = false) // Adnotacja wskazująca na to, że nie należy używać wrappera XML dla elementów kolekcji.
        @JsonProperty("polygon") // Adnotacja wskazująca, że pole polygons ma być mapowane z pola "polygon" w formacie JSON/XML.
        private List<Map<String, String>> polygons; // Deklaracja listy polygons przechowującej mapy parametrów dla elementów "polygon".

        @JacksonXmlElementWrapper(useWrapping = false) // Adnotacja wskazująca na to, że nie należy używać wrappera XML dla elementów kolekcji.
        @JsonProperty("text") // Adnotacja wskazująca, że pole texts ma być mapowane z pola "text" w formacie JSON/XML.
        private List<Map<String, String>> texts; // Deklaracja listy texts przechowującej mapy parametrów dla elementów "text".

        @JacksonXmlElementWrapper(useWrapping = false) // Adnotacja wskazująca na to, że nie należy używać wrappera XML dla elementów kolekcji.
        @JsonProperty("circle") // Adnotacja wskazująca, że pole circles ma być mapowane z pola "circle" w formacie JSON/XML.
        private List<Map<String, String>> circles; // Deklaracja listy circles przechowującej mapy parametrów dla elementów "circle".
    }

    private List<Label> labels = new ArrayList<>();
    private List<City> cities = new ArrayList<>();
    private List<Land> lands = new ArrayList<>();
    public List<Land> getLands() {
        return lands; // Zwraca listę lands zawierającą wszystkie utworzone obiekty Land.

        private void parseText(Map<String, String> params) { // Metoda parseText służy do parsowania tekstu z mapy parametrów.
            addLabel(params.get(""), new Point (Double.parseDouble(params.get("x")), Double.parseDouble(params.get("y")))); // Dodaje etykietę na podstawie przekazanych parametrów: tekstu, współrzędnych x i y.
        }

        private void addLabel(String text, Point bottomLeft) { // Metoda addLabel dodaje etykietę na podstawie tekstu i współrzędnych lewego dolnego rogu.
            labels.add(new Label(bottomLeft, text)); // Dodaje nową etykietę do listy etykiet z podanym tekstem i współrzędnymi lewego dolnego rogu.
        }

        void matchLabelsToTowns() { // Metoda matchLabelsToTowns dopasowuje etykiety do miast.
            for (City city : cities) { // Iteruje przez wszystkie miasta.
                Label nearestLabel = findNearestLabel(city.center); // Znajduje najbliższą etykietę dla danego miasta.
                if (nearestLabel != null) { // Sprawdza, czy znaleziono najbliższą etykietę.
                    city.setName(nearestLabel.text()); // Ustawia nazwę miasta na tekst najbliższej etykiety.
                }
            }
        }

        private Label findNearestLabel(Point cityCenter) { // Metoda findNearestLabel znajduje najbliższą etykietę dla danego punktu.
            double minDistance = Double.MAX_VALUE; // Inicjalizuje minimalną odległość na maksymalną wartość double.
            Label nearestLabel = null; // Inicjalizuje najbliższą etykietę jako null.
            for (Label label : labels) { // Iteruje przez wszystkie etykiety.
                double distance = calculateDistance(cityCenter, label.point()); // Oblicza odległość między centrum miasta a punktem etykiety.
                if (distance < minDistance) { // Jeśli obliczona odległość jest mniejsza od aktualnej minimalnej odległości.
                    minDistance = distance; // Ustawia nową minimalną odległość.
                    nearestLabel = label; // Ustawia najbliższą etykietę na aktualną etykietę.
                }
            }
            return nearestLabel; // Zwraca najbliższą etykietę.
        }

        private double calculateDistance(Point p1, Point p2) { // Metoda calculateDistance oblicza odległość między dwoma punktami.
            double dx = p1.x() - p2.x(); // Oblicza różnicę współrzędnych x.
            double dy = p1.y() - p2.y(); // Oblicza różnicę współrzędnych y.
            return Math.sqrt(dx * dx + dy * dy); // Oblicza odległość jako pierwiastek kwadratowy z sumy kwadratów różnic współrzędnych.
        }

        void addCitiesToLands() { // Metoda addCitiesToLands dodaje miasta do obszarów ziemi.
            for (Land land : lands) { // Iteruje przez wszystkie obszary ziemi.
                for (City city : cities) { // Iteruje przez wszystkie miasta.
                    if (land.inside(city.center)) { // Sprawdza, czy miasto znajduje się wewnątrz danego obszaru ziemi.
                        land.addCity(city); // Dodaje miasto do obszaru ziemi.
                    }
                }
            }
        }

        void parse(String path) { // Metoda parse służy do parsowania pliku SVG.

            XmlMapper xmlMapper = new XmlMapper(); // Tworzy obiekt XmlMapper do parsowania plików XML.
            File file = new File(path); // Tworzy obiekt File reprezentujący plik o podanej ścieżce.
            try {
                Svg svg = xmlMapper.readValue(file, Svg.class); // Parsuje plik SVG na obiekt Svg.
                for(var item : svg.texts) // Iteruje przez wszystkie teksty w SVG.
                    parseText(item); // Parsuje tekst.
                // TODO: Krok 13
                matchLabelsToTowns(); // Dopasowuje etykiety do miast.
                addCitiesToLands(); // Dodaje miasta do obszarów ziemi.

                for (var item : svg.rects) { // Iteruje przez wszystkie prostokąty w SVG.
                    double x = Double.parseDouble(item.get("x")); // Pobiera współrzędną x.
                    double y = Double.parseDouble(item.get("y")); // Pobiera współrzędną y.
                    double width = Double.parseDouble(item.get("width")); // Pobiera szerokość.
                    double height = Double.parseDouble(item.get("height")); // Pobiera wysokość.

                    // Oblicza środek miasta.
                    double centerX = x + width / 2; // Oblicza współrzędną x środka.
                    double centerY = y + height / 2; // Oblicza współrzędną y środka.

                    // Tworzy obiekt Land i dodaje go do listy lands.
                    lands.add(new Land(List.of(new Point(x, y), new Point(x + width, y),
                            new Point(x + width, y + height), new Point(x, y + height))));
                }

            } catch (IOException e) { // Obsługuje wyjątek IOException.
                throw new RuntimeException(e); // Rzuca nowy wyjątek RuntimeException z przekazanym wyjątkiem IOException.
            }
        }
    }

