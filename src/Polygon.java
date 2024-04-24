import java.util.List;

public class Polygon {
    private List<Point> lista;

    public Polygon(List<Point> lista) {
        this.lista = lista;
    }

    public List<Point> getVertices() {
        return lista;
    }

    public boolean inside(Point point){
        int counter=0;
        for(int i=0;i<lista.size()-1;i++){
            Point pa=lista.get(i);
            Point pb=lista.get(i+1);
            if(pa.y>pb.y){
                Point c;
                c=pa;
                pa=pb;
                pb=c;
            }
            if(pa.y < point.y && point.y < pb.y){
                double d = pb.x - pa.x;
                if(d == 0){
                    double x = pa.x;
                    if(x < point.x){
                        counter++;
                    }
                }
                else
                {
                    double a = (pb.y - pa.y) / d;
                    double b = pa.y - a * pb.x;
                    double x = (point.y -b) / a;
                    if(x < point.x){
                        counter++;
                    }
                }
            }
        }
        if(counter % 2 == 1){
            return false;
        }else{
            return true;
        }
    }
}
