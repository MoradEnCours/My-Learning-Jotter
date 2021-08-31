package Set_01;

public class ContainerMostWater {

    public static void main(String[] args) {
        int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        int a = maxArea(height);
        System.out.println(a);
    }

    /* Feed in an array of heights */
    public static int maxArea(int[] height) {
        /*
         * Consider an abstract starting left position that has value 0.
         */
        int l = 0;
        /* Store the number of heights listed. */
        int r = height.length - 1;
        /* Initialise an answer: currently, it is 0. */
        int ans = 0;
        /* Sequence through the array till the end is reached */
        while (l < r) {
            /*
             * Two comparisons exist: First comparant: The present value stored in answer.
             * Second comparant: A decremented size of the array multiplied by the minimum
             * found between the first and last value. Either the already existing answer
             * value remains or a new one which is greater takes its place.
             */
            ans = Math.max(ans, (r - 1) * Math.min(height[l], height[r]));
            /*
             * If the value on the left is less than the value on the far right, shift the
             * left-reach index one position to the right. Otherwise, shift the rightmost
             * index to the left. Remember: We are seeking a greater value because we are
             * looking to obtain the maximum area possible.
             */
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        /*
         * Here, the while loop has terminated as the end of the array has been reached.
         * Thus, an answer is now returned.
         */
        return ans;
    }
}
