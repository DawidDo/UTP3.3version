/**
 *
 *  @author Dołowy Dawid S19573
 *
 */

package zad1;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;



public class Anagrams  {
    private  List<List<String>> myList;
    private List<String> words;
    public Anagrams(String file){
        try{
            words = Pattern.compile("[\\s]")
                    .splitAsStream( new String(Files.readAllBytes(Paths.get(file))))
                    .filter(e->!e.isEmpty())
                    .collect(Collectors.toList());
        }
        catch (IOException e){
            System.out.println(e);
        }
        myList = new ArrayList<>();
    }

    List<List<String>> getSortedByAnQty(){
        List <String> myMod = words;
        while (!myMod.isEmpty()) {
            ArrayList<String> anagramy = new ArrayList<>();
            anagramy.add(myMod.get(0));
            char[] w1 = myMod.get(0).toCharArray();
            myMod.remove(0);
            Arrays.sort(w1);
            for (int i = 0; i < myMod.size(); i++) {
                char[] w2 = myMod.get(i).toCharArray();
                Arrays.sort(w2);
                if (Arrays.equals(w1, w2)) {
                    anagramy.add(myMod.get(i));
                    myMod.remove(i--);
                }
            }
            myList.add(anagramy);
        }
        return myList.stream().sorted(Comparator.comparing(e-> e.toString().length()).reversed()).collect(Collectors.toList());}

    String  getAnagramsFor(String word){
        List <String> listWithGroup = new ArrayList<>();
        char [] w1 = word.toCharArray();
        Arrays.sort(w1);

        for(List<String> list : myList){
            for(String str : list){
                char [] w2 = str.toCharArray();
                Arrays.sort(w2);
                if(Arrays.equals(w1,w2)) {
                    if(!word.equals(str))
                        listWithGroup.add(str);
                }
            }
        }
        return word+": " +listWithGroup;
    }
}