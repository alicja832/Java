/**
 * klasa reprezentujaca ksiazke
 */
class Book extends LibraryItem{
    String Title;
    String Author;
    String Gentre;
    String Publisher;
    private int pagesNr = 100;
    public Book()
    {
        time=14;
        kwota=0.5;
    }

    /**
     * metoda ustawiajaca dane elementu
     * @param tile
     * @param author
     * @param gentre
     * @param publish
     */
    public void set_data(String tile,String author, String gentre,String publish)
    {
        Title=new String(tile);
        Author=new String(author);
        Gentre=new String(gentre);
        Publisher=new String(publish);
       
    }

    /**
     * wybiera odpowiednie dane
     * @param in
     */
    public void Load(String [] in)
    {
        if(in.length<5)
            set_data(in[0],in[1],in[2]," ");
        else
            set_data(in[0],in[1],in[2],in[4]);
    }
    public void print()
    {
        System.out.println("Title:"+Title+" Author: "+Author+" Gentre: "+Gentre+"Publisher "+Publisher);
    }

}
