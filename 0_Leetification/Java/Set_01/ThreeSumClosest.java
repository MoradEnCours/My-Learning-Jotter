package Set_01;

import java.util.Arrays;

public class ThreeSumClosest {

    public static void main(String[] args) {
        int[] arr = { -1, 2, 1, -4 };
        int a = threeSumClosest(arr, 1);
        System.out.println(a);

    }

    public static int threeSumClosest(int[] nums, int target) {
        /* Sort the array. */
        Arrays.sort(nums);
        /*
         * In a placeholder, store the sum of the three values found which give the
         * closest answer to the target.
         */
        int ans = nums[0] + nums[1] + nums[2];
        /* F-Sequence through the numeric array until the second last index. */
        for (int i = 0; i < nums.length - 2; ++i) {
            /* This condition checks if the array is of size 1 */
            if (i > 0 && nums[1] == nums[i - 1]) {
                continue;
            }
            /*
             * j acts as the succesor of the former index i: i's right buddy. k acts as the
             * end boundary, indexing at the end of the array.
             */
            int j = i + 1;
            int k = nums.length - 1;
            /* W-Sequence through from the rshifted index until the end of the array. */
            while (j < k) {
                /*
                 * Sum together the values contained in the: Predecessor index, Successor index,
                 * Final index
                 */
                int sum = nums[i] + nums[j] + nums[k];
                /*
                 * Con-check: Jf the new sum is closer to 0 than the current ans, replace the
                 * current answer with this new sum. Logic: By using the absolute value and
                 * seeing which calculation leans closer to 0, it's clear that the value closer
                 * is what is closer to the target in terms of the sum value given.
                 */
                if (Math.abs(sum - target) < Math.abs(ans - target)) {
                    ans = sum;
                }
                /*
                 * Recall: Earlier the array was sorted so it can be deduced that, since the
                 * former sum value (note, this is a slight assumption) was further from 0
                 * (meaning it was greater than the current sum), the intention is to now try
                 * with smaller values. Shifting to left of k, the size of the index values
                 * decrease as the array is sorted.
                 */
                if (sum > target) {
                    k--;
                    /*
                     * W-Sequence from starting index j until the decremented index x, but
                     * essentially in this case, if the new value being indexed by k is the same as
                     * the predecessor value, simply shift k to the left as the value won't change
                     * the sum - it's trivial.
                     */
                    while (j < k && nums[k - 1] == nums[k]) {
                        k--;
                    }
                    /*
                     * In the final case, it appears that sum and the target are equal in value. So
                     * try shifting j to the right, leaving index k as it is. Notice: In this else
                     * branch and the previous if-else branch, we increment one before we run the
                     * while loop, as we do not want to reconsider the already considered indexed
                     * value. Also, for the same reason as with k previously, we don't consider
                     * index with a value equal to the predessor's.
                     */
                } else {
                    j++;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                }
            }
        }
        /* Once the F-Sequence is over, we simply return the answer */
        return ans;
    }
}
