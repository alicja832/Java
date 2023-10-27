import java.awt.Color;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @Sroka
 * class for load and select sroka from picture
 */
public class Sroka {
    /**
     * tablica binarna z informacjami o obrazku sroki-punkt odniesienia
     */
    private static ArrayList<ArrayList<Integer>> ref;
    BufferedImage img=new BufferedImage(100, 100, BufferedImage.TYPE_BYTE_GRAY);
    /**
     * tablica z wspólrzędnymi białych kropek w obrazku sroki
     */
    private ArrayList<ArrayList<Integer>> inf;
    /**
     * tablica binarna z informacjami o obrazku
     */
    public ArrayList<ArrayList<Integer>> store;
    static int elements=0;

    /**
     * konstruktor domyślny-ładuje obrazek sroki, by móc znajdować sroki w innych obrazkach
     */
    Sroka()
    {
        try {
            LoadImage("ref_image.tif");
        }catch(IOException e)
        {
            System.out.println("Nie udało się załadować obrazka");
        }

        GetMapy();

        ref=new ArrayList<>(2);
        for(int i=0;i<2;i++)
            ref.add(new ArrayList<>(store.get(0).size()));
        ref=store;
        to_contour(ref);
    }
    /**
     * method for load picture to instation of a class i od razy konwertuje do tablicy
     * @param name
     * @throws IOException
     */
    public final void LoadImage(String name)throws IOException
    {
        img=ImageIO.read(new File(name));
        ImageToArray();
    }

    /**
     * funkcja wybierająca współrzędne górnego konturu sroki-punktów które tworzą funkcję
     * @param table
     */
    private void to_contour(ArrayList<ArrayList<Integer>> table)
    {
        int k,y_max=0;
        //tablice które opisują kontur
        ArrayList<Integer> tabx=new ArrayList<>();
        ArrayList<Integer> taby=new ArrayList<>();

            for(int j=0;j<img.getWidth();j++)
            {
                y_max=10000;
                if(table.get(0).contains(j)) {
                    for (k = 0; k < table.get(0).size(); k++)
                        if (table.get(0).get(k) == j && table.get(1).get(k) < y_max)
                            y_max = table.get(1).get(k);


                    //tutaj chcemy przeszukać wszyskie elementy ktore zawierają tego x i  wyodrębnic tego co mają największy y

                    tabx.add(j);
                    taby.add(y_max);
                }
            }
            //próba
        int i,j;
        ArrayList<ArrayList<Integer>> tmp=new ArrayList<>();
        for(k=0;k<100;k++)
            tmp.add(new ArrayList<>(100));
        for(i=0;i<100;i++) {
            for (j = 0; j < 100; j++) {
                tmp.get(i).add(0);
                if (tabx.contains(j) && taby.get(tabx.indexOf(j)) == i) {
                    tmp.get(i).set(j,1);
                }
            }
        }
        try {
            DisplayImage(tmp, 100, 100, "linia");
        }catch(IOException e)
        {
            System.out.println("nn");
        }


        elements=tabx.size();
            ref.set(0,tabx);
            ref.set(1,taby);

    //tabx oraz taby będą służyły teraz do sprawdzania czy jakiś x, y należy do wielomianu
    }

    /**
     *
     * store-tablica współrzędnych białych pikseli w załadowanym obrazie
     *             własciwe liczenie srok
     *             tutaj współrzędne powinny być w jakiejś tablicy globalnej teoretycznie
     */
    public int GetMapies()

    {
        GetMapy();

        //x_p oraz x_y- przesunięcie danej białej figury sroki, x-liczba srok
        int x=0,x_p=0,y_p=0;
        //zmienne do iterowania po tablicach
        //n-liczba wspólnych punktów konturu sroki i konturu danego znalezionego bialego elementu na obrazku
        int i,j,k,sroki=0,y_min,y_max=0,n=0,m=1,l;
        //tablice do zapisu wspólrzędnych wspólnych punktów
        ArrayList<Integer> tabx;
        ArrayList<Integer> taby;
        tabx=new ArrayList<>();
        taby=new ArrayList<>();
        ArrayList<Integer> max_x=new ArrayList<>();
        ArrayList<Integer> max_y=new ArrayList<>();

        //tutaj zapisujemy współrzędne tylko tych punktów, których ilość jest odpowiednia-są górnym konturem sroki
        ArrayList<Integer> tabxx=new ArrayList<>();
        ArrayList<Integer> tabyy=new ArrayList<>();

        for(i=0;i<img.getHeight();i++) {

            n = 0;

            for (j = 0; j < img.getWidth(); j++) {

                y_min = 1000;

                //sprawdzanie czy znajduje się biała kropka o danej współrzędnej x
                if (store.get(0).contains(j)) {

                    //szukamy tego punktu który jest elementem górnego konturu-znajduje się najwyżej
                    for (k = 0; k < store.get(0).size(); k++) {
                        if (store.get(0).get(k) == j && store.get(1).get(k) < y_min && store.get(1).get(k) > y_max) {
                            y_min = store.get(1).get(k);
                        }

                    }

                    //sprawdzanie czy dany punkt należy do krzywej wyznaczonej przez kontur sroki
                    if (lagrangeInterpolation(ref.get(0), ref.get(1), j - x_p, y_min - y_p)) {

                        n++;
                        tabx.add(j);
                        taby.add(y_min);
                    }

                }
                if (tabx.size() != 0) {
                    //jesli wzięty element jest elementem kolejnego konturu na obrazku ustalamy jego przesuniecie zakładam,
                    if (tabx.get(tabx.size() - 1) < (j)) {
                        int x_max=0;
                        x_p = j - ref.get(0).get(0);
                        y_p = y_min - ref.get(1).get(0);
                        //dla poprzedniego tego elementu powinniśmy znaleźć y_max
                        for(l=y_min+1;l<img.getHeight();l++){
                            for (k = 0; k < store.get(0).size(); k++)
                                if (store.get(0).get(k) == j) {

                                    if (store.get(0).get(k) < j && store.get(0).get(k) > (j - 10) && store.get(1).get(k) == l) {
                                        m=1;
                                        break;
                                    } else {
                                        y_max = l;
                                        x_max = store.get(0).get(k);
                                        m=0;
                                        break;
                                    }
                                }
                            if(m==0)
                                break;
                        }
                        max_y.add(y_max);
                        max_x.add(x_max);
                        //jednocześnie powinniśmy tutaj znaleźć dla niego y który powinien być ograniczeniem dla bieżącego, nie poprzedniego elementu
                        int min_od=Math.abs(max_x.get(0)-j);
                        int ind=0;
                        for(m=0;m<max_x.size();m++) {
                            if(Math.abs(max_x.get(m)-j)<min_od)
                            {
                                min_od=Math.abs(max_x.get(m)-j);
                                ind=m;

                            }

                        }
                        y_max=max_y.get(ind);


                        //sprawdzamy czy ilość wspólnych punktów wystarcza, aby uznać ten element za srokę
                        if (Math.abs(n - elements) < 20) {
                            x++;
                            n = 0;
                            //jesli tak to zachowujemy wspołrzędne punktów
                            if(tabx==null) {
                                tabxx = tabx;
                                tabyy = taby;
                            }
                            else{
                                for(m=0;m<tabx.size();m++)
                                {
                                    tabxx.add(tabx.get(m));
                                    tabyy.add(taby.get(m));
                                }
                            }
                        } else {

                            tabx.clear();
                            taby.clear();

                        }
                    }
                }


            }
        }
        //robimy próbe!
        ArrayList<ArrayList<Integer>> tmp=new ArrayList<>();
        for(k=0;k<512;k++)
            tmp.add(new ArrayList<>(100));
        for(i=0;i<512;i++) {
            for (j = 0; j < 512; j++) {
                if (tabxx.contains(j) && tabyy.get(tabxx.indexOf(j)) == i) {
                    tmp.get(i).add(1);
                } else tmp.get(i).add(0);
            }
        }
        try {
            DisplayImage(tmp, 512, 512, "trst");
        }catch(IOException e)
        {
            System.out.println("nn");
        }

        return x;
    }

    /**
     * funkcja pomocnicza czy dany punkt należy do funkcji
     * @param xs
     * @param
     * @param x
     * @return
     */
    public static boolean lagrangeInterpolation(ArrayList<Integer> xs, ArrayList<Integer> ys, int x, int y_pr){
        double t;
        double y = 0.0;

        for(int k = 0; k< xs.size(); k++){
            t = 1.0;
            for(int j = 0; j < xs.size() ; j++){
                if(j != k ){
                    t=t*((x-xs.get(j))/(double)(xs.get(k)-xs.get(j)));
                }
            }
            y += t*(ys.get(k));
        }
        if(Math.abs(y-y_pr)==0)
            return true;
        else return false;
    }

    /**
     *
     * @param tab-tablica dwu-wymiarowa wypełniona 0 i 1
     * @param n
     * @param m
     * @param name-nazwa pliku jaki chcemy wygenerować
     * @throws IOException
     */
    public void DisplayImage(ArrayList<ArrayList<Integer>> tab,int n,int m,String name)throws IOException
    {
        var image = new BufferedImage(512, 512, BufferedImage.TYPE_BYTE_GRAY);
        Color white = new Color(255, 255, 255);
        int rgb = white.getRGB();
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                if(tab.get(i).get(j)==1)
                    image.setRGB(j,i,rgb);
        ImageIO.write(image,"tif",new File(name+".tif"));
    }

    /**
     * wypełniamy tablicę int elementami 1 i 0 na podstawie wczytanego do programu obrazka- 0 jak jest czarne i 1- jak jest białe
     */
    private void ImageToArray() {
        int p, a, r, g;
        int i,j;
        inf=new ArrayList<>(img.getHeight());
        for (i=0; i<img.getHeight();i++) {
            inf.add(new ArrayList<>(img.getWidth()));
            for(j=0;j<img.getWidth();j++)
                inf.get(i).add(0);
        }
        System.out.println(img.getHeight());
        for (int x = 0; x < img.getHeight(); x++)
            for (int y = 0; y < img.getWidth(); y++) {
                p = img.getRGB(y, x);
                //get red
                r = (p >> 16) & 0xff;
                //get green
                g = (p >> 8) & 0xff;
                //get blue
                a = p & 0xff;
                if (r == 255 && a == 255 && g == 255) {
                    inf.get(x).set(y,1);
                } else {
                    inf.get(x).set(y,0);
                }
            }
    }

    /**
     * tutaj zliczamy liczbę kropek na sroce-na obrazku referencyjnym służącym za nasz model
     * oraz przechowamy współrzędne tych kropek
     * @return - zwraca liczbę tych kropek
     */
    private int GetMapy()
    {
        int amount=0;
        int i,j;

        store=new ArrayList<>(2);
        for (i=0; i<2;i++)
            store.add(new ArrayList<>());
        for(i=0;i<img.getHeight();i++)
        {
            for(j=0;j<img.getWidth();j++)
                if(inf.get(i).get(j)==1)
                {
                    store.get(0).add(j);
                    store.get(1).add(i);
                    amount+=1;
                }

        }
        return amount;
    }

}