package world.playtogether.strmatch;

import world.playtogether.hashtable.ApHashTable;

/**
 * <project> algoPractice
 *
 * <p> 字典树
 *
 * @author penggs
 * @since 2020-08-17
 */
public class ApTrie {
    private TrieNode root = new TrieNode("/");

    public void insert(String[] text) {
        // 游走的指针
        TrieNode p = root;
        for (String s : text) {
            TrieNode pp = p.children.get(s);
            if (pp == null) {
                TrieNode newNode = new TrieNode(s);
                p.children.put(s, newNode);
                pp = newNode;
            }
            p = pp;
        }
        p.isEnding = true;
    }

    public boolean find(String[] pattern) {
        TrieNode p = root;
        for (String s: pattern) {
            TrieNode pp = p.children.get(s);
            if (pp == null) {
                return false;
            }
            p = pp;
        }
        return p.isEnding;
    }

    @Override
    public String toString() {
        return root.toString();
    }

    public static class TrieNode {
        String data;
        ApHashTable<String, TrieNode> children = new ApHashTable<>();
        public boolean isEnding = false;

        public TrieNode(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"data\":\"" + data + '\"' +
                    ", \"children\":" + (children==null ? "" : children.toString2()) +
                    ", \"isEnding\":" + isEnding +
                    "}";
        }
    }

    public static void main(String[] args) {
        String engStr1 = "How are you";
        String engStr2 = "How to do";
        String engStr3 = "How about a cup of coffee";
        String engStr4 = "How about a cup of beer";
        ApTrie apTrie = new ApTrie();
        apTrie.insert(engStr1.split(" "));
        apTrie.insert(engStr2.split(" "));
        apTrie.insert(engStr3.split(" "));
        apTrie.insert(engStr4.split(" "));
        System.out.println(apTrie.find("How to do".split(" ")));
        System.out.println(apTrie.toString());
    }
}