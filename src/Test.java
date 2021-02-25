public class Test {

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            // if (nums[mid] == target) {
            //     return true;
            // }
            if (nums[left] < nums[mid]) {
                // 落在前有序数组里
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[left]) {
                // 落在后有序数组中
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else if (nums[left] == nums[mid]) {
                left++;
            }
        }
        // 后处理，夹逼以后，还要判断一下，是不是target
        return nums[left] == target;
    }

    public static void main(String[] args) {
        Test test = new Test();
        int[] nums = {1, 0, 1, 1, 1};
        int target = 0;
        boolean flag = test.search(nums, target);
        System.out.println(flag);

    }
}

