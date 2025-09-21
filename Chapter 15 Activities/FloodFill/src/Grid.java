import java.util.Stack;

public class Grid
{
    private static final int SIZE = 10;
    int[][] pixels = new int[SIZE][SIZE];
    

    /**
     * Flood fill, starting with the given row and column.
    */
    public void floodfill(int row, int column)
    {
        Stack<Integer> stack = new Stack<>(); // Stack to hold the rows and columns as integers
        int counter = 1; // Counter to fill in the pixels

        stack.push(row); // First row
        stack.push(column); // First column

        while (!stack.isEmpty()) {
            int col = stack.pop(); // pops the comumn first
            int r = stack.pop(); // pops the row second

            // Check if the rows and columbs are filled
            if (r >=0 && r < SIZE && col >= 0 && col < SIZE && pixels[r][col] == 0) {
                pixels[r][col] = counter; // Fill the pixel
                counter++; // Increment the counter
                // Okay so the rows will go top to bottom and the columns will go left to right
                stack.push(r);
                stack.push(col+1);
                stack.push(r);
                stack.push(col-1);
                stack.push(r+1);
                stack.push(col );
                stack.push(r-1);
                stack.push(col);
                // This is really confusing I wish I could visualize it better
            }
        }
    }

    @Override
    public String toString()
    {
        String r = "";
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
                r = r + String.format("%4d", pixels[i][j]);
            r = r + "\n";
        }
        return r;
    }
}
