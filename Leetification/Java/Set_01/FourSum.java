package Set_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    public static void main(String[] args) {
        int[] arrayToSum = { 1, 0, -1, 0, -2, 2 };
        List<List<Integer>> a = fourSum(arrayToSum, 0);
        System.out.println(a);
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        /* Create a list to store lists of integers */
        List<List<Integer>> ans = new ArrayList<>();
        /* Sort the number array */
        Arrays.sort(nums);
        /* Sequence: from the first array element to the third last */
        for (int i = 0; i < nums.length - 3; ++i) {
            /*
             * In the case where an index, which is not the 0th one, indexes a value that is
             * to its predecessor, an iteration is skipped for this index.
             */
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            /*
             * Second sequence: from the first array index after the first sequence until
             * the second last index of the array.
             */
            for (int j = i + 1; j < nums.length - 2; ++j) {
                /**
                 * In the case where index j is two index-spans from index i (to the right of
                 * it), and the value pertaining to index j matches with the value pertaining to
                 * the index one index-span to the left of it (i.e. index i), simply continue to
                 * the next iteration of the sequence.
                 */
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                /**
                 * V-Storage: - Store the index value one-right-index-span from j. - Store the
                 * last index value.
                 */
                int l = j + 1;
                int r = nums.length - 1;
                /**
                 * C-Sequence: from the index position after j until the last index of the
                 * array.
                 */
                while (l < r) {
                    /**
                     * Summify the values pertaining to the four indexes.
                     */
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    /**
                     * Validation: Check if the sum matches the original target. In the case that it
                     * does, append the four values pertaining to the indeces to the list. Note: The
                     * conversion of an array into a list.
                     */
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        /**
                         * After a successful match, next the indexes l and r converge inwards to filter
                         * down. Explanation: As for why r is not left unchanged, the reason comes from
                         * the fact that the array has been sorted. So, if we are moving the index to
                         * the left, then one of the numbers in the sum could be greater, and thus the
                         * overall sum augments. As such, to counter against this, a smaller value of r
                         * is needed, therefore why slide the r index to the left.
                         */
                        l++;
                        r--;
                        /**
                         * Filler bypass: There's no use in considering values pertaining to indices
                         * which are the same as predecessors. Thus, they can be skipped. Also, indices
                         * l and r should not overlap since it's not fair to consider the same number
                         * twice.
                         */
                        while (l < r && nums[l] == nums[l - 1])
                            l++;
                        while (l < r && nums[r] == nums[r + 1])
                            r--;
                        /**
                         * Situation B: In the case that the sum is greater than the target, index r
                         * needs to shift leftwards in order to try with smaller values.
                         */
                    } else if (sum > target) {
                        r--;
                        /**
                         * Filter bypass:
                         */
                        while (l < r && nums[r] == nums[r + 1])
                            r--;
                        /**
                         * Situation C: In the case that the sum is less than the target, index l needs
                         * to shift rightwards in order to try with bigger values.
                         */
                    } else {
                        l++;
                        /**
                         * Filter bypass:
                         */
                        while (l < r && nums[l] == nums[l - 1])
                            l++;
                    }
                }
            }
        }
        return ans;
    }
}
