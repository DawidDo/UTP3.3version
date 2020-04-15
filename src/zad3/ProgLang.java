package zad3;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ProgLang  {

    private Map<Character, Set<String>> progMap;
    private Map<String,Set<Character>> langMap;
    private List<String> allLines;
    private Set <Character> progsSet;
    private List<String> langsList;
    public ProgLang(String str){
        langsList = new ArrayList<>();
        progsSet = new LinkedHashSet<>();
        langMap = new LinkedHashMap<>();
        progMap = new LinkedHashMap<>();
        allLines = new ArrayList<>();
        try{
            List<String> allLines = Files.readAllLines(Paths.get(str), Charset.defaultCharset());
            langsList =Pattern.compile("\\s").splitAsStream(allLines.stream().collect(Collectors.joining(" "))).filter(e->e.length() > 1).collect(Collectors.toList());
            List<List<Character>> progsList = new ArrayList<>();
            String[] progs = allLines.stream().toArray(String[]::new);
            for (int i = 0 ; i < progs.length ; i++) {
                String newLine = progs[i].replaceAll("[A-Za-z]{2,}", "").replace("C++","").replace("C#","").replaceAll("\\t","");
                progsList.add(newLine.chars().mapToObj( e -> (char) e).collect(Collectors.toList()));
            }
            Set<Set<Character>> setProg = new LinkedHashSet<>();

            for(List<Character> list : progsList){
                setProg.add(new LinkedHashSet<>(list));
            }

            Iterator<String> lang = langsList.iterator();
            Iterator<Set<Character>> prog = setProg.iterator();

            while (lang.hasNext() && prog.hasNext()) {
                langMap.put(lang.next(), prog.next());
            }

            for(Set<Character> set : setProg){
                for(Character pro : set){
                    progsSet.add(pro);
                }
            }

            for(String  lg : langMap.keySet() ){
                for(Character  pr : langMap.get(lg)){

                    if(progMap.containsKey(pr)){
                        progMap.get(pr).add(lg);}
                    else{
                        Set<String> lag = new LinkedHashSet<>();
                        lag.add(lg);
                        progMap.put(pr,lag);
                    }
                }
            }
        }catch(IOException e){
            System.out.println("Błąd " + e);
        }
    }

    public Map<String, Set<Character>> getLangsMap(){

        return langMap; }

    public Map<Character,Set<String>>getProgsMap(){

        return progMap;}

    public Map <String,Set<Character>>getLangsMapSortedByNumOfProgs(){

        return  langMap.entrySet().stream().
                sorted(Collections.reverseOrder(Comparator.comparing(e -> e.getValue().size()))).
                collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue, (old,newVal) -> old, LinkedHashMap :: new));}

    public Map<Character, Set<String>> getProgsMapSortedByNumOfLangs(){

        return  progMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).
                sorted(Collections.reverseOrder(Comparator.comparing(e -> e.getValue().size()))).
                collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue, (old,newVal) -> old, LinkedHashMap :: new)); }

    public Map <Character,Set<String>> getProgsMapForNumOfLangsGreaterThan(int arg){

        return progMap.entrySet().stream().
                filter(e->e.getValue().size() > arg).
                collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue, (old,newVal) -> old, LinkedHashMap :: new)); }

}
