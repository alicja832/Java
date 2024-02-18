class Hamming {
    public int compare(String one, String two)throws Exception
    {
        int x=0;
        int len_one=one.length();
        int len_two=two.length();

        if((len_one==0) || (len_two==0))
            throw new Exception("Puste wyrazy"+two);
        
        int len=len_one;
        if(len_one<len_two)
        {
            x=len_two-len_one;
        }
        if(len_two<len_one)
        {
            x=len_one-len_two;
            len=len_two;
        }
        for( int i=0;i<len;i++)
        {
            if(one.charAt(i)!=two.charAt(i))
                ++x;
        }
        return x;
    }
}
