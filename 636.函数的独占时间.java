import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode.cn id=636 lang=java
 *
 * [636] 函数的独占时间
 */

// @lc code=start
class Solution {

    private class Task{
        private final boolean start = true;
        private final boolean end = false;
        
        private int id;
        private boolean flag;
        private int timestamp;

        public Task(String s) {
            String[] info = s.split(":");
            id = Integer.valueOf(info[0]);
            if (info[1].equals("start")) {
                flag = start;
            } else {
                flag = end;
            }
            timestamp = Integer.valueOf(info[2]);
        }
    }

    
    public int[] exclusiveTime(int n, List<String> logs) {

        int[] runtime = new int[n];
        Deque<Task> stack = new ArrayDeque<>();

        for (String s : logs) {
            Task t = new Task(s);
            if (stack.isEmpty()) {
                stack.push(t);
            } else if (t.flag) {
                // 当前栈不为空，且新增函数调用，统计上一个函数的运行时间
                runtime[stack.peek().id] += (t.timestamp - stack.peek().timestamp);
                stack.push(t);
            } else {
                // 当前栈不为空，且当前为结束标记，则当前任务结束运行
                runtime[stack.peek().id] += t.timestamp - stack.pop().timestamp  + 1;
                // 如果当前栈不为空，则仍有函数调用未结束，更新当前函数开始时刻
                if (!stack.isEmpty()) {
                    stack.peek().timestamp = t.timestamp + 1;
                }
            }
        }

        return runtime;
    }
}
// @lc code=end

