package runningmediantwo;

public class RunningMedianTwo
{
    public static void main(String[] args)
    {
        MySolution sol = new MySolution();
        //testing
       /* for (int ii=0; ii <= 1000944; ii++)
        {
        	System.out.println(sol.runningMedian( (double) (ii%1000)/Math.sin(ii)));
        }*/

        System.out.println(sol.runningMedian(4.0));
        System.out.println(sol.runningMedian(6.0));
        System.out.println(sol.runningMedian(5.0));

    }                                                    
}
