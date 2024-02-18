public class Main {
    public static void main(String[] args) {
        Hamming a=new Hamming();
        HammingCleared b=new HammingCleared();
        int ile=0;
        int ile2=0;
        int ile3=0;

        try {
            ile=a.compare("Okas", "Ola");
            ile2=b.compare("O k","Ok");
            ile3=b.compare("O k\ni","Ok i");

            CheckPlagiarism check=new CheckPlagiarism();
            check.CompareFiles("Dom.txt","Domek.txt");
            
            check.raport("Dom");
            CheckPlagiarism check2=new CheckPlagiarism();
            check2.CompareFiles("Dom2.txt","Domek2.txt");
            check2.raport("Dom2");

        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        System.out.println(ile);
        System.out.println(ile2);
        System.out.println(ile3);

    }
}

