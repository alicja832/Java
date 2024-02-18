/**
 * User
 */
public interface User {

    /**
     * zmienna informujaca czy user oddaje na czas czy nie
     */
    int max=200;
    void respons();
    void wyp();
    void odd();
    boolean how_response();
    int check();
    int kara(double kwota);
    int ile_kary();
    void add_delay(int days);
    int get_delay();
    default boolean blokuj()
    {
        if(ile_kary()>max)
            return true;
        return false;
    }
    default void pokaz()
    {
        System.out.println("Stan uzytkownika:");
        System.out.println("Tego dnia stan konta uzytkownika to: "+check());
        System.out.println("Tego dnia stan kwota do zaplacenia przez uzytkownika to: "+ile_kary());
    }
    
};