public class Main
{
    public static void main(String args[])
    {
        SolveMaze solve=new SolveMaze();
        solve.DisplayLabirynt();
        solve.makePaths();
        solve.backtrack();
        solve.readWay();
    }
}
