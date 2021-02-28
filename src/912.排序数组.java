import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

/*
 * @lc app=leetcode.cn id=912 lang=java
 *
 * [912] 排序数组
 *
 * https://leetcode-cn.com/problems/sort-an-array/description/
 *
 * algorithms
 * Medium (59.56%)
 * Likes:    214
 * Dislikes: 0
 * Total Accepted:    111.2K
 * Total Submissions: 186.6K
 * Testcase Example:  '[5,2,3,1]'
 *
 * 给你一个整数数组 nums，请你将该数组升序排列。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * 
 * 
 * 示例 2：
 * 
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 50000
 * -50000 <= nums[i] <= 50000
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 选择排序，每一轮选择最小的元素交换到未排定部分开头
     */
    public int[] sortArray1(int[] nums) {
        int len = nums.length;
        // 循环不变量：[0, i)有序，且该区间内所有元素就是最终排定的样子
        for (int i = 0; i < len - 1; i++) {
            // 选择区间[i, len - 1]里最小元素的索引，交换到下标i
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
        return nums;
    }

    /**
     * 冒泡排序，两两比较，每一轮最大的浮动到最后
     * 这也太慢了，最后超时了。。。
     */
    public int[] sortArray2(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < len - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
        return nums;
    }

    /**
     * 插入排序：稳定排序，在接近有序的情况下，表现优异
     */
    public int[] sortArray3(int[] nums) {
        int len = nums.length;
        // 循环不变量：将nums[i]插入到区间[0, i)使之成为有序数组
        // 从下标为1的元素开始选择位置插入，因为下标0只有一个元素，默认有序的
        for (int i = 1; i < len; i++) {
            // 先暂存这个元素，然后之前的元素逐个后移，留出空位
            int temp = nums[i];
            // 从已经排序的序列的最右边开始比较，找到比其小的数
            int j = i;
            // 注意边界，j > 0
            while (j > 0 && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temp;
        }
        return nums;
    }

    /**
     * 列表大小小于等于该大小，将优先使用于mergeSort，使用插入排序
     */
    private static final int INSERTION_SORT_THRESHOLD = 7;
    /**
     * 归并排序
     */
    public int[] sortArray4(int[] nums) {
        int len = nums.length;
        int[] temp = new int[len];
        mergeSort(nums, 0, len - 1, temp);
        return nums;
    }

    /**
     * 对数组nums的子区间[left, right]使用归并排序
     * @param temp 用于合并两个有序数组的辅助数组，全局使用一份，避免多次创建和销毁
     */
    private void mergeSort(int[] nums, int left, int right, int[] temp) {
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(nums, left, right);
            return;
        }
        int mid = left + right >>> 1;
        mergeSort(nums, left, mid, temp);
        mergeSort(nums, mid + 1, right, temp);
        // 如果数组的这个子区间本身有序，无需合并
        if (nums[mid] <= nums[mid + 1]) {
            return;
        }
        mergeOfTwoSortedArray(nums, left, mid, right, temp);

    }

    /**
     * 合并两个有序数组：先把值复制到临时数组，再合并回去
     * @param mid [left, mid]有序，[mid + 1, right]有序
     * @param temp 全局使用的临时数组
     */
    private void mergeOfTwoSortedArray(int[] nums, int left, int mid, int right, int[] temp) {
        System.arraycopy(nums, left, temp, left, right + 1 - left);
        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                // 注意写成<就丢失了稳定性（相同元素原来靠前的排序以后依然靠前）
                nums[k] = temp[i];
                i++;
            } else if (temp[i] > temp[j]) {
                nums[k] = temp[j];
                j++;
            }
        }
        
    }

    /**
     * 对数组nums的子区间，[left, right]使用插入排序
     * @param nums 给定数组
     * @param left 左边界，能取到
     * @param right 右边界，能取到
     */
    private void insertionSort(int[] nums, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = nums[i];
            int j = i;
            while (j > left && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temp;
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    private static final Random RANDOM = new Random();
    /**
     * 快速排序1：基本快速排序
     */
    public int[] sortArray5(int[] nums) {
        int len = nums.length;
        quickSort(nums, 0, len - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        // 小区间使用插入排序
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(nums, left, right);
            return;
        }

        int pivot = partition(nums, left, right);
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int randomIndex = RANDOM.nextInt(right - left + 1) + left;
        swap(nums, left, randomIndex);

        // 基准值
        int pivot = nums[left];
        int lt = left;
        // 循环不变量：
        // all in [left + 1, lt] < pivot
        // all in [lt + 1, i) >= pivot
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                lt++;
                swap(nums, i, lt);
            }
        }
        swap(nums, left, lt);
        return lt;
    }

    public int[] sortArray6(int[] nums) {
        int len = nums.length;
        // 1. 构建大顶堆
        for (int i = len / 2 - 1; i >= 0; i--) {
            // 从第一个非叶子节点从下到上，从右到左调整结构
            adjustHeap(nums, i, len);
        }

        // 2. 调整堆结构
        for (int j = len - 1; j > 0; j--) {
            // 将堆顶元素与末尾元素进行交换
            swap(nums, 0, j);
            // 重新对堆进行调整
            adjustHeap(nums, 0, j);
        }
        return nums;
    }

    /**
     * 调整大顶堆
     */
    private void adjustHeap(int[] nums, int i, int len) {
        // 先取出当前元素i
        int temp = nums[i];
        // 从i节点的左子节点开始，也就是2i + 1处开始
        for (int k = i * 2 + 1; k < len; k = k * 2 + 1) {
            // 如果左子节点小于右子节点，k指向右子节点
            if (k + 1 < len && nums[k] < nums[k + 1]) {
                k++;
            }
            // 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if (nums[k] > temp) {
                nums[i] = nums[k];
                i = k;
            } else {
                break;
            }
        }
        nums[i] = temp;
    }

    private static final int OFFSET = 50000;
    /**
     * 计数排序
     */
    public int[] sortArray7(int[] nums) {
        int len = nums.length;
        // 由于-50000 <= A[i] <= 50000
        // 因此桶的大小为50000-（-50000） = 100000
        // 并且可以设置偏移offset为50000，目的是让每一个数都大于等于0
        // 这样就可以作为count数组的下标，查询这个数的计数
        int size = 100000;

        // 计数数组
        int[] count = new int[size];
        for (int num : nums) {
            count[num + OFFSET]++;
        }
        // 把count数组变为前缀和数组
        for (int i = 1; i < size; i++) {
            count[i] += count[i - 1];
        }
        // 先把原始数组赋值到一个临时数组中，然后回写数据
        int[] temp = nums.clone();

        // 为了保证稳定性，从后往前赋值（让数组按照原来的顺序排序）
        for (int i = len - 1; i >= 0; i--) {
            int index = count[temp[i] + OFFSET] - 1;
            nums[index] = temp[i];
            count[temp[i] + OFFSET]--;
        }

        return nums;
    }

    public int[] sortArray8(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

    public int[] sortArray9(int[] nums) {
        int len = nums.length;
        // 预处理，让所有的数都大于等于0，这样才可以使用基数排序
        for (int i = 0; i < len; i++) {
            nums[i] += OFFSET;
        }
        // 1. 找出最大的数字
        int maxVal = Arrays.stream(nums).max().getAsInt();

        // 2. 计算出最大的数字有几位，这个数值决定了我们要将整个数组看几遍
        int maxLen = getMaxLen(maxVal);

        // 计数排序时需要使用的计数数组和临时数组
        int[] temp = new int[len];

        // 表征关键字的量：除数
        // 1 表示按个位关键字排序
        // 10 表示按照十位关键字排序
        // 100 表示按照百位关键字排序
        int divisor = 1;
        // 有几位数，外层就得循环几次
        for (int i = 0; i < maxLen; i++) {
            // 每一步都要使用计数排序，保证排序结果是稳定的
            // 这一步需要额外的空间保存结果集，因此把结果保存在temp中
            countingSort(nums, temp, divisor);
            // 交换nums和temp的引用，下一轮还是按照nums做计数排序
            int[] t = nums;
            nums = temp;
            temp = t;
            // divisor自增，表示采用低位优先的基数排序
            divisor *= 10;
        }

        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = nums[i] - OFFSET;
        }
        return res;
    }

    private void countingSort(int[] nums, int[] res, int divisor) {
        int len = nums.length;
        int[] count = new int[10];
        // 1. 计算计数数组
        for (int i = 0; i < len; i++) {
            // 计算数位上的是几，先取个位，再十位，百位
            int remainder = (nums[i] / divisor) % 10;
            count[remainder]++;
        }

        // 2. 变成前缀和数组
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 3. 从后向前赋值,这是为了保证排序的稳定性
        for (int i = len - 1; i >= 0; i--) {
            int remainder = (nums[i] / divisor) % 10;
            int index = count[remainder] - 1;
            res[index] = nums[i];
            count[remainder]--;
        }
    }

    /**
     * 获取一个整数的最大位数
     */
    private int getMaxLen(int num) {
        int maxLen = 0;
        while (num > 0) {
            num /= 10;
            maxLen++;
        }
        return maxLen;
    }

    public int[] sortArray(int[] nums) {
        int len = nums.length;
        // 计算最大值和最小值
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();

        // 计算桶的数量
        int bucketNum = (max - min) / len + 1;
        List<List<Integer>> bucketList = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new ArrayList<Integer>());
        }
        // 将每个元素放入桶
        for (int i = 0; i < len; i++) {
            int num = (nums[i] - min) / len;
            bucketList.get(num).add(nums[i]);
        }
        // 对每个桶进行排序
        for (int i = 0; i < bucketNum; i++) {
            Collections.sort(bucketList.get(i));
        }
        int index = 0;
        // 将桶中的元素赋值到原序列
        for (int i = 0; i < bucketNum; i++) {
            for (int j = 0; j < bucketList.get(i).size(); j++) {
                nums[index] = bucketList.get(i).get(j);
                index++;
            }
        }
        return nums;
    }
}
// @lc code=end

