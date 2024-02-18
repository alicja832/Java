import java.util.ArrayList;

public class Sum <T extends Number>{
    long toSum(ArrayList<T> tab)
    {
        long sum=0;
        for(T i:tab)
        {
            sum+=i.longValue();
        }
        return sum;
    }
}
