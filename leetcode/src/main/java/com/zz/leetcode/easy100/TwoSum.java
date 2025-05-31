package com.zz.leetcode.easy100;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description LC_1_TwoSum
 * @Author 张卫刚
 * @Date Created on 2023/7/26
 */
public class TwoSum {


    /**
     * hash表
     * @param nums
     * @param target
     * @return
     */
    public static int[] getTwoSum(int[] nums, int target) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 暴力枚举
     * @param nums
     * @param target
     * @return
     */
    public static int[] solution2(int[] nums, int target){
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }


    public static void main(String[] args) {
//        int[] nums = new int[]{2, 7, 11, 15};
//        int target = 13;
//        System.out.println(Arrays.toString(solution2(nums, target)));
//
//        String s = "0.2225356";
//        BigDecimal bigDecimal = new BigDecimal(s).setScale(4, RoundingMode.HALF_UP);
//        double v = bigDecimal.doubleValue();
//        System.out.print(v);
//
//
//        //昌江 昌南新区  浮梁 珠山 乐平
//        int asset = 368+527+288+247+  215+372+ 470+308+274+556+609+764+368+315+336+1057+498+330+721+596+383+254+  938+153+  250+931+285+415+48+2157+203+219+40+582+314+180+81+138+297+214+364+631;
//
//        int source = 312+176+40+35+  20+93+  259+125+68+229+167+259+166+97+97+495+40+139+72+94+75+94+  71+7+  101+175+452+188+172+173+670+927+665+815+48+127+595+290+217+481+179+5395;
//
//        int contract = 42+38+7+26+  125+21+  26+94+10+12+17+30+4+14+137+49+2+104+5+6+9+6+  566+6+  11+18+22+50+14+288+14+263+33+18+514+17+11+273+12+17+66+57;
//
//        System.out.println("资产"+asset+ "资源"+source+ "合同"+contract);

//        String path =
//                "template/nyj/purchase/农村三资-资产采购-多次采购同一供货商.xlsx";
//        String title = path.split("/")[3].substring(5, path.split("/")[3].length() - 5);
//        System.out.println(title);
//
//        path = "template/nyj/purchase/农村三资-资产采购-频繁采购.xlsx";
//         title = path.split("/")[3].substring(5, path.split("/")[3].length() - 5);
//        System.out.println(title);


        String str = "广东省深圳市南山区南山街道南山社区居委会南山村委会南山村社区居委会南山村";

        String village = str.replaceAll("[市区街道]", "");
        System.out.println(village);

    }
}
