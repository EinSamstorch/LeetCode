import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javax.management.Query;

/*
 * @lc app=leetcode.cn id=127 lang=java
 *
 * [127] 单词接龙
 *
 * https://leetcode-cn.com/problems/word-ladder/description/
 *
 * algorithms
 * Hard (45.91%)
 * Likes:    696
 * Dislikes: 0
 * Total Accepted:    97.6K
 * Total Submissions: 212.5K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * 
 * 
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典 wordList 中的单词。
 * 
 * 
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列
 * 中的 单词数目 。如果不存在这样的转换序列，返回 0。
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：beginWord = "hit", endWord = "cog", wordList =
 * ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：beginWord = "hit", endWord = "cog", wordList =
 * ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * endWord.length == beginWord.length
 * 1 
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 * 
 * 
 */

// @lc code=start
class Solution {

    /**
     * 单向广度优先遍历
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        // 第一步：先将wordlist放入到哈希表中，便于判断某个单词是否在wordList中
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        wordSet.remove(beginWord);

        // 第二步：图的广度优先遍历，必须使用队列和表示是否访问过的visited哈希表
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        // 第三步：开始广度优先遍历，包含起点，因此初始化的时候步数为1
        int step = 1;
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                String currentWord = queue.poll();
                // 如果current Word能够修改1个字符与endWord相同，则返回step + 1
                if (changeWordEveryOneLetter(currentWord, endWord, queue, visited, wordSet)) {
                    return step + 1;
                }
            }
            step++;
        }
        return 0;
    }

    /**
     * 尝试对currentWord修改每一个字符，看能不能和endWord匹配
     */
    private boolean changeWordEveryOneLetter(String currentWord, String endWord, Queue<String> queue, Set<String> visited,
            Set<String> wordSet) {
        char[] charArray = currentWord.toCharArray();
        for (int i = 0; i < endWord.length(); i++) {
            // 先保存，然后恢复
            char originChar = charArray[i];
            for (char k = 'a'; k <= 'z'; k++) {
                if (k == originChar) {
                    continue;
                }
                charArray[i] = k;
                String nextWord = new String(charArray);
                if (wordSet.contains(nextWord)) {
                    if (nextWord.equals(endWord)) {
                        return true;
                    }
                    if (!visited.contains(nextWord)) {
                        queue.add(nextWord);
                        // 添加到队列后，必须马上标记为已经访问
                        visited.add(nextWord);
                    }
                }
            }
            // 恢复
            charArray[i] = originChar;
        }
        return false;
    }

    /**
     * 已经起点和终点，使用双向bfs
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 第1步：先将wordlist放入到哈希表中，便于判断某个单词是否在wordList中
        // 能转换成功的话，endWord一定在wordList中
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }

        // 第2步：已经访问过的word添加到visited哈希表中
        Set<String> visited = new HashSet<>();
        // 分别用左边和右边扩散的哈希表代替单项bfs里的队列，他们在双向bfs的过程中交替使用
        Set<String> beginVistied = new HashSet<>();
        beginVistied.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        // 第3步：执行双向bfs，左右交替扩散的步数之和为所求
        int step = 1;
        while (!beginVistied.isEmpty() && !endVisited.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑的情况更少
            if (beginVistied.size() > endVisited.size()) {
                Set<String> temp = beginVistied;
                beginVistied = endVisited;
                endVisited = temp;
            }

            // 逻辑到这里，保证beginVisited是相对较小的集合，nextLevelVisited在扩散完成之后，会成为新的beginVisited
            Set<String> nextLevelVisited = new HashSet<>();
            for (String word : beginVistied) {
                if (changeWordEveryOneLetter(word, endVisited, visited, wordSet, nextLevelVisited)) {
                    return step + 1;
                }
            }
            // 原来的beginVisited废弃，从nextlevelvisited开始新的bfs
            beginVistied = nextLevelVisited;
            step++;
        }
        return 0;
        
    }

    private boolean changeWordEveryOneLetter(String word, Set<String> endVistied, Set<String> visited,
            Set<String> wordSet, Set<String> nextLevelVisited) {
        char[] charArray = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            char originChar = charArray[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == originChar) {
                    continue;
                }
                charArray[i] = c;
                String nextWord = new String(charArray);
                if (wordSet.contains(nextWord)) {
                    if (endVistied.contains(nextWord)) {
                        return true;
                    }
                    if (!visited.contains(nextWord)) {
                        nextLevelVisited.add(nextWord);
                        visited.add(nextWord);
                    }
                }
            }
            // 恢复下次再用
            charArray[i] = originChar;
        }
        return false;
    }
}
// @lc code=end

