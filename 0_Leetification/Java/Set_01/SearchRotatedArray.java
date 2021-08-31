package Set_01;

public class SearchRotatedArray {

    // Casse-tete
    public static void main(String[] args) {
        int[] arr = { 4, 5, 6, 7, 0, 1, 2 };
        int a = search(arr, 0);
        System.out.println(a);
    }

    public static int search(int[] nums, int target) {
        /**
         * V-Storage: - Store the 0th index of the array. - Store the final index of the
         * array.
         */
        int l = 0;
        int r = nums.length - 1;
        /**
         * C-Sequence: Through the entire array. Mid-sect: Store the middle index
         */
        while (l <= r) {
            int m = (l + r) / 2;
            /**
             * Validation: Check if the midpoint matches the target In the case that it
             * does, return index that pertains to the value.
             */
            if (nums[m] == target)
                return m;
            /**
             * Consideration: Check if the value of the midpoint is greater than or equal to
             * the leftmost index's value, then check if the target is equal to or greater
             * than the leftmost index value but smaller than middle index's. If this is the
             * case, then the rightmost index shall be adjusted and placed now one
             * index-span to the left of the middle index. Otherwise, the rightmost index
             * shall be placed one index-span to the right of the middle index.
             */
            if (nums[m] >= nums[l]) {
                if (nums[l] <= target && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else {
                /**
                 * The second case arrives when the value of the middle index is smaller than
                 * the value pertaining to the left index. Consideration: In the case that value
                 * pertaining to the middle index is lower than the target, and the target value
                 * is smaller than the value pertaining to the rightmost index, the leftmost
                 * index should be repositioned one index-span to the right of the middle index.
                 */
                if (nums[m] < target && target < nums[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return -1;
    }
}
