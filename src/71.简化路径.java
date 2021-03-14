import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=71 lang=java
 *
 * [71] 简化路径
 *
 * https://leetcode-cn.com/problems/simplify-path/description/
 *
 * algorithms
 * Medium (41.61%)
 * Likes:    254
 * Dislikes: 0
 * Total Accepted:    71.2K
 * Total Submissions: 170.8K
 * Testcase Example:  '"/home/"'
 *
 * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 * 
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..）
 * 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。
 * 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 * 
 * 请注意，返回的 规范路径 必须遵循下述格式：
 * 
 * 
 * 始终以斜杠 '/' 开头。
 * 两个目录名之间必须只有一个斜杠 '/' 。
 * 最后一个目录名（如果存在）不能 以 '/' 结尾。
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 
 * 
 * 返回简化后得到的 规范路径 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：path = "/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。 
 * 
 * 示例 2：
 * 
 * 
 * 输入：path = "/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：path = "/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：path = "/a/./b/../../c/"
 * 输出："/c"
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * path 由英文字母，数字，'.'，'/' 或 '_' 组成。
 * path 是一个有效的 Unix 风格绝对路径。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 用split将字符分开后，要么得到空格，要么得到字符，要么得到..或是.
     * 使用栈模拟，遍历字符数组，如果是正常字符，就栈入，如果是..就弹出
     * 如果是.或者是空，就continue
     * 最后遍历栈，将字符拼接在一起就可以
     * @param path
     * @return
     */
    public String simplifyPath1(String path) {
        // 用split将字符分开后，要么得到空格，要么得到字符，要么得到..或是.
        String[] pathArray = path.split("/");
        // StringBuilder线程不安全，但是速度快
        StringBuilder res = new StringBuilder();
        Deque<String> stack = new ArrayDeque<>();

        for (int i = 0; i < pathArray.length; i++) {
            // 如果是空或者.  就continue
            if (pathArray[i].length() == 0 || pathArray[i].equals(".")) {
                continue;
            }
            if (!stack.isEmpty()) {
                if (pathArray[i].equals("..")) {
                    stack.pop();
                } else {
                    stack.push(pathArray[i]);
                }
            } else {
                // 如果stack为空的时候，只要不是..都是可以入栈的
                if (!pathArray[i].equals("..")) {
                    stack.push(pathArray[i]);
                }
            }
        }

        if (stack.isEmpty()) {
            return res.append("/").toString();
        }
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
            res.insert(0, "/");
        }
        return res.toString();
    }

    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder res = new StringBuilder();

        for (String item : path.split("/")) {
            if (item.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!item.equals("") && !item.equals(".")) {
                stack.push(item);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
            res.insert(0, "/");
        }
        return res.toString();
    }
}
// @lc code=end

