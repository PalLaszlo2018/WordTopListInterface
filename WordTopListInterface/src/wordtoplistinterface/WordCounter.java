/*


TASK DESCRIPTION:

1. Adott egy url lista (Egyelőre konstansként deklaráljuk). Listázzuk ki
az url-eken található html tartalmakban a 10 (vagy X) leggyakrabban
előfoduló szót, ami nem html tag vagy attribútum.
Legyen továbbá egy skipword halmaz, amit nem tekintünk szónak, ahova
kötőszavakat írjuk fel. HashMap-et és HashSet-et kell használni, hogy
elég gyors legyen. (ha ismeretlen a HashMap logikája, olvass róla itt:
https://beginnersbook.com/2013/12/hashmap-in-java-with-example/ -
továbbá google segítségével derítsd ki, mitől jó egy kulcs a HashMap esetén)
Legyen egy skipTags halmaz is, ami olyan tagokat sorol fel, amik közé
írt szavakat nem vesszük figyelembe.
(pl. <tag1> dsd dsfg ds<tag2><tag2>  dsfgdsf</tag2></tag2> dsfsd<tag1>
a skipTag-ek közé kezdetben a style, head szavakat vesszük fel.

A tag-eket rekurziv módonon dolgozza fel egy eatTag() method (paramétere lehet)
 */
package wordtoplistinterface;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 *
 * @author laszlop
 */
public class WordCounter {

    private final List<URL> urlList;
    private final static int LENGTH_OF_TOPLIST = 10;

    public WordCounter(List<URL> urlList) {
        this.urlList = urlList;
    }
    
    public void printTopList() throws IOException {
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
        wordStoring.print();        
        wordStoring.print(LENGTH_OF_TOPLIST);    
    }


}
