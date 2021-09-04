package Set_02;

public class SearchInsert {

    public static void main(String[] args) {
        int[] array = { 1, 3, 5, 6 };
        int solution = searchInsert(array, 5);
        System.out.println(solution);
    }

    public static int searchInsert(int[] nums, int target) {
        /**
         * V-Storage: Store 0th index and final index of array.
         */
        int l = 0;
        int r = nums.length - 1;
        /**
         * C-Sequence: Traverse through the entire array.
         */
        while (l <= r) {
            /**
             * Mid-sect: Set up a middle index.
             */
            int m = (l + r) / 2;
            /**
             * Consideration: Should the middle index's value match with that of the target,
             * then the index shall be returned.
             */
            if (nums[m] == target)
                return m;
            /**
             * Consideration: Should the value pertaining to the middle index be smaller
             * than the target value, then the left index should be relocated one
             * index-position to the right of the middle index.
             */
            if (nums[m] < target) {
                l = m + 1;
                /**
                 * On the other hand, if value pertaining to the middle index is greater than
                 * target value, the right index should be relocated one index-span to the left
                 * of the middle index.
                 */
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    /**
     * Original idea: To search the index position of a target value in the array, a
     * for loop could be used. A simple conditional check could verify if a value
     * pertaining to an index matches the target value. Though, in the meanwhile,
     * the target value could be one of many: Assuming the existence of three
     * pivots: left, middle and right, - The target could be less than the 0th
     * index's value; - The target could be greater than the final index's value; -
     * The target could lie between the left index and middle one or The target
     * could lie between the middle index and right one. Perhaps: ? Is the value
     * before the target less than it ? Is the value after the target greater than
     * it ? Does this target value exist in this currently observed index
     */
}
