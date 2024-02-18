/**
 * klasa ktora mozna wykorzystac do zlozenia usera i pozycji - implementacja User i Library Item
 */
public class Compute extends LibraryItem implements User{
    public User x;
    public LibraryItem y;
    public void Load(String [] in){
        y.Load(in);
    }
    public void print()
    {
        y.print();
    }
    public void add_delay(int k)
    {
        x.add_delay(k);
    }
    public int get_delay()
    {
        return x.get_delay();
    }
    public void respons()
    {}
    public void wyp()
    {
        x.wyp();
    }
    public void odd()
    {
        x.odd();
    }
    public boolean how_response()
    {
        return x.how_response();
    }
    public int check()
    {
        return x.check();
    }
    public int kara(double kwota)
    {
        return x.kara(kwota);
    }
    public int ile_kary()
    {
        return x.ile_kary();
    }

}
