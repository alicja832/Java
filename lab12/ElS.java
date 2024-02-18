import java.util.Comparator;

class ElS implements Comparable<ElS>{

    public String s;

    public ElS(String z)
    {
        s=z;
    }
    public int compareTo(ElS a)
    {
       if(a.s.length()<s.length())
         return 1;
        if(a.s.length()>s.length())
         return -1;
        return 0;
    }
    @Override
    public String toString() {
        return s;
    }
}
