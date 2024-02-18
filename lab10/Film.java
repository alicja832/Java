/**
 * klasa reprezentujaca film
 */
class Film extends LibraryItem{
    String Title;
    String Director;
    String Year;
    String Runtime;
    String Rating;
    void draw(){}
    public Film(){
        time=2;
        kwota=5.0;
    }

    /**
     *
     * @param tit
     * @param dir
     * @param year
     * @param run
     * @param rat
     * metoda ustawiajaca dane elementu w bibliotece
     */
    public void set_data(String tit,String dir,String year,String run,String rat )
    {
        Title=tit;
        Director=dir;
        Year=year;
        Runtime=run;
        Rating=rat;
    }

    /**
     * wybiera odpowiednie dane z pliku
     * @param in
     */
    public void Load(String [] in)
    {
        set_data(in[1],in[4],in[6],in[7],in[8]);
    }
    public void print()
    {
        System.out.println("Title:"+Title+" Director: "+Director+" Year: "+Year+"Runtime "+Runtime+"Rating"+Rating);
    }
}
