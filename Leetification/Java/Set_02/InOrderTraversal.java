package Set_02;

import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.TreeNode;

public class InOrderTraversal {

    // Non-functional and mal-compris

    private void dfs(List<Integer> ans, TreeNode root) {
        /**
         * Trivial: Return nothing if a tree is empty.
         */
        if (root == null)
            return;
        /**
         * Recursion: Branch to the left.
         */
        dfs(ans, root.left);
        /**
         * Add to the list the value.
         */
        ans.add(root.val);
        /**
         * Recursion: Branch to the right.
         */
        dfs(ans, root.right);
    }

    public List<Integer> inOrderTraversal(TreeNode root) {
        /**
         * Set up an array list.
         */
        List<Integer> ans = new ArrayList<>();
        /**
         * Call helper function: dfs - inputs include the list and root.
         */
        dfs(ans, root);
        return ans;
    }
}
