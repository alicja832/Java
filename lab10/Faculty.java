public  class Faculty implements User {
    public boolean respons=true;
    public boolean wyp=false;
    private int l=0;
    private int kara=0;
    private int delay=0;
    public void add_delay(int days)
    {
        delay+=days;
    }
    public int get_delay()
    {
        return delay;
    }
    public void respons()
    {
        respons=false;
    }
    public boolean how_response()
    {
        return respons;
    }
    public void wyp()
    {
        l+=1;
        wyp=true;
    }
    public void odd()
    {
        l-=1;
        wyp=false;
    }
    public int check()
    {
        return l;
    }
    public int kara(double kwota)
    {
        kara+=kwota;
        return kara;
    }
    public int ile_kary()
    {
        return kara;
    }
    Faculty(){}
}