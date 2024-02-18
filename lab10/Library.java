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
import java.util.Random;
//rozmiary tablic w plikach csv
//5 -ksiazki
//13 -journal
//12-film
class Library {
    /**
     * tablica do przechowywania elementów w bibliotece
     */
    ArrayList<LibraryItem> store=new ArrayList<>();
    /**
     *tablica zawierajaca klientow biblioteki
     */
    ArrayList<User> users=new ArrayList<>();
    int przet=0;
    int kw=0;
    int wyp=0;
    public Library(){
        additem("books.csv");
        additem("jlist.csv");
        additem("movies.csv");
        /**
         * dodawanie klientow biblioteki
         */
        for(int i=0;i<80;i++)
        {
            users.add(new Student());
        }
        for(int i=0;i<20;i++)
        {
            users.add(new Faculty());
        }
        int x;
        for(int i=0;i<33;i++)
        {
            x=new Random().nextInt(users.size());
            users.get(x).respons();
        }


    }
    public void additem(String plik)
    {
        Path path = FileSystems.getDefault().getPath(".", plik);
        try (InputStream in = Files.newInputStream(path);

            BufferedReader reader =new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            line = reader.readLine();
            String sample = ";";
            String el[]=line.split(sample);
            LibraryItem item=new Book();
            int k=el.length;
            switch(k) {
                case 5:
                    // code block
                    item=new Book();
                    break;
                case 13:
                    // code block
                    item=new Journal();
                    break;
                case 12:
                    item=new Film();
                    // code block
                    break;
            }
            while ((line = reader.readLine()) != null) {
                el=line.split(sample);
                switch(k) {
                    case 5:
                        // code block
                        item=new Book();
                        break;
                    case 13:
                        // code block
                        item=new Journal();
                        break;
                    case 12:
                        item=new Film();
                        // code block
                        break;
                }
                item.Load(el);
                store.add(item);
        }

    }catch (IOException x) {
            System.err.println(x);
        }
    }
    /**
     * wypisywanie informacji o uzytkowniku biblioteki, sprawdzanie danego dnia po uzyciu metody
     * daily operation okreslono ilosc razy
     */
    public void infoUser(int index)
    {
        users.get(index).pokaz();
    }
    /**
     * uzytkownicy z opoznieniem
     */
    public void delays()
    {
        for(User i:users)
        {
            if(i.get_delay()>0)
            {
                //informacje o uzytkowniku z opoznieniem
                i.pokaz();
            }
        }
    }
    /**
     * symulacja dziennego dzialania biblioteki
     * @param currently
     */
    public void dailyOperation(int currently)
    {
        /**
         * wylosowanie rzeczy ktora bedzie wypozyczona
         */
        int loss;//zmienna do losowania
        /**
         * tablice potrzebne do losowania z odpowiednim prawdopodobienstwem
         */
        ArrayList<Integer> to_los_book=new ArrayList<>(100);
        ArrayList<Integer> to_los_journal=new ArrayList<>(100);
        ArrayList<Integer> to_los_film=new ArrayList<>(100);
        ArrayList<Integer> to_back=new ArrayList<>(100);
        for(int i=0;i<100;i++)
        {
            to_los_book.add(0);
            to_los_film.add(0);
            to_los_journal.add(0);
            to_back.add(0);
        }
                for(int j=0;j<5;j++){
                    loss=new Random().nextInt(100);
                    to_los_book.set(loss,1);
                }
                for(int j=0;j<5;j++){
                    loss=new Random().nextInt(100);
                    to_los_film.set(loss,1);
                }
                for(int j=0;j<8;j++){
                    loss=new Random().nextInt(100);
                    to_los_journal.set(loss,1);
                }
                for(int j=0;j<2;j++){
                    loss=new Random().nextInt(100);
                    to_back.set(loss,1);
                }

         for(int i=0;i<users.size();i++)
        {
            int x=new Random().nextInt(store.size());


                LibraryItem los=store.get(x);
                loss=new Random().nextInt(100);
                if((los.getClass()==new Book().getClass()))
                {
                    //bedzie wypozyczona z pr-0.05

                    if(to_los_book.get(loss)==1) {
                        if (!los.inf && !users.get(i).blokuj())
                            Borrowitem(los, currently, users.get(i));
                        else {

                            if (los.daysOverdue(currently) == 0) {
                                if (users.get(i).how_response()) {
                                    los.inf = false;
                                    users.get(i).odd();
                                    wyp = 0;
                                }
                            }
                            if(los.daysOverdue(currently)!=0)
                            {
                                if(users.get(i).how_response()) {
                                    los.inf = false;
                                    users.get(i).odd();
                                    users.get(i).kara(store.get(i).computeFine(currently));
                                }
                            }
                            if (to_back.get(loss) == 1) {
                                los.inf = false;
                                users.get(i).odd();
                                wyp = 0;
                            }
                        }
                    }
                }
                if((los.getClass()==new Journal().getClass()))
                {

                    if(to_los_journal.get(loss)==1) {
                        if (!los.inf && !users.get(i).blokuj())
                            Borrowitem(los, currently, users.get(i));
                        else {

                            if (los.daysOverdue(currently) == 0) {
                                if (users.get(i).how_response()) {
                                    los.inf = false;
                                    users.get(i).odd();
                                    wyp = 0;
                                }
                            }
                            if(los.daysOverdue(currently)!=0)
                            {
                                if(users.get(i).how_response()) {
                                    los.inf = false;
                                    users.get(i).odd();
                                    users.get(i).kara(store.get(i).computeFine(currently));
                                }
                            }
                            if (to_back.get(loss) == 1) {
                                los.inf = false;
                                users.get(i).odd();
                                wyp = 0;
                            }
                        }
                    }
                    //bedzie wypozyczona z pr-0.08
                }
                if((los.getClass()==new Film().getClass()))
                {
                    //bedzie wypozyczona z pr-0.05

                    if(to_los_film.get(loss)==1)
                    {
                        if(!los.inf && !users.get(i).blokuj())
                           Borrowitem(los,currently,users.get(i));
                         else{

                            if(los.daysOverdue(currently)==0)
                            {
                                if(users.get(i).how_response()) {
                                    los.inf = false;
                                    users.get(i).odd();
                                    wyp = 0;
                                }
                            }
                            if(los.daysOverdue(currently)!=0)
                            {
                                if(users.get(i).how_response()) {
                                    los.inf = false;
                                    users.get(i).odd();
                                    users.get(i).kara(store.get(i).computeFine(currently));
                                }
                            }
                            if(to_back.get(loss)==1) {
                                los.inf = false;
                                users.get(i).odd();
                                wyp=0;
                            }
                        }
                    }
                }
        }

        /**
         * podsumowanie danego dnia
         */
         for(int i=0;i<store.size();i++)
        {
            if(store.get(i).inf)
            {
                wyp++;

                    kw+=store.get(i).computeFine(currently);
                if(store.get(i).isOverdue(currently))
                {
                    przet++;
                }

            }
        }
    }
    /**
     * jakie ksiazki sa wypozyczone
     */
    public void ShowLoans()
    {
        for(int i=0;i<store.size();i++)
        {
            if(store.get(i).inf)
                store.get(i).print();
        }
    }
    /**
     * statystyki ktore mozna pokazac danego dnia
     * @param currently
     */
    public void ShowStatistic(int currently)
    {
        System.out.println("Wypozyczono "+wyp+"pozycji");
        System.out.println("Przeterminowanych jest "+przet+" ksiazek");
        System.out.println("Laczna kwota pobrana: "+kw+" zl");
    }

    /**
     * wersja Borrow item wykorzystywana dla elementow wylosowanych
     * @param tmp
     * @param day
     * @param person
     */
    public void Borrowitem(LibraryItem tmp,int day,User person){
        tmp.inf=true;
        tmp.wyp=day;
        person.wyp();
        if(tmp.getClass()==new Journal().getClass())
        {
            if(person.getClass()==new Faculty().getClass())
                tmp.time= tmp.time_two;
        }
    }

    /**
     * wersja pierwsza Borrowitem- kiedy znamy tytul
     * @param day
     * @param Title
     * @param type
     * @param person
     */
    public void Borrowitem(int day,String Title,String type,String person)
    {

             if(type=="Book")
                for(int i=0;i<store.size();i++)
                {
                    LibraryItem tmp=store.get(i);

                    if(tmp.getClass()==new Book().getClass())
                    {

                        if(((Book)tmp).Title.equals(Title))
                        {
                            if(tmp.inf && tmp.isOverdue(day))
                            {
                                System.out.println(((Book)tmp).Title);
                                    System.out.println("Ksiazka jest niedostepna");
                            }
                            else {
                                tmp.wyp=day;
                                tmp.inf=true;
                                tmp.person=person;
                                System.out.println("Wypozyczono ksiazke");
                            }
                            break;
                        }
                    }
                }
        if(type=="Film")
            for(int i=0;i<store.size();i++)
            {
                LibraryItem tmp=store.get(i);

                if(tmp.getClass()==new Film().getClass())
                {

                    if(((Film)tmp).Title.equals(Title))
                    {
                        if(tmp.inf && tmp.isOverdue(day))
                        {
                            System.out.println(((Book)tmp).Title);
                            System.out.println("Film jest niedostepny");
                        }
                        else {
                            tmp.wyp=day;
                            tmp.inf=true;
                            tmp.person=person;
                            System.out.println("Wypozyczono film");
                        }
                        break;
                    }
                }
            }
        if(type=="Journal")
            for(int i=0;i<store.size();i++)
            {
                LibraryItem tmp=store.get(i);

                if(tmp.getClass()==new Journal().getClass())
                {

                    if(((Journal)tmp).eISSN.equals(Title))
                    {
                        if(tmp.inf && tmp.isOverdue(day))
                        {
                            System.out.println(((Journal)tmp).eISSN);
                            System.out.println("Czasopismo jest niedostepne");
                        }
                        else {
                            if(person.equals("Nauczyciel"))
                                tmp.time=tmp.time_two;
                            tmp.wyp=day;
                            tmp.inf=true;
                            tmp.person=person;
                            System.out.println("Wypozyczono czasopismo");
                        }
                    }
                    break;
                }
            }
                // code block



    }

}
