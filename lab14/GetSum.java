public class GetSum extends Numbers implements Runnable{
    private int suma=0;
    public void run()
    {
        try{
            if(tab.size()==0)
                Thread.sleep(10000);
        }catch(InterruptedException e)
        {
            System.out.println("Watek przerwany");
        }
        if(tab.size()!=0)
        {
            for(int i=0;i<tab.size();i++)
                suma+=tab.get(i);
        }
    }
    public int getSum()
    {
        return suma;
    }
}
