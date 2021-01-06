package world.playtogether.hot;

/**
 * 字典树
 */
public class ApTrie {
    private boolean isEnd;
    private ApTrie[] next = new ApTrie[26];

    public void insert(String word) {
        ApTrie root = this;
        char[] chars = word.toCharArray();
        for (char ch: chars) {
            int index = ch - 'a';
            if (root.next[index] == null) root.next[index] = new ApTrie();
            root = root.next[index];
        }
        root.isEnd = true;
    }

    public boolean search(String word){
        ApTrie root = this;
        char[] chars = word.toCharArray();
        for (char ch: chars) {
            int index = ch - 'a';
            if (root.next[index] == null) return false;
            root = root.next[index];
        }
        return root.isEnd;
    }

    public boolean startWith(String word){
        ApTrie root = this;
        char[] chars = word.toCharArray();
        for (char ch: chars) {
            int index = ch - 'a';
            if (root.next[index] == null) return false;
            root = root.next[index];
        }
        return true;
    }
}
