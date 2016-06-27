import javax.management.Query;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by jisakun on 15-11-5.
 */
public class Solution {
    public static void main(String[] args) {
        Solution T = new Solution();
        int[] nums = new int[]{1,1};
//        int [] nums = new int[]{};
        String s = "123";
        System.out.println(T.numDecodings("10"));
    }

    public int numDecodings(String s) {
        // https://leetcode.com/problems/decode-ways/
        int LENGTH = s.length();
        int[] ways = new int[LENGTH+1];
        int tag = 0;
        ways[0] = 0;
        if(LENGTH > 0){
            ways[1] = (s.charAt(0)=='0')?0:1;
        }
        for(int i=1; i<LENGTH; i++){
            int numOneChar = Integer.parseInt(s.substring(i,i+1));
            int numberTwoChar = Integer.parseInt(s.substring(i-1, i+1));
            int maxOne = (numOneChar>0)?(ways[i]+1):0;
            int maxTwo = (numberTwoChar<=26)?(ways[i-1]*2):0;
            ways[i+1] = maxOne+maxTwo;
        }

        return ways[LENGTH];

    }

    public ListNode deleteDuplicates(ListNode head) {
        // https://leetcode.com/problems/remove-duplicates-from-sorted-list/
        if(head==null){
            return head;
        }
        ListNode formerPtr = head;
        ListNode laterPtr = head.next;
        while(laterPtr != null){
            if(laterPtr.val == formerPtr.val){
                laterPtr = laterPtr.next;
                formerPtr.next = laterPtr;
            } else {
                formerPtr = formerPtr.next;
                laterPtr = laterPtr.next;
            }
        }
        return head;
    }

//    public int ladderLength(String beginWord, String endWord, Set<String> wordList){}

    // TLE
    public int ladderLengthTLE(String beginWord, String endWord, Set<String> wordList) {
        // https://leetcode.com/problems/word-ladder/
        Set<String> leftDict = new HashSet<String>(wordList);
        Set<String> usedDict = new HashSet<String>();
        Queue<String> ladder = new LinkedList<String>();
        int minLength = Integer.MAX_VALUE;

        ladder.add(beginWord);
        int preLength = 1;
        int lastLevelCount = 1;
        leftDict.remove(beginWord);
        while (ladder.size() > 0){
            int preLevelCount = 0;
            preLength++;
            for(int i=0; i<lastLevelCount; i++) {
                String stepNow = ladder.poll();
                if (canTrans(stepNow, endWord)) {
                    return preLength;
                }else {
                    Set<String> used = new HashSet<String>();
                    for(String word : leftDict){
                        if(canTrans(stepNow, word)){
                            preLevelCount++;
                            ladder.add(word);
                            used.add(word);
                        }
                    }
                    leftDict.removeAll(used);
                }
            }
        }

        return 0;
    }

    public boolean canTrans(String from, String to){
        int tag = 0;
        for(int i=0; i<from.length(); i++){
            if(from.charAt(i) != to.charAt(i)){
                tag ++;
            }
            if(tag > 1){
                return false;
            }
        }
        return tag == 1;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        // https://leetcode.com/problems/reverse-linked-list-ii/
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode mPre = fakeHead;
        ListNode tag = fakeHead.next;
        ListNode preTag = fakeHead;
        int reverseCount = n-m+1;
        for(int i=1; i<=n; i++){
            if(i == m-1){
                mPre = tag;
            }
            if(i > m){
                preTag.next = tag.next;
                tag.next = mPre.next;
                mPre.next = tag;
                tag = preTag.next;
            } else {
                preTag = tag;
                tag = tag.next;
            }

        }
        return fakeHead.next;
    }

    public ListNode reverseList(ListNode head) {
        // https://leetcode.com/problems/reverse-linked-list/
        ListNode newHead = new ListNode(0);
        newHead.next = null;
        while (head != null){
            ListNode tmpNode = new ListNode(head.val);
            tmpNode.next = newHead.next;
            newHead.next = tmpNode;
            head = head.next;
        }
        return newHead.next;
    }

    // remain document , quick select
    public int findKthLargest(int[] nums, int k) {
        // https://leetcode.com/problems/kth-largest-element-in-an-array/
        int LENGTH = nums.length;
        Stack<Integer> st = new Stack<Integer>();
        st.push(0);
        st.push(LENGTH -1);
        while(st.size() > 0){
            int high = st.pop();
            int highMark = high;
            int low = st.pop();
            int lowMark = low;
            int pivot = nums[low];
            while(low<high){
                if(nums[high]<=pivot && low<high){
                    high --;
                }
                nums[low] = nums[high];
                if(nums[low]>=pivot && low<high){
                    low ++;
                }
                nums[high] = nums[low];
            }
            // now low = high
            nums[low] = pivot;
            if(low>k-1){
                st.push(lowMark);
                st.push(low-1);
            }else {
                if(low<k-1){
                    st.push(low+1);
                    st.push(highMark);
                }else{
                    break;
                }
            }

        }
        return nums[k-1];
    }

    public void testSubstring(){
        String root = "asdfghjkl";
        String tag = "asd";
        System.out.println(root.indexOf(tag));
    }

    public boolean canJump(int[] nums){
//        https://leetcode.com/problems/jump-game/
        int start = nums[0];
        int stop = -1;
        for(int i=0; i<nums.length; i++){
            if(start < 0){
                break;
            }

            stop = i;
            if(nums[i]>start){
                start = nums[i];
            }
            start -- ;
        }
        if(stop == nums.length-1){
            return true;
        } else {
            return false;
        }
    }



    // remain document
    public int numSquares(int n) {
        // https://leetcode.com/problems/perfect-squares/
        int[] min = new int[n+1];
        min[0] = 0;
        min[1] = 1;
        for(int i=2;  i<=n; i++){
            int localMin = 4;
            for(int j=1; j*j<=i; j++){
                int tmp = min[i-j*j]+1;
                localMin = tmp<localMin?tmp:localMin;
            }
            min[i] = localMin;
        }

        return min[n];
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());                        // 空集
        List<List<Integer>> tmpResult = new ArrayList<List<Integer>>();
        for(int i=0; i<nums.length; i++){
            // add tmpResult to result
            result.addAll(tmpResult);
            // append num[i] to end of subset in tmpResult
            for(List<Integer> sb : tmpResult){
                sb.add(nums[i]);
            }
            // add num[i] to tmpResult
            List<Integer> subuset = new ArrayList<Integer>();
            subuset.add(nums[i]);
            tmpResult.add(subuset);
        }
        result.addAll(tmpResult);

        return result;
    }

    // remain document -done
    public int maxProduct(int[] nums) {
        // https://leetcode.com/problems/maximum-product-subarray/
        int LENGTH = nums.length;
        int minLocal=nums[0];
        int maxLocal = nums[0];
        int global = nums[0];
        for(int i=1; i<LENGTH; i++){
            int minCopy = minLocal;
            minLocal = Math.min(Math.min(minLocal*nums[i], maxLocal*nums[i]), nums[i]);
            maxLocal = Math.max(Math.max(maxLocal*nums[i], minCopy*nums[i]), nums[i]);
            global = Math.max(global, maxLocal);
        }
        return global;

    }

    public ListNode partition(ListNode head, int x) {
        // https://leetcode.com/problems/partition-list/
        ListNode smallHead = new ListNode(0);
        ListNode bigHead = new ListNode(0);
        ListNode smallPtr = smallHead;
        ListNode bigPtr = bigHead;
        ListNode headPtr = head;
        while(headPtr != null){
            if(headPtr.val<x){
                smallPtr.next = new ListNode(headPtr.val);
                smallPtr = smallPtr.next;
            }else {
                bigPtr.next = new ListNode(headPtr.val);
                bigPtr = bigPtr.next;
            }
            headPtr = headPtr.next;
        }
        smallPtr.next = bigHead.next;
        return smallHead.next;
    }

    // remain document
    public String shortestPalindrome(String s) {
        // https://leetcode.com/problems/shortest-palindrome/
        int i = 0, end = s.length() - 1, j = end; char chs[] = s.toCharArray();
        while(i < j) {
            if (chs[i] == chs[j]) {
                i++; j--;
            } else {
                i = 0; end--; j = end;
            }
        }
        return new StringBuilder(s.substring(end+1)).reverse().toString() + s;
    }

    public String shortestPalindromeTLE(String s) {
        // https://leetcode.com/problems/shortest-palindrome/
        if(s.equals("") || s.equals(null)){
            return s;
        }
        int palindromeIndex = 1;
        for (int i = s.length(); i > 0; i--) {
            String subS = s.substring(0, i);
            if(subS.equals(new StringBuilder(subS).reverse().toString())){
                palindromeIndex = i;
                break;
            }
        }
        StringBuffer palindromeString= new StringBuffer();
        palindromeString.append(new StringBuilder(s.substring(palindromeIndex)).reverse().toString())
                .append(s);
        return palindromeString.toString();

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode HEAD = head;
        ListNode pt1 = head;
        ListNode pt2 =head;
        ListNode ptLast = null;
        for(int i=0; i<n-1; i++){
            pt2 = pt2.next;
        }
        while(pt2.next != null){
            ptLast = pt1;
            pt1 = pt1.next;
            pt2 = pt2.next;
        }
        if(pt1 == head){
            HEAD = pt1.next;
        }else {
            ptLast.next = pt1.next;
        }
        return HEAD;
    }

    public int maxProfit(int[] prices) {
        // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
        if(prices.length <= 1) {
            return 0;
        }

        int maxProfit = Integer.MIN_VALUE;
        int maxPrice = prices[prices.length-1];
        for(int i=prices.length-2; i>=0; i--){
            if(prices[i]>maxPrice){
                maxPrice = prices[i];
            }
            int delta =  maxPrice - prices[i];
            if(delta > maxProfit){
                maxProfit = delta;
            }
        }
        return maxProfit;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // https://leetcode.com/problems/same-tree/
        boolean flag = true;
        Stack<TreeNode> stackP = new Stack<TreeNode>();
        Stack<TreeNode> stackQ = new Stack<TreeNode>();
        if(p==null && q==null){
            return  true;
        }
        if((p==null) || (q==null)){
            return false;
        }

        stackP.push(p);
        stackQ.push(q);
        while (!stackP.isEmpty() && !stackQ.isEmpty()){
            TreeNode nodeP = stackP.pop();
            TreeNode nodeQ = stackQ.pop();
            if(nodeP.val != nodeQ.val){
                flag = false;
                break;
            }
            if((nodeP.left != null)&&(nodeQ.left != null)){
                stackP.push(nodeP.left);
                stackQ.push(nodeQ.left);
            }else{
                if(!((nodeP.left == null)&&(nodeQ.left == null))){
                    flag = false;
                    break;
                }
            }
            if((nodeP.right != null)&&(nodeQ.right != null)){
                stackP.push(nodeP.right);
                stackQ.push(nodeQ.right);
            }else{
                if(!((nodeP.right == null)&&(nodeQ.right == null))){
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    // remain document
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        // https://leetcode.com/problems/insert-interval/
        ArrayList<Interval> newIntervals = new ArrayList<Interval>();
        if(intervals.isEmpty()){
            newIntervals.add(newInterval);
            return newIntervals;
        }
        boolean mergeStart = false;
        boolean mergeDone = false;
        int start = newInterval.start;
        int end = newInterval.end;
        for(Interval interval : intervals){
            if(interval.end < start){
                newIntervals.add(interval);
            }else {
                if(interval.start < start){
                    start = interval.start;
                }
            }
            if(interval.end > end){
                if(interval.start > end){
                    if(!mergeDone){
                        Interval tmp = new Interval();
                        tmp.start = start;
                        tmp.end = end;
                        newIntervals.add(tmp);
                        mergeDone = true;
                    }
                    newIntervals.add(interval);
                }
                else {
                    end = interval.end;
                }
            }
        }
        if(!mergeDone){
            Interval tmp = new Interval();
            tmp.start = start;
            tmp.end = end;
            newIntervals.add(tmp);
        }

        return newIntervals;

    }






}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
