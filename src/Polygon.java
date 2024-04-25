import java.util.List; // Importuje klasę List z pakietu java.util, która reprezentuje kolekcję uporządkowanych elementów, do których można odwoływać się przez ich indeksy.

public class Polygon { // Deklaracja klasy Polygon.
    private List<Point> lista; // Deklaracja pola przechowującego listę punktów tworzących wielokąt.

    public Polygon(List<Point> lista) { // Konstruktor klasy Polygon.
        this.lista = lista; // Inicjalizacja listy punktów tworzących wielokąt.
    }

    public List<Point> getVertices() { // Metoda zwracająca wierzchołki wielokąta.
        return lista; // Zwraca listę punktów tworzących wielokąt.
    }

    public boolean inside(Point point){ // Metoda sprawdzająca, czy podany punkt znajduje się wewnątrz wielokąta.
        int counter=0; // Deklaracja licznika przecięć boków wielokąta z prostą przechodzącą przez punkt.

        for(int i=0;i<lista.size()-1;i++) {
            Point pa = lista.get(i); // Pobiera punkt pa z listy o indeksie i.
            Point pb = lista.get(i + 1); // Pobiera punkt pb z listy o indeksie i+1.
            if (pa.y > pb.y) { // Sprawdza, czy y-koordynata punktu pa jest większa od y-koordynaty punktu pb.
                Point c; // Deklaruje zmienną pomocniczą c typu Point.
                c = pa; // Przypisuje punkt pa do zmiennej c.
                pa = pb; // Przypisuje punkt pb do zmiennej pa.
                pb = c; // Przypisuje punkt c do zmiennej pb.
            }
            if (pa.y < point.y && point.y < pb.y) { // Sprawdza, czy y-koordynata punktu pa jest mniejsza od y-koordynaty punktu point, a y-koordynata punktu point jest mniejsza od y-koordynaty punktu pb.
                double d = pb.x - pa.x; // Oblicza różnicę x-koordynat punktów pb i pa.
                if (d == 0) { // Sprawdza, czy d jest równa 0.
                    double x = pa.x; // Przypisuje x-koordynat punktu pa do zmiennej x.
                    if (x < point.x) { // Sprawdza, czy x jest mniejsza od x-koordynaty punktu point.
                        counter++; // Zwiększa wartość licznika counter o 1.
                    }
                } else // Jeśli d nie jest równa 0:
                {
                    double a = (pb.y - pa.y) / d; // Oblicza współczynnik kierunkowy a prostej przechodzącej przez punkty pb i pa.
                    double b = pa.y - a * pb.x; // Oblicza współczynnik b prostej przechodzącej przez punkty pb i pa.
                    double x = (point.y - b) / a; // Oblicza x-koordynatę punktu przecięcia prostej przechodzącej przez punkty pb i pa z prostą poziomą przechodzącą przez punkt point.
                    if (x < point.x) { // Sprawdza, czy x jest mniejsza od x-koordynaty punktu point.
                        counter++; // Zwiększa wartość licznika counter o 1.
                    }
                }
            }
            if (counter % 2 == 1) { // Sprawdza, czy wartość licznika counter jest nieparzysta.
                return false; // Zwraca false, jeśli punkt point znajduje się na zewnątrz wielokąta.
            } else { // W przeciwnym razie:
                return true; // Zwraca true, jeśli punkt point znajduje się wewnątrz wielokąta.
            }
        }
    }}