package leetcode;

//给你一个字符串 s，找到 s 中最长的回文子串。
//
//         
//
//        示例 1：
//
//        输入：s = "babad"
//        输出："bab"
//        解释："aba" 同样是符合题意的答案。
//        示例 2：
//
//        输入：s = "cbbd"
//        输出："bb"
//        示例 3：
//
//        输入：s = "a"
//        输出："a"
//        示例 4：
//
//        输入：s = "ac"
//        输出："a"
//         
//
//        提示：
//
//        1 <= s.length <= 1000
//        s 仅由数字和英文字母（大写和/或小写）组成

public class Leetcode5 {

    class Solution {
        public String longestPalindrome(String s) {
            StringBuilder stringBuilder = new StringBuilder("#");
            for (int i = 0;i < s.length();i++) {
                stringBuilder.append(s.charAt(i));
                stringBuilder.append("#");
            }
            String str = stringBuilder.toString();
            // 插入#后，维护一个p[]
            // 同时维护1个当前最靠右的中心位置rightest_mid，最靠右位置rightest_pos
            // 以及全局最大max_mid_pos
            int max_mid_pos = 0;
            int rightest_pos = 0;
            int rightest_mid = 0;
            int[] p = new int[str.length()];
            for (int i = 0;i < str.length();i++) {
                if (rightest_pos > i) {
                    p[i] = p[2 * rightest_mid - i];
                    int q = i + 2 * p[2 * rightest_mid - i] - 1;
                    if (i % 2 == 0) {
                        q++;
                    }
                    if (q < rightest_pos) {
                        continue;
                    }

                }
                for (int j = rightest_pos + 1;j < str.length();j++) {
                    if (2 * i - j < 0 || str.charAt(j) != str.charAt(2 * i - j)) {
                        if (i % 2 == 1 && p[i] == 0) {
                            p[i] = 1;
                        }
                        break;
                    }
                    rightest_pos++;
                    rightest_mid = i;
                    if (i % 2 == 0) {
                        // 此时为 #
                        p[i] = (j - i + 1) / 2;
                    } else {
                        p[i] = (j - i) / 2 + 1;
                    }
                }
                if (p[i] > p[max_mid_pos]
                        || ((i % 2 == 0) && p[i] == p[max_mid_pos])) {
                    max_mid_pos = i;
                }
            }
            if (max_mid_pos % 2 == 0) {
                return s.substring(max_mid_pos / 2 - 1 - p[max_mid_pos] + 1, max_mid_pos / 2 - 1 + p[max_mid_pos] + 1);
            } else {
                return s.substring((max_mid_pos - 1) / 2 - p[max_mid_pos] + 1, (max_mid_pos - 1) / 2 + p[max_mid_pos]);
            }
        }
    }

    public static void main(String[] args) {
        Leetcode5.Solution solution = new Leetcode5().new Solution();
        // 正确输出
        String s2 = "aaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaa";
        // 输入
        String s3 = "aaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaa";
        String test = "aabbaaaabbaa";
        System.out.println(solution.longestPalindrome(test));
        System.out.println(solution.longestPalindrome(s3));
        System.out.println(s2);
        System.out.println();
    }
}
