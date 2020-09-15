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
import java.util.List;

/**
 * This application allows you to give some URL-s, and it will collect the X most used words of the content of the given homepages.
 * You can add skipwords, which will not be collected. You can define the value of X as well.
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
              
        WordStoring wordStoring = new WordStoring(urlList);
        wordStoring.addSkipWord("an");
        wordStoring.addSkipWord("and");
        wordStoring.addSkipWord("by");
        wordStoring.addSkipWord("for");
        wordStoring.addSkipWord("if");
        wordStoring.addSkipWord("in");
        wordStoring.addSkipWord("is");
        wordStoring.addSkipWord("it");
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
