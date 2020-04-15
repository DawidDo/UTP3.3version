/**
 *
 *  @author Dołowy Dawid S19573
 *
 */

package zad2;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CustomersPurchaseSortFind {
    List<String> words;
    List<Purchase>purchaseList;

    void readFile(String path){
        try{  words = Pattern.compile("\\n")
                .splitAsStream( new String(Files.readAllBytes(Paths.get(path))))
                .filter(f->!f.isEmpty())
                .collect(Collectors.toList());
            purchaseList = new ArrayList<>();
        }catch (IOException E){
            System.out.println(E);
        }
        for(String str : words) {
            String[] myStrings = str.split(";");
            int i = 0;
            purchaseList.add(new Purchase(myStrings[i++], myStrings[i++], myStrings[i++], Double.parseDouble(myStrings[i++]), Double.parseDouble(myStrings[i])));
        }
    }
    void showPurchaseFor(String arg){
        System.out.println("Klient " + arg);
        for(Purchase obj : purchaseList){
            if(obj.getId().compareTo(arg)==0){
                System.out.println(obj.getId()+";"+obj.getName()+";"+obj.getNazwaTowaru()+";"+obj.getIlosc()+";"+obj.getCena());
            }
        }
        System.out.println();
    }
    void showSortedBy (String arg){
        List<Purchase> modifyList = new ArrayList<>();
        modifyList.addAll(purchaseList);
        if(arg.equals("Nazwiska")){
            Collections.sort(modifyList,Comparator.comparing(Purchase::getId));
            Collections.sort(modifyList, Comparator.comparing(Purchase::getName));
            System.out.println(arg);
            for(Purchase obj :modifyList){
                System.out.println(obj.getId()+";"+obj.getName()+";"+obj.getNazwaTowaru()+";"+obj.getIlosc()+";"+obj.getCena());
            }
            System.out.println();
        }
        else if(arg.equals("Koszty")){
            Collections.sort(modifyList,Comparator.comparing(Purchase::getId));
            Collections.sort(modifyList,Comparator.comparing(Purchase::getSuma).reversed());
            System.out.println(arg);
            for(Purchase obj :modifyList){
                System.out.println(obj.getId()+";"+obj.getName()+";"+obj.getNazwaTowaru()+";"+obj.getIlosc()+";"+obj.getCena()+" (koszt: " + obj.getSuma()+")");
            }
            System.out.println();
        }
    }
}