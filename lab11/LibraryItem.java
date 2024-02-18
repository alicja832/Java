import java.text.DateFormat;
import java.util.Date;

/**
 * klasa abstrakcyjna reprezentujaca dowolny element w bibliotece
 */
public abstract class LibraryItem {

    public boolean inf=false;
    public String person=new String();
    /**
     * pola wspolne dla wszystkich obiektow w bibliotece
     */
    public int wyp=0;
    int time=0;
    int time_two=0;
    double kwota=0;
    LibraryItem(){}
    abstract public void Load(String [] in);
    public int daysOverdue(int currently)
    {
        if (inf)
            return currently-wyp-time;
        return 0;
    }
    public boolean isOverdue(int currently)
    {
        if(daysOverdue(currently)<0) {
         return true;
        }
        else {
            return false;
        }
    }
    abstract public void print();
    public double computeFine(int currently)
    {
        return daysOverdue(currently)*kwota;
    }
}


