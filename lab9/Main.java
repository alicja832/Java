import java.beans.Expression;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.Math;
public class Main{
    public static void main(String args[]) throws Exception
    {
        int el;
        int i;
        int wynik=0;
        int l=0;
        int m=0;


        ArrayList<Integer> store=new ArrayList<>();
        ArrayList<Character> dzialanie=new ArrayList<>();


        Path path = FileSystems.getDefault().getPath(".", "Dzialania.txt");
        try (InputStream in = Files.newInputStream(path);
           
        BufferedReader reader =new BufferedReader(new InputStreamReader(in))) {
            String line = null;
           
            while ((line = reader.readLine()) != null) {
                m=0;
                l=0;
            try {
                for (i = 0; i < (line.length() - 1); i++) {

                    if (line.charAt(i) == '(') {
                        m = 1;
                    }
                    if (line.charAt(i) == ')' && m > 0)
                        m = 4;

                    el = line.charAt(i) - 48;
                    if ((el + 48) >= 300)
                        throw new ThreeException();
                    if ((el + 48) >= (97))
                        throw new TwoException(line.charAt(i));

                    if (el < 0) l = 0;

                    if (el >= 0 && m == 1) {

                        if (!store.isEmpty()) {

                            int k = store.get(0);
                            store.add(k);
                            store.set(0, el);
                            m = 2;
                            l=1;
                        }

                    }
                    else if (el >= 0 && m == 2) {

                        if(store.size() > 1 && l>0)
                        {
                            int k = store.get(1);
                            store.add(k);
                            store.set(1, el+store.get(1)*10);
                            m = 3;
                            l=1;
                        }
                        else if (store.size() > 1) {
                            int k = store.get(1);
                            store.add(k);
                            store.set(1, el);
                            m = 3;
                            l=1;
                        }

                    }
                    else if (el >= 0 && l > 0 ){
                        store.set(store.size()-1,store.get(store.size()-1)*10+el);
                        //store.set(store.size()-1,c);

                    }
                    else if (el >= 0 && (m == 0 || m == 4))
                    {
                        store.add(el);
                        l=1;
                    }

                    if (line.charAt(i) == '+') {
                        if (m == 2) {
                            if (!store.isEmpty()) {
                                char k = dzialanie.get(0);
                                dzialanie.add(k);
                                dzialanie.set(0, '+');
                            }

                        } else
                            dzialanie.add('+');


                    }
                    if (line.charAt(i) == '-') {
                        if (m == 2) {
                            if (!store.isEmpty()) {
                                char k = dzialanie.get(0);
                                dzialanie.add(k);
                                dzialanie.set(0, '-');

                            }

                        } else
                            dzialanie.add('-');
                    }
                    if (line.charAt(i) == '*') {
                        if (m == 2) {
                            if (!store.isEmpty()) {
                                char k = dzialanie.get(0);
                                dzialanie.add(k);
                                dzialanie.set(0, '*');

                            }

                        } else
                            dzialanie.add('*');
                    }
                    if (line.charAt(i) == '/') {
                        if (m == 2) {
                            if (!store.isEmpty()) {
                                char k = dzialanie.get(0);
                                dzialanie.add(k);
                                dzialanie.set(0, '/');

                            }

                        }
                        dzialanie.add('/');
                    }
                }
                if(line.charAt(i)!='=')
                {
                    throw new OneException("=");
                }
                if(!(m==0) && !(m==4))
                {
                    throw new OneException(")");
                } 
                else{
                    if(store.size()%2==0)
                        for(i=0;i<store.size();i+=2)
                        {
                            if(store.get(i)>(Math.pow(2,16)-1) || store.get(i+1)>(Math.pow(2,16)-1))
                            {
                                throw new FourException();
                            }
                            if(dzialanie.get(i/2)=='+')
                            {
                                wynik+=store.get(i)+store.get(i+1);
                            }
                             if(dzialanie.get(i/2)=='-')
                            {
                                wynik+=store.get(i)-store.get(i+1);
                            }
                            if(dzialanie.get(i/2)=='/')
                            {
                                wynik+=store.get(i)/store.get(i+1);
                            }
                            if(dzialanie.get(i/2)=='*')
                            {
                                wynik+=store.get(i)*store.get(i+1);
                            }
                        }
                    else{

                        for(i=0;i<(store.size()-1);i+=2)
                        {

                            if(dzialanie.get(0)=='+')
                            {
                                wynik+=store.get(i)+store.get(i+1);
                            }
                            if(dzialanie.get(0)=='-')
                            {
                                wynik+=store.get(i)-store.get(i+1);
                            }
                            if(dzialanie.get(0)=='/')
                            {
                                wynik+=store.get(i)/store.get(i+1);
                            }
                            if(dzialanie.get(0)=='*')
                            {
                                wynik+=store.get(i)*store.get(i+1);
                            }
                        }
                        if(dzialanie.get(1)=='+')
                        {
                            wynik+=store.get(i);
                        }
                        if(dzialanie.get(1)=='-')
                        {
                            wynik=wynik-store.get(i);
                        }
                        if(dzialanie.get(1)=='/')
                        {
                            wynik=wynik/store.get(i);
                        }
                        if(dzialanie.get(1)=='*')
                        {
                            wynik=wynik*store.get(i);
                        }

                    }
                }
                System.out.println("Wynik:" + wynik);
                store.clear();
                dzialanie.clear();
                wynik=0;
            }
            catch(ArithmeticException e)
            {
                System.out.println("zero division error");
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }        
             }catch (IOException x) {
            
                System.out.println("Pliku nie znaleziono w folderze.");
                System.err.println(x);
        }
        
    } 
}

