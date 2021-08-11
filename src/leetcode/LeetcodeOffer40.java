package leetcode;
//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
//
//         
//
//        示例 1：
//
//        输入：arr = [3,2,1], k = 2
//        输出：[1,2] 或者 [2,1]
//        示例 2：
//
//        输入：arr = [0,1,2,1], k = 1
//        输出：[0]
//         
//
//        限制：
//
//        0 <= k <= arr.length <= 10000
//        0 <= arr[i] <= 10000

public class LeetcodeOffer40 {

    class Solution {
        public int[] getLeastNumbers(int[] arr, int k) {
            makeK(arr, 0, arr.length, k);
            int[] res = new int[k];
            for (int i = 0;i < k;i++) {
                res[i] = arr[i];
            }
            return res;
        }

        void makeK(int[] arr,int l, int r, int k) {
            if (r - l == k || r - l < 2) {
                return;
            }
            int now_right = r;
            // 将[l,r)中最小的k个数排列在左边
            for (int i = l;i < now_right - 1;i++) {
                // 将比第一个数小的放左边，大的放右边
                if (arr[i + 1] >= arr[i]) {
                    now_right--;
                    int tmp = arr[i + 1];
                    arr[i + 1] = arr[now_right];
                    arr[now_right] = tmp;
                    i--;
                } else {
                    int tmp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = tmp;
                }
            }
            // 至此，now right左边的都比他小
            if (now_right - l == k) {
                return;
            }
            if (now_right - l < k) {
                makeK(arr, now_right, r, k - now_right + l);
            } else {
                makeK(arr, l, now_right, k);
            }

        }
    }

    public static void main(String[] args) {
        LeetcodeOffer40.Solution solution = new LeetcodeOffer40().new Solution();
        int[] arr = new int[] {3,2,1};
        int[] leastNumbers = solution.getLeastNumbers(arr, 2);
    }
}
