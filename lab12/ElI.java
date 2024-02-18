import java.util.Comparator;

class ElI implements Comparable<ElI>{
    private int a=0;
    public ElI(int el)
    {
        a=el;
    }

    public int compareTo(ElI b)
    {
        int a1=a;
        int b1=b.a;
        int sa1=0;
        int sa2=0;
        int mn=1;
        while(a1>1)
        {
            
            a1/=mn;
            b1/=mn;
            mn*=10;
            if(a1==0)
                sa1+=1;
            if(b1==0)
                sa2+=1;
        }
        if(sa1<sa2)
            return 1;
        if(sa1>sa2)
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return ""+a;
    }
}
