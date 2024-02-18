//zadanie 2
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
public class Comp<T extends Comparable<T>> {
  
   ArrayList<T> els = new ArrayList<T>(); // Create an ArrayList object
   Comp()
   {
        
   }
   void add(T  u)
   {
        els.add(u);
   }
    void sortB() 
   {
       for(int i=0;i<els.size();i++)
       {
            for(int j=1;j<(els.size()-i);j++)
            {
                if(els.get(j-1).compareTo(els.get(j))>0)
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
