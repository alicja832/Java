// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.ArrayList;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        Sroka sroka=new Sroka();
        sroka.change();
        try{
            sroka.LoadImage("test_image.tif");
        }catch(IOException e)
        {
            System.out.println("Nie znaleziono obrazka");
        }
        try {
            ArrayList<ArrayList<Integer>> tab = new ArrayList<>(100);
            for (int i = 0; i < 100; i++) {
                tab.add(new ArrayList<>());
                for (int j = 0; j < 100; j++) {
                    if (j % 5 != 0)
                        tab.get(i).add(1);
                    else tab.get(i).add(0);
                }

            }
            sroka.DisplayImage(tab,100,100,"plik");
        }catch(IOException x)
            {
             System.out.println("Nie utworzono obrazka");
            }
        sroka.GetMapy();

        System.out.println(sroka.GetMapies());
    }
}