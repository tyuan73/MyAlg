/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 5/20/17
 * Time: 10:45 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class DesignInMemoryFileSystem588 {
    Obj root = null;

    class Obj {
        Map<String, Obj> list = null;
        String content = null;
        boolean isDir = true;

        Obj() {
            this.list = new TreeMap<>();
            this.content = "";
            this.isDir = true;
        }
    }

    public DesignInMemoryFileSystem588() {
        this.root = new Obj();
    }

    public List<String> ls(String path) {
        String[] p = path.split("/");
        Obj cur = root;
        String name = "/";
        for (String s : p) {
            if (s == null || s.length() == 0) continue;
            cur = cur.list.get(s);
            name = s;
        }
        if (cur.isDir)
            return new ArrayList(cur.list.keySet());
        else {
            ArrayList<String> ans = new ArrayList<>();
            ans.add(name);
            return ans;
        }
    }

    public void mkdir(String path) {
        String[] p = path.split("/");
        Obj cur = root;
        for (String s : p) {
            if (s == null || s.length() == 0) continue;
            if (!cur.list.containsKey(s))
                cur.list.put(s, new Obj());
            cur = cur.list.get(s);
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] p = filePath.split("/");
        Obj cur = root;
        for (String s : p) {
            if (s == null || s.length() == 0) continue;
            if (!cur.list.containsKey(s))
                cur.list.put(s, new Obj());
            cur = cur.list.get(s);
        }
        cur.content += content;
        cur.isDir = false;
    }

    public String readContentFromFile(String filePath) {
        String[] p = filePath.split("/");
        Obj cur = root;
        for (String s : p) {
            if (s == null || s.length() == 0) continue;
            cur = cur.list.get(s);
        }
        return cur.content;
    }

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
}
