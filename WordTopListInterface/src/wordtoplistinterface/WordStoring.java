/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistinterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 * @author laszlop
 */
public class WordStoring implements WordStore {

    private final List<URL> urlList;
    private final Set<String> skipTags; // TODO LP: I think there are two categories skip tags and skip words, so you should store the skipWords in another Set
    private final Set<Character> separators;
    private final Map<String, Integer> wordFrequency = new HashMap<>();
    private final static Logger LOGGER = Logger.getLogger(WordStoring.class.getName());

    public WordStoring(List<URL> urlList) {
        this.urlList = urlList;
        this.skipTags = new HashSet<>(Arrays.asList("head", "style")); // texts between these tags are ignored
        this.separators = new HashSet<>(Arrays.asList(' ','"','(', ')','*', '<', '.', ':', '?', '!', ';', '-', '–', '=', '{', '}'));
    }

    public void processURLs() throws IOException {
        for (int i = 0; i < urlList.size(); i++) {
            URL url = urlList.get(i);
            processContent(url);
        }
    }

    public void processContent(URL url) throws IOException {
        LOGGER.info("Processing of the homepage " + url.toString() + " started");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String openingTag = findOpeningTag(reader);
        LOGGER.info(openingTag + " (as opening tag) identified.");
        eatTag(openingTag, reader);
        reader.close(); // TODO LP: reader must be closed even if an error happens 
        // TODO LP: use try-finally or try-with-resource -> https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
    }

    private void eatTag(String tag, BufferedReader reader) throws IOException {
        int value;
        StringBuilder word = new StringBuilder();
        while ((value = reader.read()) != -1) {
            char character = (char) value;
            if (character == '<') {
                if (!skipTags.contains(tag)) {
                    store(word.toString().toLowerCase());
                }
                String nextTagString = buildTag(reader);
                if (('/' + tag).equals(nextTagString)) {
                    return;
                }
                if (!skipTags.contains(tag) && !nextTagString.startsWith("/")) {
                    eatTag(nextTagString, reader);
                }
            }
            if (separators.contains(character) || Character.isWhitespace(character)) {
                if (!skipTags.contains(tag)) {
                    store(word.toString().toLowerCase());
                }
                word.setLength(0);
                continue;
            }
            word.append(character);
        }
    }

    private String findOpeningTag(BufferedReader reader) throws IOException {
        int value;
        String openingTag = "";
        while ((value = reader.read()) != -1) {
            char character = (char) value;
            if (character == '<') {
                openingTag = buildTag(reader);
                return openingTag;
            }
        }
        return openingTag;
    }

    private String buildTag(BufferedReader reader) throws IOException {
        StringBuilder tag = new StringBuilder();
        int value;
        while ((value = reader.read()) != -1) {
            char tagChar = (char) value;
            if (tagChar == '>') {
                return tag.toString().toLowerCase();
            }
            tag.append(tagChar);
        }
        return tag.toString().toLowerCase();
    }

    @Override
    public void store(String word) {
        if (word.length() > 1 && !skipTags.contains(word)) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            // TODO LP: your solution is also fine, please check my solution as well
//            Integer value = wordFrequency.get(word);
//            wordFrequency.put(word, value == null ? 1: value+1);
        }        
    }

    @Override
    public void addSkipWord(String word) {
        skipTags.add(word);
        LOGGER.info(word + " (as tag to skip) added.");
    }

    @Override
    public void print() {
        System.out.println("Full frequency list: " + sortedWordFreq());
    }

    @Override
    public void print(int n) {
        List<Map.Entry<String, Integer>> sortedList = sortedWordFreq();
        System.out.print("The " + n + " most used words:");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + sortedList.get(i));
        }
        System.out.println(""); // TODO LP: do you need this? :)
    }

    private List<Map.Entry<String, Integer>> sortedWordFreq() { 
    // TODO LP: please check my version, this is more compact, your version did a lot of extra work
        ArrayList<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordFrequency.entrySet());
        Collections.sort(sortedList, new WordFreqComparator());
        Collections.reverse(sortedList); // TODO LP: you can implement WordFreqComparator the way that you don't need to call the reverse()
        return sortedList;
    }

}
