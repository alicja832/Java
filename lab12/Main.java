import java.util.ArrayList;

public class Main{
    public static void main(String args[]) throws Exception
    {
      Add<Integer,Double> add=new Add<Integer,Double>(3, 3.5);
      System.out.println(add.add());
      Comp< Character> s=new Comp<Character>();
      s.add('a');
      s.add('z');
      s.add('b');
      s.sortB();
      s.wypisz();

      Comp<Integer> compp=new Comp<Integer>();
      compp.add(3);
      compp.add(2);
      compp.add(1);
      compp.sortB();
      compp.wypisz();

      Comp<Double> comp=new Comp<Double>();
      comp.add(3.0);
      comp.add(2.0);
      comp.add(1.0);
      comp.sortB();
      comp.wypisz();

      Comp<String> c=new Comp<String>();
      c.add("vvv");
      c.add("ww");
      c.add("aa");
      c.sortB();
      c.wypisz();

      Comp<ElS> el=new Comp<ElS>();
      el.add(new ElS("vvv"));
      el.add(new ElS("ww"));
      el.add(new ElS("a"));
      el.sortB();
      el.wypisz();

      Comp<ElI> eli=new Comp<ElI>();
      eli.add(new ElI(101));
      eli.add(new ElI(1001));
      eli.add(new ElI(1));
      eli.sortB();
      eli.wypisz();

      Sum<Integer> sum1=new Sum<>();
      ArrayList<Integer> array=new ArrayList<Integer>();
      array.add(1);
      array.add(8);
      array.add(10);
      System.out.println(sum1.toSum(array));

      Sum<Double> sum2=new Sum<>();
      ArrayList<Double> array2=new ArrayList<Double>();
      array2.add(1.0);
      array2.add(8.0);
      array2.add(10.0);
      System.out.println(sum2.toSum(array2));
    } 
}

