/*
	a) 1005.0
	b) 0.0
	c) 5000379.5
*/

package runningmediantwo;
import java.util.*;

/*Using a max and min heap to track elements around the median
	Calculate new median based on index shifting per insert
	Heap insert is log n
	With n insertions, worst case complexity is nlogn
*/
public class MySolution
{
	static double median;
	static boolean called = false;
	static int total;
	static PriorityQueue<Double> minHeap = new PriorityQueue<Double>();
	static PriorityQueue<Double> maxHeap = new PriorityQueue<Double>(11,Collections.reverseOrder());
	static int minSize;
	static int maxSize;
    
    public static double runningMedian(double input)
    {
        minSize = minHeap.size();
        maxSize = maxHeap.size();

        if(!called)
        {
        	median = input;
        	minHeap.add(input);
        	called = true;
        }
        else
        {
        	if(minSize > maxSize)
        	{
        		if(input < median)
        		{
        			maxHeap.add(input);
        		}
        		else
        		{
        			minHeap.add(input);
        			maxHeap.add(minHeap.poll());
        		}
        	}
        	else if(minSize == maxSize)
        	{
        		if(input < median)
        		{
        			maxHeap.add(input);
        		}
        		else
        		{
        			minHeap.add(input);
        			maxHeap.add(minHeap.poll());
        		}
        	}
        	else if(minSize < maxSize)
        	{
        		if(input < median)
        		{
        			maxHeap.add(input);
        			minHeap.add(maxHeap.poll());
        		}
        		else
        		{
        			minHeap.add(input);
        		}
        	}
        }

        minSize = minHeap.size();
        maxSize = maxHeap.size();

        if(maxHeap.peek() != null)
        {
	        if(minSize == maxSize)
	        {
	     		median = (minHeap.peek() + maxHeap.peek())/2.0;
	        }
	        else if(minSize > maxSize)
	        {
	        	median = maxHeap.peek();
	        }
	        else if(minSize < maxSize)
	        {
	        	median = maxHeap.peek();
	        }
	    }
 		return median;
    }
}