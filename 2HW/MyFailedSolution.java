package runningmediantwo;
import java.util.*;

//Attempted to use median of 5 algorithm, correct logic renders this approached useless...

public class MySolution
{
	static List<Double> theList = new ArrayList<Double>();

    public static double runningMedian(double input)
    {
    	//Add the new query to the list
        theList.add(input);
        //If the list has one element, return it
        if(theList.size() == 1)
        {
        	return theList.get(0);
        }
        //If the list has less than 5 elements, sort it and return
        else if(theList.size() <= 5)
        {
        	Collections.sort(theList);

        	int floorMed = (int) Math.floor(theList.size()/2);

        	if(theList.size()%2 == 1)
        	{
        		return theList.get(floorMed);
        	}
        	else
        	{
        		return ( (theList.get(floorMed) + theList.get(floorMed-1))/2.0);
        	}
        }
        else
        {
        	//If there is more than 5, run the median of medians algorithm
        	return findMedian( theList.toArray(new Double[theList.size()]), (theList.size()/2) + 1, 0,theList.size()-1);
        }
    }

    public static double findMedian(Double array[], int med, int min, int max)
    {
        System.out.print("Full Array: ");
        System.out.println(Arrays.toString(array));
    	//If the index range is the same, we've hit the median
    	if(min == max)
    	{
    		return array[max];
    	}
    	//Call partition to sort the index of median of medians
    	int k = partition(array,0,array.length-1);
        //Make sure we're in the correct range
        int len = k - min + 1;

        if(len == med)
        {
        	return array[k];
        }
        //If the returned index is higher than the median index, search left subarray
        if(len > med)
        {
        	return findMedian(array,med,min,k-1);
        }
        //If it is lower, search the right subarray
        else
        {
        	return findMedian(array,len,k+1,max);
        }
    }

    public static int partition(Double array[], int min, int max)
    {
    	//Median of medians, go!!
    	double pivot = getPivot(array, min, max);
        System.out.println("Median of medians: " + pivot);

    	//Find the sorted position of the pivot (like nuts and bolts!)
    	int sortedIndex = 0;
    	for(int x = 0; x < array.length; x++)
    	{
    		if(array[x] < pivot)
    		{
    			sortedIndex++;
    		}
    		else if(array[x] > pivot)
    		{
    			sortedIndex--;
    		}
    		else if(array[x] == pivot)
    		{
    			sortedIndex++;
    		}
    	}
    	return sortedIndex;
    }

    public static double getPivot(Double array[], int min, int max)
    {
    	if(max-min+1 <= 9)
        {
            Arrays.sort(array);

            /*if(array.length%2 == 1)
            {
                return array[array.length/2];
            }
            else
            {
                return ( array[array.length/2] + array[(array.length/2)-1]/2.0 );
            }*/

            return array[array.length/2];
        }

    	double fiveArr[] = null;
    	//Array to hold the medians of the subarrays
    	Double medians[] = new Double[(array.length/5)+1];
    	//Index of medians array that we will work with
    	int medIndex = 0;

    	while(min <= max)
    	{
    		int subSize = Math.min(5,max-min+1);
    		fiveArr = new double[subSize];

    		for(int x = 0; x < subSize && min < max+1; x++,min++)
    		{
    			fiveArr[x] = array[min];
    		}

            if(fiveArr.length%2 == 1)
            {
                medians[medIndex] = fiveArr[fiveArr.length/2];
                medIndex++;
            }
            else
            {
                medians[medIndex] = ( (fiveArr[fiveArr.length/2] + fiveArr[(fiveArr.length/2)-1])/2.0 );
                medIndex++;
            }

            System.out.println("Five Arr: " + Arrays.toString(fiveArr));

    		medIndex++;
   		}
        System.out.println("Median array: " + Arrays.toString(medians));
    	return getPivot(medians, 0, medians.length-1);
    }
}
