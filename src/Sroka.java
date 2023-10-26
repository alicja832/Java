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
     * deklaracja tablicy ref-czyli odniesienia-nasza pierwotna sroka
     */
    private static ArrayList<ArrayList<Integer>> ref;
    BufferedImage img=new BufferedImage(100, 100, BufferedImage.TYPE_BYTE_GRAY);

    private ArrayList<ArrayList<Integer>> inf;
    public ArrayList<ArrayList<Integer>> store;
    static int elements=0;

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
        to_function(ref);
    }
    /**
     * method for load picture to instation of a class
     * @param name
     * @throws IOException
     */
    public final void LoadImage(String name)throws IOException
    {
        img=ImageIO.read(new File(name));
        ImageToArray();
    }

    public void change()
    {
        try {
            DisplayImage(inf, 68, 94,"mian");
        }catch(IOException e)
        {
            System.out.println("Coś poszło nie tak");
        }
    }

    /**
     * możemy podać jako argumenty puste tablice które będą uzupełnione ooo mamy najmniejszy do największy punkt  oraz dla każdego x znajdujemy y the highest!!!!
     * jak to zastosowac dla reszty ptaków lecimy pokolei po pikselach sekwencyjnie +1+1 kiedy
     * spotkamy na swojej drodze 1 do zaczynamy sprawdzac czy dany punkt
     * wpasowuje sie do funcji- gdy jest zero przerywamy  jesli nie jedziemy dalej-gdy
     * liczba się zgadza tyle co bylo w ref to konczymy i mowimy eureka-
     * ale to już sprawdzamy w tej z liczbami nie kolorami-
     * i własnie wybieramy -najwieksze x i najwieksze y dla tych x i
     * sprawdzamy czy sie zgadza
     * @param table-bedzie to nasza tablica ref
     */
    private void to_function(ArrayList<ArrayList<Integer>> table)
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
     * store-tablica współrzędnych białych pikseli w dużym obrazie
     *             własciwe liczenie srok
     */
    public int GetMapies()

    {
        int x=0,x_p=0,y_p=0;
        //tu będzie tablica z współrzędnymi/znowu ale może inną damy:
        ArrayList<Integer> tabx;
        ArrayList<Integer> taby;
        tabx=new ArrayList<>();
        taby=new ArrayList<>();
        ArrayList<Integer> tabxx=new ArrayList<>();
        ArrayList<Integer> tabyy=new ArrayList<>();
        int i,j,k,sroki=0,y_max=0,n=0,m;
        for(i=0;i<img.getHeight();i+=5) {
            n = 0;

            y_max = 1000;
            for (j = 0; j < img.getWidth(); j++) {
                y_max = 1000;
                if (store.get(0).contains(j)) {
                    for (k = 0; k < store.get(0).size(); k++)
                        if (store.get(0).get(k) == j && store.get(1).get(k) < y_max && store.get(1).get(k) > i) {
                            y_max = store.get(1).get(k);

                        }
                    //sprawdzimy teraz czy kolejny punkt jest punktem początku kolejnego zwierza
                    //tutaj chcemy przeszukać wszyskie elementy ktore zawierają tego x i  wyodrębnic tego co mają największy y
//                    if(lagrangeInterpolation(ref.get(0),ref.get(1),j,y_max)) {
////                        if(tabx.size()!=0) {
////                            if(j==(tabx.get(tabx.size()-1)+1)) {
////                                tabx.add(j);//czy j jest o 1 większy od porzedniego?
////                                taby.add(y_max);
////                                x++;
////                            }
////                        }
////                        else {
//                            tabx.add(j);//czy j jest o 1 większy od porzedniego?
//                            taby.add(y_max);
//                            x++;
//
//                       // }
//                    }


                    if (lagrangeInterpolation(ref.get(0), ref.get(1), j - x_p, y_max - y_p)) {
//                        if(tabx.size()!=0) {
//                            if(j==(tabx.get(tabx.size()-1)+1)) {
//                                tabx.add(j);//czy j jest o 1 większy od porzedniego?
//                                taby.add(y_max);
//                                x+
//                            }
//                        }
//                        else {
                        n++;
                        tabx.add(j);//czy j jest o 1 większy od porzedniego?
                        taby.add(y_max);

                        // }

                    }

                }
                if (tabx.size() != 0) {
                    if (tabx.get(tabx.size() - 1) < (j - 3)) {
                        x_p = j - ref.get(0).get(0);
                        y_p = y_max - ref.get(1).get(0);
                        if (Math.abs(n - elements) < 20) {
                            x++;
                            n = 0;
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
//                if(10<(x) && x<50) {
//                    sroki++;
//                    x=0;
//                }
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
     * wypełniamy tablicę int elementami 1 i 0- 0 jak jest czarne i 1- jak jest białe
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
     * @return
     */
    public int GetMapy()
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