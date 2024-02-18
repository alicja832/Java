import java.util.Random;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Main{
    public static void main(String args[])
    {
        Random rand = new Random();

        int N=10000;
        int K=5;
        int m=N/K;
        int tab[]=new int[N];
        int calasuma=0;
        for(int i=0;i<N;i++)
        {
            tab[i]=rand.nextInt(100);
        }

        //System.out.println(Arrays.toString((tab)));
        PartialSum watki[]=new PartialSum[K];
        Thread t[]=new Thread[K];
        for(int i=0;i<K;i++)
        {
            watki[i]=new PartialSum(tab,(i+1)*m);
        }
        for(int i=0;i<K;i++)
        {
            t[i]=new Thread(watki[i]);
            watki[i].stime();
            t[i].start();
            System.out.println(watki[i].getTime());
        }
        for(int i=0;i<K;i++)
        {
            calasuma+=watki[i].getSum();
          
        }
        System.out.println(calasuma);
        
        
        //porownanie czasu
        //zadanie 2
        GetSum element1=new GetSum();
        GetNumbers element2=new GetNumbers();
        Thread one=new Thread(element1);
        Thread two=new Thread(element2);
        two.setPriority(1);
        one.setPriority(2);
        two.start();
        one.start();
        System.out.println("Obliczona suma"+element1.getSum());
    }
}