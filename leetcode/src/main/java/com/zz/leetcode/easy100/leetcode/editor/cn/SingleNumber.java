/**
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1 ：
 * <p>
 * <p>
 * 输入：nums = [2,2,1]
 * 输出：1
 * <p>
 * <p>
 * 示例 2 ：
 * <p>
 * <p>
 * 输入：nums = [4,1,2,1,2]
 * 输出：4
 * <p>
 * <p>
 * 示例 3 ：
 * <p>
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 3 * 10⁴
 * -3 * 10⁴ <= nums[i] <= 3 * 10⁴
 * 除了某个元素只出现一次以外，其余每个元素均出现两次。
 * <p>
 * <p>
 * Related Topics 位运算 数组
 * 👍 3066 👎 0
 */

package com.zz.leetcode.easy100.leetcode.editor.cn;


import java.util.Arrays;
import java.util.Collections;

public class SingleNumber {
    public static void main(String[] args) {
        Solution solution = new SingleNumber().new Solution();
        int[] nums = new int[]{4, 1, 2, 1, 2};
        System.out.println(solution.singleNumber(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int singleNumber(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }
            int[] array = Arrays.stream(nums).sorted().toArray();
            int result = 0;
            for (int i = 0; i < array.length; i++) {
                if (i + 1 == array.length) {
                    result = array[i];
                    continue;
                }
                if (array[i] == array[i + 1]) {
                    i++;
                    continue;
                }
                result = array[i];
            }
            return result;
        }

        public int solution2(int[] nums) {
            int ans = nums[0];
            for (int i = 1; i < nums.length; i++) {
                ans = ans ^ nums[i];
            }
            return ans;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}