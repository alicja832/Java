import java.io.IOException;
import java.io.InputStreamReader;

public class GetNumbers extends Numbers implements Runnable{
    
    public GetNumbers()
    {
        
    }
    public void run()
    {
        int el;
        try{
            InputStreamReader input=new InputStreamReader(System.in);
            while(!((el=input.read())<48))
            {
                el=Integer.valueOf(el-48);
                System.out.println("Podana liczba: "+ el);
                tab.add(el);
            }
        }
        catch(IOException e)
        {
            System.out.println("IOException");
        }
      

    }
    
}
