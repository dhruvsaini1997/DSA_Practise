public class SplitListInParts {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode[] splitListToParts(ListNode head, int k) {
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        int quotient = len / k;
        int remainder = len % k;

        curr = head;
        ListNode[] ans = new ListNode[k];

        for (int i = 0; i < k; i++) {
            ans[i] = curr;
            int sublistSize = quotient + (i < remainder ? 1 : 0);

            for (int j = 0; j < sublistSize - 1; j++) {
                if(curr.next!=null)
                 curr = curr.next;
            }

            if (curr != null) {
                ListNode temp = curr.next;
                curr.next = null;
                curr = temp;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        // Create a sample linked list for testing
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        int k = 6;
        ListNode[] result = splitListToParts(head, k);

        for (ListNode sublist : result) {
            while (sublist != null) {
                System.out.print(sublist.val + " ");
                sublist = sublist.next;
            }
            System.out.println();
        }
    }
}
