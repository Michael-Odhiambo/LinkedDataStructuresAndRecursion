

public class QuickSort {


    public static void main( String[] args ) {

        int[] A = { 23, 10, 7, 45, 16, 86, 56, 2, 31, 18 };

        quickSort( A, 0, A.length - 1 );

        for ( int i = 0; i < A.length; i++ )
            System.out.println( A[i] );

    }

    private static void quickSort( int[] A, int lo, int hi ) {

        if ( hi > lo ) {

            int pivot = quickStep( A, lo, hi );
            quickSort( A, lo, pivot - 1 );
            quickSort( A, pivot + 1, hi );
        }
    }



    private static int quickStep( int[] A, int lo, int hi ) {


        // Randomly select a pivot value from the array.
        // int pivotIndex = (int)( A.length * Math.random() );
        int pivot = A[ lo ];  // pivot element.

        // There should be atleast two items in the array.
        while ( hi > lo ) {

            while ( hi > lo && A[hi] > pivot )
                hi--;

            if ( hi == lo )
                break;

            // At this point, we know for sure that the value A[hi] is less than the pivot, so we need to move
            // it to the index position at "lo".
            A[lo] = A[hi];
            lo++;

            while ( hi > lo && A[lo] < pivot )
                lo++;

            if ( hi == lo )
                break;

            // At this point, we know for sure that the value A[lo] is greater than the pivot, so we need to move it
            // to the index position at "hi";
            A[hi] = A[lo];
            hi--;


        }

        // At this point, we are sure that hi == lo.
        A[lo] = pivot;
        return lo;
    }
}
