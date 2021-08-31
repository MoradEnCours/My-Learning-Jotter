package Set_01;

import java.util.Arrays;

// ???

public class RemoveElements {
    public static void main(String[] args) {
        int[] arr = { 3, 2, 2, 3 };
        int a = removeElement(arr, 2);
        System.out.println(a);
    }

    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; ++j) {
            // System.out.println(j);
            System.out.println(Arrays.toString(nums));
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
        }
        // System.out.println(i);
        System.out.println(Arrays.toString(nums));
        return i;
    }

}
