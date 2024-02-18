import java.util.*;
public class Compv2<T extends Comparator<T> >{
    T tab;
   ArrayList<T> els = new ArrayList<T>(); // Create an ArrayList object
   Compv2(T el)
   {
        tab=el;
   }
   void add(T  u)
   {
        els.add(u);
   }
   
    void sortB() 
   {
       for(int i=0;i<els.size();i++)
       {
            for(int j=1;j<=i;j++)
            {
                if(tab.compare(els.get(j-1),els.get(j))>0)
                {
                    Collections.swap(els, j-1, j);  
                }
            }
       }
   }
   void wypisz()
   {
    System.out.println(els.toString());
   }
}
