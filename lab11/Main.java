import java.lang.reflect.*;

public class Main{
    public static void main(String args[])
    {
        Library lib=new Library();
        for(int i=0;i<100;i++)
            lib.dailyOperation(i);
        //lib.ShowStatistic(364);
        lib.infoUser(0);
        for(int i=0;i<50;i++)
            lib.dailyOperation(i);
        lib.infoUser(1);
        System.out.println(Book.class);

        Book book = new Book();
        String s = book.toString();
        
        //Class c1 = Class.forName(s);
        //System.out.println(c1.toString());
        Class c2 = book.getClass();
        System.out.println(c2.toString());
        Class c3 = Book.class;
        System.out.println(c3.toString());
        //pobrac i wyswietlic wszystkie zadeklarowane metody klasy book
        java.lang.reflect.Method[] m = c2.getDeclaredMethods();
        java.lang.reflect.Field[] f = c2.getDeclaredFields();
        for (int i = 0; i < m.length; i++) {

            System.out.println(m[i]);
            //uzyskać modyfikator dostępu metod i pobrać typy zwracane dla każdej metody.

        }
         for (int i = 0; i < f.length; i++) {
            
             System.out.println(f[i]);
            if(f[i].toString()=="private int Book.pagesNr")
            {
               // f[i].set(book, 120);
            }
            //uzyskać modyfikator dostępu metod i pobrać typy zwracane dla każdej metody.
            
        
        }
    
       

    } 
}
