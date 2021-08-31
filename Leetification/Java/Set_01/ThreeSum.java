package Set_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// There's a bracket error: Revise this later

public class ThreeSum {
    public void threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; ++i) {
            /*
             * Condering indexes after the 0th, and the value index matches its
             * predecessor... if this condition is met, continue to the next loop sequence.
             */
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            /*
             * Set j to contain a value +1 greater than the current index. Set k to contain
             * a -1 size of the array
             */
            int j = i + 1;
            int k = nums.length - 1;
            /*
             * Sequence through: start with the index after i (j), and continue until the
             * end of the array (k)
             */
            while (j < k) {
                /*
                 * Sum together the following: Value of the currently indexed time Value of the
                 * next currently indexed time Value of the final indexed item
                 */
                int sum = nums[i] + nums[j] + nums[k];
                /*
                 * Remember: The goal is to get a sum of 0. If the sum result is 0: Append to
                 * the list our values used in the sum calculation. Remark: The values are
                 * translated from array to list. Shift j one position to the right, Shift k one
                 * position to the left. Note: The array is being squashing inwards.
                 */
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    /*
                     * Sequence through: from the proceeding index until the end of the array.
                     * Con-check that the current value of this proceeding j matches its
                     * predecessor. Shift j to the right one place if successful.
                     */
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    /*
                     * Sequencing through-W: from j till what was supposedly considered the end,
                     * con-checking that the penultimate value indexed by k matches with the final
                     * value. Shift k one position to the left if successful.
                     */
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                        /*
                         * But in the case that the sum is not 0: In this case, con-check if it's
                         * greater than 0
                         */
                    } // else if (sum > 0) {
                    /*
                     * Shift the penultimate index (k) one position to the left.
                     */
                    // k--;
                    // }
                    /*
                     * Sequencing through-W: move from the starting index (j) until the new
                     * premature end (k), con-checking that the value of the new premature index
                     * does not match with the value contained in its successor. Shift k one
                     * position to the left if successful.
                     */
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                        /* The else branch means the sum is less than 0. */
                    } // else {
                    /* Shift j one position to the right. */
                    // j++;
                    /*
                     * Sequencing through-W: from starting j to penultimate ending k, con-checking
                     * that the value in j matches with its predecessor. Shift j one position to the
                     * right if successful.
                     */
                } // while (j < k && nums[j] == nums[j-1]) {
                  // j++;
                  // }
                // }
            }
        }
    }
}
