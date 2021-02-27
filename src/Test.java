public class Test {

   public int reversePairs(int[] nums) {
       int len = nums.length;
       if (len < 2) {
           return 0;
       }
       int[] copy = nums.clone();
       int[] temp = new int[len];
       
       return reversePairs(copy, 0, len - 1, temp);
   }

   /**
    * nums[left, right]计算逆序对个数，并且排序
    */
    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }
        int mid = (left + right) >>> 1;
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);

        // 如果整个数组已经有序，则无需合并
        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }
        
        int crossPairs = mergeAndCount(nums, left, mid, right, temp);
        return leftPairs + rightPairs + crossPairs;
   }

   /**
    * nums[left, mid]有序， nums[mid + 1, right]有序
    */
   private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
       for (int i = left; i <= right; i++) {
           temp[i] = nums[i];
       }
       int i = left;
       int j = mid + 1;
       int count = 0;

       for (int k = left; k <= right; k++) {
           if (i == mid + 1) {
               nums[k] = temp[j++];
           } else if (j == right + 1) {
               nums[k] = temp[i++];
           } else if (temp[i] <= temp[j]) {
               nums[k] = temp[i++];
           } else {
               nums[k] = temp[j++];
               // 在j指向的元素归并回去的时候，计算逆序对的个数，只多了这一行代码
               count += mid - i + 1;
           }
       }

       return count;
   }

   public static void main(String[] args) {
        Test test = new Test();
        int[] nums = {1, 0, 1, 1, 1};
        int target = 0;
        boolean flag = test.search(nums, target);
        System.out.println(flag);

    }
}

