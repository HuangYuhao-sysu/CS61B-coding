import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B {

    public class Node {
        String item;
        HashMap<Character, Node> children;

        public Node() {
            item = "";
            children = new HashMap<Character, Node>();
        }
    }

    Node root;

    public MyTrieSet() {
        root = new Node();
    }

    @Override
    public void clear() {
        root = new Node();
    }

    @Override
    public boolean contains(String key) {
        return contains(root, key);
    }

    private boolean contains(Node n, String key) {
        if (key == "") return n.item != "";
        Character first = key.charAt(0);
        String rest = key.substring(1, key.length());
        if (n.children.containsKey(first)) {
            return contains(n.children.get(first), rest);
        }
        return false;
    }

    @Override
    public void add(String key) {
        if (key == "") throw new IllegalArgumentException("key is null");
        add(root, key, key);
    }

    private void add(Node n, String key, String orgKey) {
        if (key == "") {
            n.item = orgKey;
            return;
        }

        Character first = key.charAt(0);
        String rest = key.substring(1, key.length());
        if (!n.children.containsKey(first)) {
            Node newNode = new Node();
            n.children.put(first, newNode);
            add(newNode, rest, orgKey);
        } else {
            add(n.children.get(first), rest, orgKey);
        }
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        return keysWithPrefix(root, prefix);
    }

    private List<String> keysWithPrefix(Node n, String prefix) {
        if (prefix == "") return findAll(n);
        Character first = prefix.charAt(0);
        String rest = prefix.substring(1, prefix.length());
        if (!n.children.containsKey(first)) return null;
        return keysWithPrefix(n.children.get(first), rest);
    }

    private List<String> findAll(Node n) {
        if (n == null) {
            return null;
        } else {
            ArrayList<String> l = new ArrayList<String>();
            for (Character c : n.children.keySet()) {
                l.addAll(findAll(n.children.get(c)));
            }
            if (n.item != "") l.add(n.item);
            return l;
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        List<String> l = keysWithPrefix(key);
        String longest = "";
        for (String s : l) {
            if (s.length() > longest.length()) longest = s;
        }
        return longest;
    }
}
