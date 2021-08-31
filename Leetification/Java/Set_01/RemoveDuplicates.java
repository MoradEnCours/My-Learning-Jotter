package Set_01;

import java.util.Arrays;

public class RemoveDuplicates {

    // TBR Unsure about the logic in this.
    public static void main(String[] args) {
        int[] arr = { 1, 1, 2 };
        int a = removeDuplicates(arr);
        System.out.println(a);
    }

    /*
     * Duplicates bubble to the right. Reason: The bubbling stops
     */
    public static int removeDuplicates(int[] nums) {
        /* Initialise the index to be 0. */
        int i = 0;
        /* F-Sequence through the number array */
        for (int num : nums) {
            /*
             * The 0 index is not considered since it's first to be scanned and thus there
             * are no predecessors before it. It will serve as the first value to be
             * accepted as unique. If a current indexed value afterwards is unique from its
             * predecessor, it will also be considered.
             */
            if (i < 1 || num != nums[i - 1]) {
                nums[i++] = num;
                System.out.println(num);
            }
            System.out.println(Arrays.toString(nums));
        }
        System.out.println(Arrays.toString(nums));
        return i;
    }
}
