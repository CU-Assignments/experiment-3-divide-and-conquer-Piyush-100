import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    public static ListNode deserialize(String data) {
        if (data.equals("[]")) return null;  
        data = data.replace("[", "").replace("]", ""); 
        String[] values = data.split(",");

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (String val : values) {
            current.next = new ListNode(Integer.parseInt(val.trim()));
            current = current.next;
        }
        return dummy.next;
    }
}

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return convertToBST(head, null);
    }

    private TreeNode convertToBST(ListNode head, ListNode tail) {
        if (head == tail) return null;

        ListNode slow = head, fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode root = new TreeNode(slow.val);
        root.left = convertToBST(head, slow);
        root.right = convertToBST(slow.next, tail);

        return root;
    }

   
    private static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        String input = "[-10,-3,0,5,9]"; 
        ListNode head = ListNode.deserialize(input);

        Solution solution = new Solution();
        TreeNode root = solution.sortedListToBST(head);
        printInorder(root); 
    }
}
