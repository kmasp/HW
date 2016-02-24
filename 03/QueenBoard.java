//Kyle Moon
//APCS2 -- pd10
//HW03 -- Execution
//2016-02-23

/*
 * class QueenBoard
 * Generates solutions for N-Queens problem.
 */

public class QueenBoard {

    private int[][] _board;
    private int size;

    public QueenBoard( int s )
    {
        _board = new int[s][s];
        size = s;
    }

    /*
     * precondition: board is filled with 0's only.
     * postcondition:
     * If a solution is found, board shows position of N queens,
     * returns true.
     * If no solution, board is filled with 0's,
     * returns false.
     */
    public boolean solve()
    {
        return solveH(0);
    }

    /*
     *Helper method for solve.
     */
    private boolean solveH( int col )
    {
        if (col == size)
        {
            return true;
        }
        for (int row = 0; row < size; row++)
        {
            if (addQueen(row, col))
            {
                addQueen(row, col);
                if (solveH(col + 1))
                {
                    return true;
                }
                removeQueen(row, col);
            }
        }
        return false;
    }


    public void printSolution()
    {
        /* Print board, a la toString...
           Except:
           all negs and 0's replaced with underscore
           all 1's replaced with 'Q'
        */
        String s = "";
        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                if (_board[row][col] == 1)
                {
                    s += "Q";
                }
                else
                {
                    s += "-";
                }
                s += "  ";
            }
            s += "\n";
        }
        System.out.println(s);
    }



    //================= YE OLDE SEPARATOR =================

    /*
     * <General description>
     * precondition:
     * postcondition:
     */
    private boolean addQueen(int row, int col)
    {
        if(_board[row][col] != 0)
        {
            return false;
        }
        _board[row][col] = 1;
        int offset = 1;
        while(col + offset < _board[row].length)
        {
            _board[row][col+offset]--;
            if(row - offset >= 0)
            {
                _board[row-offset][col+offset]--;
            }
            if(row + offset < _board.length){
                _board[row+offset][col+offset]--;
            }
            offset++;
        }
        return true;
    }


    /*
     * <General description>
     * precondition:
     * postcondition:
     */
    private boolean removeQueen(int row, int col){
        if ( _board[row][col] != 1 ) {
            return false;
        }
        _board[row][col] = 0;
        int offset = 1;

        while( col+offset < _board[row].length ) {
            _board[row][col+offset]++;
            if( row - offset >= 0 ) {
                _board[row-offset][col+offset]++;
            }
            if( row + offset < _board.length ) {
                _board[row+offset][col+offset]++;
            }
            offset++;
        }
        return true;
    }


    /*
     * <General description>
     * precondition:
     * postcondition:
     */
    public String toString()
    {
        String ans = "";
        for( int r = 0; r < _board.length; r++ ) {
            for( int c = 0; c < _board[0].length; c++ ) {
                ans += _board[r][c]+"  ";
            }
            ans += "\n";
        }
        return ans;
    }


    //main method for testing...
    public static void main( String[] args )
    {
        /*
        QueenBoard b = new QueenBoard(5);
        System.out.println(b);
        b.solve();
        b.printSolution();
        */
        for (int i = 4; i < 11; i++)
        {
            QueenBoard b = new QueenBoard(i);
            System.out.println("Testing " + i + "x" + i + ":\n");
            b.printSolution();
            System.out.println("Solving " + i + "x" + i + ":\n");
            b.solve();
            b.printSolution();
            System.out.println("\n\n");
        }
    }

}//end class
