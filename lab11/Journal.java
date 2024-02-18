/**
 * klasa reprezentujaca czasoppismo
 */
class Journal extends LibraryItem{
    String eISSN;
    String Latestissue;
    String Publisher;
    String JournalURL;
    public Journal(){
        time=3;
        time_two=7;
        kwota=2.0;
    }

    /**
     *
     * @param eI
     * @param Late
     * @param pub
     * @param JouralU
     * ustawia dane czasopisma
     */

    public void set_data(String eI, String Late,String pub,String JouralU)
    {
        eISSN=eI;
        Latestissue=Late;
        Publisher=pub;
        JournalURL=JouralU;

    }

    /**
     *
     * @param in
     * wybieranie odpowiednich danych z pliku csv
     */
    public void Load(String [] in)
    {
        set_data(in[3],in[6],in[4],in[12]);
    }
    public void print()
    {
        System.out.println("eISSN:"+eISSN+" Author: "+Latestissue+"Publisher: "+Publisher+"JournalURL: "+JournalURL);
    }
}
