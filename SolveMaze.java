import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
public class SolveMaze {
    
    //tablica opisujaca labrynt
    ArrayList<ArrayList<Integer>> tab;
    //wspolrzedne punktu startowego
    int[] start=new int[2];
    //zapisywane wpolrzedne najkrotszej sciezki
    ArrayList<ArrayList<Integer>> paths;
    //tutaj moja zmienna
    int s = 0;
    //zaladowanie labiryntu do tablicy
    private void loadMaze(String plik)
    {
        tab=new ArrayList<>();
        int i=0;
        int n=0;

        Path path = FileSystems.getDefault().getPath(".", plik);
        
        try (InputStream in = Files.newInputStream(path);
            
            BufferedReader reader =new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
        
                tab.add(new ArrayList<>());
                //System.out.println(line);
                while(n<line.length())
                {
                    if(line.charAt(n)=='W')
                        tab.get(i).add(-1);
                    if(line.charAt(n)=='C')
                        tab.get(i).add(0);
                    if(line.charAt(n)=='S')
                    {
                        tab.get(i).add(1);
                        start[0]=i;
                        start[1]=n;
                    }
                        if(line.charAt(n)=='F')
                        tab.get(i).add(-2);   
                    n++;
                }
                n=0;
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
        do
        {
            numberAdjacent();
            if(s>200)
            {
                break;
            }
        }
        while(tab.get(start[0]).get(start[1])!=(-2));
    }
    private void numberAdjacent(){
        s++;
        int i=start[0];
        int j=start[1];

            if(j<(tab.get(0).size()-1))
                if(tab.get(i).get(j+1)==0)
                {
                    tab.get(i).set(j+1,tab.get(i).get(j)+1);
                    start[0]=i;
                    start[1] = j + 1;
                    System.out.println("i: " + start[0]);
                     System.out.println("j: " + start[1]);
                  
                    if(tab.get(i).get(j+1)==-2)
                {
                    start[0]=i;
                    start[1]=j+1;
                }
                }
             if(i<(tab.size()-1))
                if(tab.get(i+1).get(j)==0)
                {
                    tab.get(i+1).set(j,tab.get(i).get(j)+1);
                    start[0]=i+1;
                    start[1] = j;
                      System.out.println("i: " + start[0]);
                     System.out.println("j: " + start[1]);
                  
                    
                    if(tab.get(i+1).get(j)==-2)
                {
                    start[0]=i+1;
                    start[1]=j;
                      
                }
                }
                 if(j!=0){
                    if(tab.get(i).get(j-1)==0)
                    {
                        tab.get(i).set(j-1,tab.get(i).get(j)+1);
                        start[0]=i;
                        start[1] = j - 1;
                          System.out.println("i: " + start[0]);
                     System.out.println("j: " + start[1]);
                  
                       
                    }
                        if(tab.get(i).get(j-1)==-2)
                {
                    start[0]=i;
                    start[1]=j-1;
                       
                }
                }
                if(i!=0){
                    if(tab.get(i-1).get(j)==0)
                    {
                        tab.get(i-1).set(j,tab.get(i).get(j)+1);
                        start[0]=i-1;
                        start[1] = j;
                          System.out.println("i: " + start[0]);
                     System.out.println("j: " + start[1]);
                  
                       
                    }
                    if(tab.get(i-1).get(j)==-2)
                {
                    start[0]=i-1;
                    start[1]=j;
                       
                }
                }
               
                
            
        
    }
    /**
     * wykonwywanie wycofywania i zapis najkrotszej sciezki
     */
    public void backtrack()
    {
      
    }
    
}
