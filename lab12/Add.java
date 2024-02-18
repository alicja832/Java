//zadanie 1
public class Add<T extends Number,U extends Number> {
    private T x; 
    private U y;
    public Add(T a,U b)
    {
        x=a;
        y=b;
    }

    public long add()
    {
        return  x.longValue()+y.longValue();
    }
}
