package Set_01;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class TwoSum {

    public static void main(String[] args) {
        int[] values = { 2, 7, 11, 15 };
        System.out.println(Arrays.toString(twoSum(values, 9)));
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        /* Create a map: first integer is for number value, second is for index */
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            System.out.println(nums[i]);
            System.out.println(i);
            System.out.println(map.containsKey(nums[i]));
            if (map.containsKey(nums[i])) {
                ans[0] = map.get(nums[i]);
                ans[i] = i;
                break;
            }
            /*
             * Number in array doesn't exist in map: so include value of it substracted from
             * target and its index
             */
            map.put(target - nums[i], i);
            System.out.println(map.keySet());
            System.out.println(map.values());
        }
        return ans;
    }
}
