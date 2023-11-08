import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
public class SolveMaze {
    
    //tablica opisujaca labrynt
    ArrayList<ArrayList<Integer>> tab;
    //zapisywane wpolrzedne najkrotszej sciezki
    ArrayList<ArrayList<Integer>> paths;
    //wspolrzedne punktu startowego i koncowego
    int[] start = new int[2];
    int[] finish = new int[2];
    
    //zaladowanie labiryntu do tablicy
    private void loadMaze(String plik)
    {
        tab=new ArrayList<>();
        int i = 0;
        int n = 0;
        int j = 0;

        Path path = FileSystems.getDefault().getPath(".", plik);
        
        try (InputStream in = Files.newInputStream(path);
            
            BufferedReader reader =new BufferedReader(new InputStreamReader(in))) {
            String line = null;
          
            while ((line = reader.readLine()) != null) {
        
                tab.add(new ArrayList<>());
                while(n<line.length())
                {
                    if (line.charAt(n) == 'W')
                    {
                        tab.get(i).add(-1);
                        j++;
                    }
                    if (line.charAt(n) == 'C')
                    {
                        tab.get(i).add(0);
                        j++;
                    }
                    if(line.charAt(n)=='S')
                    {
                        tab.get(i).add(1);
                        start[0]=i;
                        start[1] = j;
                        j++;
                    }
                    if(line.charAt(n)=='F')
                    {
                        tab.get(i).add(-2);
                        finish[0] = i;
                        finish[1] = j;
                        j++;
                    }
                    n++;
                }
                n = 0;
                j = 0;
                i++;
            }   
        } catch (IOException x) {
            System.err.println(x);
        }

    }
    //konstrukor domyslny zaladujacy plik z labiryntem
    SolveMaze()
    {
        loadMaze("maze_txt.txt");
        
    }
    //metoda wyswietlajaca labirynt
    void DisplayLabirynt()
    {
         for(int i=0;i<tab.size();i++)
        {
            for(int j=0;j<tab.get(i).size();j++)
                System.out.print(tab.get(i).get(j)+"\t");
             System.out.println();
        }
    }
    //metoda pozwalajaca na ustalenie mozliwych sciezek
    public void makePaths()
    {

        numberAdjacent(start[0], start[1]);

    }
    /**
     * procedura ktora rekurencyjnie wyznacza wszystkie mozliwe drogi do celu
     * @param i
     * @param j
     */
    private void numberAdjacent(int i, int j){

               
            if (j < (tab.get(0).size() - 1)) {

                if (tab.get(i).get(j + 1) == -2) {
                    tab.get(i).set(j + 1, tab.get(i).get(j) + 1);
                    return;
                }
                if (tab.get(i).get(j + 1) == 0) {
                    tab.get(i).set(j + 1, tab.get(i).get(j) + 1);
                    numberAdjacent(i, j + 1);
                }
            }
            if (i < (tab.size() - 1)) {
                if (tab.get(i + 1).get(j) == -2) {
                     tab.get(i + 1).set(j, tab.get(i).get(j) + 1);
                    return;
                }
                if (tab.get(i + 1).get(j) == 0) {
                    tab.get(i + 1).set(j, tab.get(i).get(j) + 1);
                    numberAdjacent(i + 1, j);
                }
            }
            if (j != 0) {
                if (tab.get(i).get(j - 1) == -2) {
                        tab.get(i).set(j-1,tab.get(i).get(j)+1);
                        return;
                }
                    if(tab.get(i).get(j-1)==0)
                    {
                        tab.get(i).set(j-1,tab.get(i).get(j)+1);
                        numberAdjacent(i, j-1);
                  }
                }
            if (i != 0) {

                if (tab.get(i - 1).get(j) == -2) {
                        tab.get(i-1).set(j,tab.get(i).get(j)+1);
                        return;
                    }
                    if(tab.get(i-1).get(j)==0)
                    {
                        tab.get(i-1).set(j,tab.get(i).get(j)+1);
                           numberAdjacent(i-1,j);   
                    }

                }
               
                
            
        
    }
    /**
     * wykonwywanie wycofywania i zapis najkrotszej sciezki
     */
    public void backtrack()
    {
        paths = new ArrayList<>();
        rekpowrot(finish[0], finish[1]);

    }
  
    int z = 0;
    /**
     * procedura zapisujaca wspolrzedne najkrotszej sciezki do tablicy
     * @param i
     * @param j
     */
    void rekpowrot(int i,int j)
    {
        int el = tab.get(i).get(j);
        paths.add(new ArrayList<>());
        paths.get(z).add(i);
        paths.get(z).add(j);
        z++;
        if (j < (tab.get(0).size() - 1)) {

            if (tab.get(i).get(j + 1) == 1) {
                return;
            }
            if (tab.get(i).get(j + 1) == el - 1) {
                rekpowrot(i, j + 1);
            }
        }
        if (i < (tab.size() - 1)) {
            if (tab.get(i + 1).get(j) == 1) {
                return;
            }
            if (tab.get(i + 1).get(j) == el - 1) {
                rekpowrot(i + 1, j);
            }
        }
        if (j != 0) {
            if (tab.get(i).get(j - 1) == 1) {
                return;
            }
            if (tab.get(i).get(j - 1) == el - 1) {
                rekpowrot(i, j - 1);
            }
        }
        if (i != 0) {

            if (tab.get(i - 1).get(j) == 1) {
                return;
            }
            if (tab.get(i - 1).get(j) == el - 1) {
                rekpowrot(i - 1, j);
            }
        }
    }
    
    /**
     * procedura pozazujaca wspolrzedne najkrotszej drogi
     */

    public void readWay()
    {
        System.out.println("Wspolrzedne najkrotszej sciezki");
        for(int i=0;i<paths.size();i++)
        {
            for(int j=0;j<paths.get(i).size();j++)
                System.out.print(paths.get(i).get(j)+"\t");
             System.out.println();
        }
    }
}
