class PartialSum implements Runnable{
    void stime()
    {
        t0=System.currentTimeMillis();
    }
    long getTime()
    {
        return System.currentTimeMillis()-t0;
    }
    public PartialSum(int tab[],int step)
    {
        liczby=tab;
        krok=step;
    }
    public void run()
    {
        for(int i=0;i<krok;i++)
        {
          csuma+=liczby[i];
        }
    }
    public int getSum()
    {
        return csuma;
    }
   
    private int liczby[];
    private int krok;
    private int csuma=0;
    private long t0=0;

}