package Set_02;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    // Incomplete: Casse-tete

    /**
     * Original idea: There are a number of possibilities regarding how numbers can
     * evaluate to the target sum. - Checking if a number can readd itself to give
     * the sum required. - Adding a number to itself until a different number from
     * the array is required to reach the target sum. For the left and rightmost
     * indices, it's a one-sided traversal but for the indices in between, looking
     * at the values which pertain to indices on the left and right seems relevant.
     * Rather than adding to evaluate if the target sum is reached, maybe
     * subtraction to see if 0 is met will be used instead.
     */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        /**
         * Set up an array list: A matrix list of integers.
         */
        List<List<Integer>> ans = new ArrayList<>();
        /**
         * Call the helper function, dfs. Supplied are: - An array list, - A newly
         * created array list, - An array of candidates, and - The target value, and 0
         */
        dfs(ans, new ArrayList<>(), candidates, target, 0);
        return ans;

    }

    private void dfs(List<List<Integer>> ans, List<Integer> tmp, int[] candidates, int target, int idx) {
        /**
         * Trivial: A target that is a negative value is disregarded.
         */
        if (target < 0)
            return;
    }

}
