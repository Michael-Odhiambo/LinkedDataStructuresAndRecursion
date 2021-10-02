
public class TowerOfHanoi {

    public static void main( String[] args ) {
        move( 3, 0, 2, 1 );
    }

    private static void move( int disks, int from, int to, int through ) {
        if ( disks == 1 ) {
            System.out.println( from + "  ->  " + to );
        }
        else {
            move( disks - 1, from, through, to );
            System.out.println( from + "  ->  " + to );
            move( disks - 1, through, to, from );
        }
    }
}