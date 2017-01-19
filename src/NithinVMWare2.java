
public class NithinVMWare2 {

    public static void main(String[] args) {
        String t = mergeStrings("abc", "def");
        System.out.println(t);
    }

    static String mergeStrings(String a, String b) {
        if (a == null)
            return b;
        if (b == null)
            return a;

        String result = "";
        int i = 0;
        while (i < a.length() && i < b.length()) {
            result += a.charAt(i) + "" + b.charAt(i);
            i++;
        }
        while (i < a.length()) {
            result += a.charAt(i);
            i++;
        }
        while (i < b.length()) {
            result += b.charAt(i);
            i++;
        }
        return result;
    }

    static LinkedListNode removeNodes(LinkedListNode list, int x) {
        if (list == null)
            return list;
        LinkedListNode head = list;
        LinkedListNode prev = null;
        while (list != null) {
            if (list.val == x) {
                if (head == list) {
                    head = head.next;
                } else {
                    prev.next = list.next;
                }
            } else {
                prev = list;
            }
            list = list.next;
        }
        
        return head;
    }
}

class LinkedListNode {
    int val;
    LinkedListNode next;
};
