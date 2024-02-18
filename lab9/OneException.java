public class OneException extends Exception{
    String powod;
    OneException(String cause)
    {
        powod=cause;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "syntax error: "+"\'"+powod+"\' expected";
    }
}
