import java.util.ArrayList;
public class HammingCleared extends Hamming{

    private String clear(String el)
    {
        StringBuilder s=new StringBuilder();
        for(int i=0;i<el.length();i++) {
            if (el.charAt(i) == ' ' || el.charAt(i) == '_' || el.charAt(i) == '\n') {
            
            } else {
               
               s.append(el.charAt(i));
            }
        }
        
        return s.toString();
    }
    public int compare(String one, String two)throws Exception
    {
        String newone=clear(one);
        String newtwo=clear(two);
     
        return super.compare(newone,newtwo);
    }


}
