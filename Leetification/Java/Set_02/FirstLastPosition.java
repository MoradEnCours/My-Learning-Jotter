package Set_02;

import java.util.Arrays;

public class FirstLastPosition {
    public static void main(String[] args) {
        int[] array = { 5, 7, 7, 8, 8, 10 };
        int[] solution = searchRange(array, 8);
        System.out.println(Arrays.toString(solution));
    }

    public static int[] searchRange(int[] nums, int target) {
        /**
         * V-Storage: Store an array, with default {-1,-1}.
         */
        int[] ans = new int[] { -1, -1 };
        /**
         * In the case that an array is empty, the default answer should be returned.
         */
        if (nums.length == 0)
            return ans;
        /**
         * V-Storage: Store the 0th index and final index of the array.
         */
        int l = 0;
        int r = nums.length - 1;
        /**
         * C-Sequence: Traverse through the array, until the second last index.
         */
        while (l < r) {
            /**
             * Mid-sect: Take the middle index
             */
            int m = (l + r) / 2;
            /**
             * P-Check: In the case that the value of the middle index is equal to or
             * greater than the target value, then the rightmost pivot should be
             * repositioned to an index location holding a smaller value. Since the array is
             * sorted, the rightmost index should be placed where the middle pivot is.
             * Otherwise, if the value pertaining to the middle index is smaller than the
             * target value, then the left index should be repositioned one index-span to
             * the right of the middle index.
             */
            if (nums[m] >= target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        /**
         * Consideration: Should the value pertaining to the left index match with the
         * target value, then it should be stored in the answer, noting the index
         * number.
         */
        if (nums[l] == target) {
            ans[0] = l;
            /**
             * Otherwise, a false response should be returned.
             */
        } else {
            return ans;
        }
        /**
         * Currently: To arrive at this stage, the left index should have a value which
         * matches with the target. The right index is positioned be at the end.
         */
        r = nums.length - 1;
        /**
         * C-Sequence: From the 0th index until the second last.
         */
        while (l < r) {
            /**
             * Mid-sect: Though, a one is added in the evaluation.
             */
            int m = (l + r + 1) / 2;
            /**
             * Consideration: Should the middle index be less than or equal to the target,
             * then the search should shift to the right, with the left index repositions
             * into the place of the middle index. Note: The reason why the left index takes
             * the exact position of the middle index is due to using <= : there is the
             * potential that the target is equal to the value pertaining to the middle
             * index.
             */
            if (nums[m] <= target) {
                l = m;
                /**
                 * On the other hand, if the target is less than the value pertaining to the
                 * middle index, then the search should shift to the left of the middle index:
                 * in other words, the right index is repositioned one index-span to the left of
                 * the middle index.
                 */
            } else {
                r = m - 1;
            }
        }
        /**
         * Catchup: We need to check if the value pertaining to the right pivot matches
         * the target value. If it does, the answer will be complete, though, if not,
         * then it's a false negative.
         */
        if (nums[r] == target) {
            ans[1] = r;
        } else {
            return new int[] { -1, -1 };
        }
        return ans;
    }
}
