import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CheckPlagiarism {
    
   static int prog=10;
   int identic=0;
   double avg=0;
   static void SetProg(int p){
        prog=p;
   }
    public String CompareFiles(String fileone, String filetwo) throws Exception
    {

        try( 

            BufferedReader file_one=new BufferedReader(new InputStreamReader(new FileInputStream( fileone )))
        )
        {
            try(
            BufferedReader file_two=new BufferedReader(new InputStreamReader(new FileInputStream( filetwo )))){
            String line;
            String line_two;
            int hamming=0;
            int ilosc=0;
            int count1=0;
            int count2=0;
            HammingCleared b=new HammingCleared();
            file_one.mark(1000);
            file_two.mark(1000);
                while((line=file_one.readLine())!=null)
            {
                   
                    count1++;

            }
             while((line=file_two.readLine())!=null) {
                
                 count2++;
             }
             System.out.println(count1);
             System.out.println(count2);
            if(count1==0 || count2==0)
                return "Plik jest pusty";

            file_one.reset();
            file_two.reset();
            file_one.mark(1000);
            file_two.mark(1000);

            if(count1>count2)
            {
                
                while((line=file_one.readLine())!=null)
                {
                    
                       while((line_two=file_two.readLine())!=null)
                        {
                            try{
                            hamming+=b.compare(line, line_two);
                            }catch(Exception e)
                            {
                                System.out.println(e.toString());
                            }
                            if(b.compare(line, line_two)==0) identic++;

                            ilosc++;
                        }
                }
                
            }
            else if(count1<count2){

               while((line=file_two.readLine())!=null)
                {
                       while((line_two=file_one.readLine())!=null)
                        {
                            try{
                            hamming+=b.compare(line, line_two);
                            }catch(Exception e)
                            {
                                System.out.println(e.toString());
                            }
                            if(b.compare(line, line_two)==0) identic++;

                            ilosc++;
                        }
                }

            }
            else
            {
               
                while((line=file_two.readLine())!=null)
                {   
                       line_two=file_one.readLine();
                        
                            hamming+=b.compare(line, line_two);
                            ilosc++;
                }
            }
            file_one.close();
            file_two.close();
            avg=(double)hamming/ilosc;
            if(avg>prog)
            {
                return "Wykryto plagiat!";
            }
        }catch(IOException e)
        {
            throw new Exception("Bład w otwieraniu plikow");
        }
            
        }catch(IOException e)
        {
            throw new Exception("Bład w otwieraniu plikow");
        }
         return "Nie wykryto plagiatu!";
    }
    public void raport(String name)throws IOException
    {
        FileWriter a=new FileWriter("Raport"+name+".txt");
        a.write("Srednia hamming:"+avg+"\n");
        a.write("Liczba tych samych wierszy:"+identic+"\n");
        a.close();
    }
    
    
}
