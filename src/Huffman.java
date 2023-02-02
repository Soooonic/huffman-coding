import java.io.*;
import java.util.*;
import java.lang.String;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
public class Huffman {

    public static void main(String[] args) {
            StringBuilder word= new StringBuilder();
        try {
            FileReader reader = new FileReader("MyFile.txt");
            int character;
            while ((character = reader.read()) != -1) {
                word.append((char) character);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter("output.txt");
            Huffmancoding h=new Huffmancoding(word.toString());
            writer.write("Encoding: \n");
            HashMap<Character,String>mp=h.encoding();
            for (Map.Entry<Character,String>entry: mp.entrySet())
            {
                writer.write(entry.getKey());
                writer.write(": ");
                writer.write(entry.getValue());
                writer.write("\n");
            }
            writer.write("Decoding: ");
            writer.write(h.decoding());
            writer.write("\n");
            writer.write("Orginal size : ");
            writer.write(String.valueOf(h.calculateOrginalsize()));
            writer.write("\n");
            writer.write("Compression size: ");
            writer.write(String.valueOf(h.calculateCompressedsize()));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//aabbcccddddeeffff