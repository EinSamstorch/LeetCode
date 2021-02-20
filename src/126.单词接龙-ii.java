import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=126 lang=java
 *
 * [126] 单词接龙 II
 *
 * https://leetcode-cn.com/problems/word-ladder-ii/description/
 *
 * algorithms
 * Hard (39.13%)
 * Likes:    395
 * Dislikes: 0
 * Total Accepted:    28.8K
 * Total Submissions: 73.7K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord
 * 的最短转换序列。转换需遵循如下规则：
 * 
 * 
 * 每次转换只能改变一个字母。
 * 转换后得到的单词必须是字典中的单词。
 * 
 * 
 * 说明:
 * 
 * 
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * 输出:
 * [
 * ⁠ ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * 输出: []
 * 
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 * 
 */

// @lc code=start
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // 先将wordlist放入到哈希表里，用于判断某个单词是否在wordlist中
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        if (wordSet.size() == 0 || !wordList.contains(endWord)) {
            return res;
        }

        // 第1步：使用广度优先遍历得到后继节点列表successors
        // key: 字符串，value: 广度优先遍历过程中key的后继节点列表
        Map<String, Set<String>> successors = new HashMap<>();
        boolean found = bfs(beginWord, endWord, wordSet, successors);
        if (!found) {
            return res;
        }

        // 第2步：基于后继节点列表successors，使用回溯算法得到所有最短路径列表
        Deque<String> path = new ArrayDeque<>();
        path.addLast(beginWord);
        dfs(beginWord, endWord, successors, path, res);
        return res;
    }

    private void dfs(String beginWord, String endWord, Map<String, Set<String>> successors, Deque<String> path,
            List<List<String>> res) {
        // 返回条件
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (!successors.containsKey(beginWord)) {
            return;
        }
        Set<String> successorWords = successors.get(beginWord);
        for (String nextWord : successorWords) {
            // 做选择
            path.addLast(nextWord);
            // 回溯
            dfs(nextWord, endWord, successors, path, res);
            // 撤销选择
            path.removeLast();
        }
    }

    private boolean bfs(String beginWord, String endWord, Set<String> wordSet, Map<String, Set<String>> successors) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        // 标准写法，记录访问过的单词
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        boolean found = false;
        int wordLen = beginWord.length();
        // 当前层访问过的节点，当前层全部遍历完成之后，再添加到总的visited集合里
        Set<String> nextLevelVisited = new HashSet<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                char[] charArray = currentWord.toCharArray();
                for (int j = 0; j < wordLen; j++) {
                    char originChar = charArray[j];
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (charArray[j] == k) {
                            continue;
                        }
                        charArray[j] = k;
                        String nextWord = new String(charArray);
                        if (wordSet.contains(nextWord)) {
                            if (!visited.contains(nextWord)) {
                                if (nextWord.equals(endWord)) {
                                    found = true;
                                }
                                // 避免下层元素重复加入队列
                                if (!nextLevelVisited.contains(nextWord)) {
                                    queue.offer(nextWord);
                                    nextLevelVisited.add(nextWord);
                                }
                                // 维护successors的定义
                                // successors.computeIfAbsent(currentWord, a -> new HashSet<>());
                                // successors.get(currentWord).add(nextWord);

                                // 维护 successors 的定义
                                if (successors.containsKey(currentWord)) {
                                    successors.get(currentWord).add(nextWord);
                                } else {
                                    Set<String> newSet = new HashSet<>();
                                    newSet.add(nextWord);
                                    successors.put(currentWord, newSet);
                                }
                            }
                        }
                    }
                    // 恢复
                    charArray[j] = originChar;
                }
            }
            if (found) {
                break;
            }
            visited.addAll(nextLevelVisited);
            nextLevelVisited.clear();
        }
        return found;
    }

    private static final int INF = 1 << 20;
    private Map<String, Integer> wordId; // 单词到id的映射
    private ArrayList<String> idWord; // id到单词的映射
    private ArrayList<Integer>[] edges; // 图的边
    
    public Solution() {
        wordId = new HashMap<>();
        idWord = new ArrayList<>();
    }

    /**
     * 感受下如何建图就好了。。。
     */
    public List<List<String>> findLadders1(String beginWord, String endWord, List<String> wordList) {
        int id = 0;
        // 将wordList中所有单词加入wordId中，相同的只保留一个
        // 并为每一个单词分配一个id
        for (String word : wordList) {
            if (!wordId.containsKey(word)) {
                wordId.put(word, id++);
                idWord.add(word);
            }
        }

        // 如果endWord不在wordList中，误解
        if (!wordId.containsKey(endWord)) {
            return new ArrayList<>();
        }

        // 将beginword也加入到wordid中
        if (!wordId.containsKey(beginWord)) {
            wordId.put(beginWord, id++);
            idWord.add(beginWord);
        }

        // 初始化存边用的数组
        edges = new ArrayList[idWord.size()];
        for (int i = 0; i < idWord.size(); i++) {
            edges[i] = new ArrayList<>();
        }
        // 添加边
        for (int i = 0; i < idWord.size(); i++) {
            for (int j = i + 1; j < idWord.size(); j++) {
                // 如果两者可以通过转换得到，则在他们间建立一条无向边
                if (transformCheck(idWord.get(i), idWord.get(j))) {
                    edges[i].add(j);
                    edges[j].add(i);
                }
            }
        }

        int dest = wordId.get(endWord);
        List<List<String>> res = new ArrayList<>();
        // 到每个点的代价
        int[] cost = new int[id];
        for (int i = 0; i < id; i++) {
            // 每个点的代价初始化为无穷大
            cost[i] = INF;
        }

        // 将起点加入队列，并将其cost设为0
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        ArrayList<Integer> tempBegin = new ArrayList<>();
        tempBegin.add(wordId.get(beginWord));
        queue.add(tempBegin);
        cost[wordId.get(beginWord)] = 0;

        // 开始广度优先搜索
        while (!queue.isEmpty()) {
            ArrayList<Integer> now = queue.poll();
            // 最近访问的点
            int last = now.get(now.size() - 1);
        }

        return res;
    }

    /**
     * 两个字符串是否可以通过改变一个字母后相等
     */
    private boolean transformCheck(String str1, String str2) {
        int differences = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)){
                differences++;
            }
        }
        return differences == 1;
    }
}
// @lc code=end

