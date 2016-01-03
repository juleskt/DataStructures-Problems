import java.lang.*;
/*
    The whole idea of finding the median of two arrays is to take the median of both
    and compare them against eachother. For the array whose median is greater, take the lower half (remove indexes in front of the median,
    the number you remove is equal to half the other array. For the other, take the upper half. Keep taking the median until one array is sie 2
    and calculate the median from there. Otherwise, take care of the corner cases.

    Since this algorithm involes splitting n and m in half over and over (a modified binary search), it is O(log(n+m))
*/
public class MySolution 
{
    public int findMedianSortedArrays(int A[],int B[])
    {
    	int lenA = A.length;
    	int lenB = B.length;

    	//Cheese case, lenB MUST be even 1 2 3 4 5 6 7 8 9 10
    	if(lenA == 1)
    	{
    		//If A is bigger than all of B or less than all of B
    		if(A[0] > B[lenB-1])
    		{
    			return B[lenB/2];
    		}
    		else if(A[0] < B[0])
			{
				return B[(lenB/2)-1];
			}
    		//If A is somewhere in the middle
    		else
    		{
    			//Upper and lower median values for evens
    			int medBHigh = B[lenB/2];
    			int medBLow = B[(lenB/2)-1];

    			if(A[0] < medBLow)
    			{
    				return medBLow;
    			}
    			
    			else if(A[0] > medBHigh)
    			{
    				return medBHigh;
    			}
    			
    			else if(A[0] > medBLow && A[0] < medBHigh)
    			{
    				return A[0];
    			}
                else if(A[0] == medBLow || A[0] == medBHigh)
                {
                    return A[0];
                }
    		}
    	}
    	//Cheese case, lenA MUST be even
    	else if(lenB == 1)
    	{
    		//If B is bigger than all of A
    		if(B[0] >= A[lenB-1])
    		{
    			return A[lenA/2];
    		}
    		else if(B[0] <= A[0])
    		{
    			return A[(lenA/2)-1];
    		}
    		//If B is somewhere in the middle, adjust the median by one index based on where B is
    		else
    		{
    			//Upper and lower median values for evens 
    			int medAHigh = A[lenA/2];
    			int medALow = A[(lenA/2)-1];

    			if(B[0] < medALow)
                {
    				return medALow;
                }
    			else if(B[0] > medAHigh)
                {
    				return medAHigh;
                }	
    			else if(B[0] > medALow && A[0] < medAHigh)
                {
    				return B[0];
                }
                else if(B[0] == medALow || B[0] == medAHigh)
                {
                    return B[0];
                }
            }
    	}
    	//Entirety of A is smaller than B, find the median of the whole sequence
        else if(A[lenA-1] < B[0])
        {
            int medIndex = (int)(Math.ceil((double) ((lenA-1)+(lenB-1)) /2));

            if(medIndex > lenA-1)
            {
                return B[medIndex - lenA];
            }
            else if(medIndex > lenB-1)
            {
                return A[medIndex];
            }
        }
    	//Entirety of B is smaller than A, find the median of the whole sequence
    	else if(B[lenB-1] < A[0])
    	{
    		int medIndex = (int)Math.ceil((double) ((lenA-1)+(lenB-1)) /2);

    		if(medIndex > lenB-1)
    		{
    			return A[medIndex - lenB];
    		}
            else if(medIndex > lenA-1)
            {
                return B[medIndex];
            }
    	}
    	//Mixed, comparing the medians of both arrays and truncating parts of the arrays based on result
    	else
    	{
    		int total = lenA+lenB;
    		int aPtr, bPtr;
    		//If A is even
    		if(lenA % 2 == 0)
    		{
    			aPtr = lenA/2;
    			bPtr = (lenB-1)/2;
    			int choppedA = lenA;
    			int choppedB = lenB;
                int numCuts = 0;

                if( (double)B[bPtr] == (double) (A[aPtr] + A[aPtr-1])/2 )
                {
                    return B[bPtr];
                }

    			while(choppedA >= 1 && choppedB >= 1)
    			{
    				//Two elements left in even array
                    if(choppedA == 2)
                    {
                        double medA = (double)((A[aPtr-1]+A[aPtr])/2);
                        double medB = (double)((B[bPtr-1])+B[bPtr]/2);

                        if( medB > medA )
                        {
                            return A[aPtr+1];
                        }
                        else if( medB < medA )
                        {
                            return A[aPtr-1];
                        }
                        else if( medB == medA )
                        {
                            return (int)medB;
                        }
                    }
                    //One element left in odd array
                    if(choppedB == 2)
                    {
                        double medA = (double)((A[aPtr-1]+A[aPtr])/2);
                        double medB = (double)((B[bPtr-1])+B[bPtr]/2);
                        aPtr--;

                        //If B is between the medians
                        if( medB > medA )
                        {
                            return A[aPtr+1];
                        }
                        else if( medB < medA )
                        {
                            return A[aPtr-1];
                        }
                        else if( medB == medA )
                        {
                            return (int)medB;
                        }
                    }

                    //mid(A) > mid(B)
    				if((double) (A[aPtr] + A[aPtr-1])/2 > (double)B[bPtr])
    				{
                        aPtr -= (((lenB-1)-bPtr)/2);
                        choppedA -= (((lenB-1)-bPtr)/2);

    					bPtr += (((lenB-1)-bPtr)/2);
                        choppedB -= choppedB/2;
    				}
    				//mid(A) < mid(B)
    				else if((double) (A[aPtr] + A[aPtr-1])/2 < (double)B[bPtr])
                    {
                        numCuts++;
                        aPtr += ((lenA-aPtr)/2);
                        choppedA = choppedA/2;

                        if((lenA-aPtr)/2 == 0)
                        {
                            if(A[aPtr] > B[bPtr-numCuts]+1)
                            {
                                return B[bPtr-numCuts];
                            }
                            else if(A[aPtr] < B[bPtr-numCuts])
                            {
                                return B[aPtr-numCuts+1];
                            }
                            else if(A[aPtr] < B[bPtr-numCuts+1] && A[aPtr] > B[bPtr-numCuts])
                            {
                                return A[aPtr];
                            }
                            else if(A[aPtr] == B[bPtr-numCuts] || A[aPtr] == B[bPtr-numCuts+1])
                            {
                                return A[aPtr];
                            }
                        }

                        bPtr -= ((lenA)-aPtr)/2;
                    }
    			}
    		}
    		//If B is even
    		else
    		{
                bPtr = lenB/2;
                aPtr = (lenA-1)/2;
                int choppedB = lenB;
                int choppedA = lenA;
                int numCuts = 0;

                if( (double)A[aPtr] == (double) (B[bPtr] + B[bPtr-1])/2 )
                {
                    return A[aPtr];
                }

                while(choppedA >= 1 && choppedB >= 1)
                {
                    //Two elements left in even array
                    if(choppedB == 2)
                    {
                        double medB = (double)((B[bPtr-1]+B[bPtr])/2);
                        double medA = (double)((A[aPtr-1])+A[aPtr]/2);

                        if( medA > medB )
                        {
                            return B[bPtr+1];
                        }
                        else if( medA < medB )
                        {
                            return B[bPtr-1];
                        }
                        else if( medB == medA )
                        {
                            return (int)medB;
                        }
                    }
                    //Two elements left in odd array
                    if(choppedA == 2)
                    {
                        double medB = (double)((B[bPtr-1]+B[bPtr])/2); //LOL
                        double medA = (double)((A[bPtr-1])+A[bPtr]/2);
                        bPtr--;

                        //Calculating shifts
                        if( medA > medB )
                        {
                            return B[bPtr+1];
                        }
                        else if( medA < medB )
                        {
                            return B[bPtr-1];
                        }
                        else if( medB == medA )
                        {
                            return (int)medB;
                        }
                    }

                    //mid(B) > mid(A)
                    if((double) (B[bPtr] + B[bPtr-1])/2 > (double)A[bPtr])
                    {
                        bPtr -= (((lenA-1)-aPtr)/2);
                        choppedB -= (((lenA-1)-aPtr)/2);

                        aPtr += (((lenA-1)-aPtr)/2);
                        choppedA -= choppedA/2;
                    }
                    //mid(B) < mid(A)
                    else if((double) (B[bPtr] + B[bPtr-1])/2 < (double)A[aPtr])
                    {
                        numCuts++;
                        bPtr += ((lenB-bPtr)/2);
                        choppedB = choppedB/2;

                        if((lenB-bPtr)/2 == 0)
                        {
                            if(B[bPtr] > A[aPtr-numCuts]+1)
                            {
                                return A[aPtr-numCuts];
                            }
                            else if(B[bPtr] < A[aPtr-numCuts])
                            {
                                return A[aPtr-numCuts+1];
                            }
                            else if(B[bPtr] < A[aPtr-numCuts+1] && B[bPtr] > A[aPtr-numCuts])
                            {
                                return B[bPtr];
                            }
                            else if(B[bPtr] == A[aPtr-numCuts] || B[bPtr] == A[aPtr-numCuts+1])
                            {
                                return B[bPtr];
                            }
                        }

                        aPtr -= ((lenB)-bPtr)/2;
                    }
                }
    		}
    	}  
    	return 100000000;
    }
}