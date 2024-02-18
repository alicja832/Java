public class TwoException extends Exception{

    char powod;
    TwoException(char cause)
    {
        powod=cause;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "runtime error: "+"\'"+powod+"\' undefined";
    }
    
}
