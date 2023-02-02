import java.io.*;
import java.util.*;
import java.lang.String;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.Comparator;
public class Huffmancoding {
     String s;
    String encodedstr="";
    String decodedstr="";
    HashMap<Character ,Integer >mp=new HashMap<>();
    HashMap<Character ,Double >prob=new HashMap<>();
    //code,string
    static HashMap<String,Character>table1=new HashMap<>();
    //char,code
    static HashMap<Character,String>table2=new HashMap<>();
    Huffmancoding(String text){
        s=text;
    }
    public static void dfs(Node root, String code) {
        if (root.left == null && root.right == null && root.s.length()==1) {
            if(!table2.containsKey(root.s.charAt(0))) {
                table2.put(root.s.charAt(0), code);
                table1.put(code, root.s.charAt(0));
            }
            return;
        }
        dfs(root.left, code + '0');
        dfs(root.right, code + '1');
    }
    public HashMap<Character,String> encoding(){

        //setting the probability for each char
        for (int i = 0; i < s.length(); i++) {
            if(!mp.containsKey(s.charAt(i))){
                mp.put(s.charAt(i),  1);
            }
            else {
                mp.put(s.charAt(i), mp.get(s.charAt(i)) + 1);
            }
        }
        for (Map.Entry<Character,Integer>entry: mp.entrySet())
        {
            prob.put(entry.getKey(),Double.valueOf(entry.getValue()/s.length()));
        }
        PriorityQueue<Node> pq = new PriorityQueue<Node>(prob.size(), new ImplementComparator());
        for (Map.Entry<Character,Double>entry: prob.entrySet())
        {
           Node n1=new Node();
           n1.s=""+entry.getKey();
           n1.prob=entry.getValue();
           n1.right=null;
           n1.left=null;
           pq.add(n1);
        }
        Node root=null;
        while (pq.size()>1){
            Node p1,p2;
            p1=pq.poll();
            p2=pq.poll();
            Node n1=new Node();
            n1.prob = p1.prob + p2.prob;
            n1.s = p1.s+p2.s;
            n1.left = p1;
            n1.right = p2;
            root = n1;
            pq.add(n1);
        }

        dfs(root, "");

        for (int i = 0; i < s.length(); i++) {
            encodedstr+=table2.get(s.charAt(i));
        }
        System.out.println(encodedstr);
        return table2;
    }
    String decoding(){
        for (int i = 0; i < encodedstr.length(); i++) {
            String cur="";
            int j = i;
            for (; j < encodedstr.length(); j++) {
                cur=cur+encodedstr.charAt(j);
                if(table1.containsKey(cur)){
                    decodedstr=decodedstr+table1.get(cur);
                    i=j;
                    break;
                }
            }
        }
        return decodedstr;
    }
    int calculateOrginalsize(){
        return s.length()*9;
    }
    int calculateCompressedsize(){
        return encodedstr.length();
    }
}
