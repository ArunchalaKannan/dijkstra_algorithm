import java.util.*;
class HelloWorld {
    public static int countPaths(int[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        int[][] dp = new int[rows][cols];

        dp[0][0] = (array[0][0] == 0) ? 1 : 0;

        for (int j = 1; j < cols; j++) {
            dp[0][j] = (array[0][j] == 0) ? dp[0][j - 1] : 0;
        }

        for (int i = 1; i < rows; i++) {
            dp[i][0] = (array[i][0] == 0) ? dp[i - 1][0] : 0;
        }
        
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (array[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }
       
        return dp[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        int[][] array = {
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0}
        };

        int ways = countPaths(array);
        System.out.println("Number of ways to reach the end: " + ways);
    }
}
