/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistinterface;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List; // TODO LP: please do not leave unused imports in the file! (CTRL+SHIFT+i)

/**
 *
 * @author laszlop
 */
public class WordTopListInterface {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        System.out.println("WordTopListRecursive application started.");
        List<URL> urlList = new ArrayList<>();
        urlList.add(new URL("https://justinjackson.ca/words.html"));
        urlList.add(new URL("http://abouthungary.hu/"));
        urlList.add(new URL("https://www.javatpoint.com/java-tutorial"));
        urlList.add(new URL("https://www.bbc.com/"));
        System.out.println("Checked URL-s: " + urlList);
        WordCounter wordCounter = new WordCounter(urlList);
        wordCounter.printTopList();
               
        // TODO LP: I can do everything without WordCounter class
        WordStoring wordStoring = new WordStoring(urlList);
        wordStoring.addSkipWord("an");
        wordStoring.addSkipWord("and");
        wordStoring.addSkipWord("by");
        wordStoring.addSkipWord("for");
        wordStoring.addSkipWord("if");
        wordStoring.addSkipWord("in");
        wordStoring.addSkipWord("is");
        wordStoring.addSkipWord("of");
        wordStoring.addSkipWord("on");
        wordStoring.addSkipWord("that");
        wordStoring.addSkipWord("the");
        wordStoring.addSkipWord("to");
        wordStoring.addSkipWord("with");
        
        wordStoring.processURLs();
        
        wordStoring.print(10);
    }

}
