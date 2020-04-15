/**
 *
 *  @author Dołowy Dawid S19573
 *
 */

package zad2;


public class Purchase implements Comparable {
    private String id;
    private String name;
    private String nazwaTowaru;
    private double cena;
    private double ilosc;

    Purchase(String id, String name, String nazwaTowaru, double ilosc, double cena){
        this.cena = cena;
        this.id  = id;
        this.name = name;
        this.nazwaTowaru = nazwaTowaru;
        this.ilosc = ilosc;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public String getName(){
        return this.name;
    }
    public String getId(){return this.id;}
    public String getNazwaTowaru(){return this.nazwaTowaru;}
    public double getCena(){return this.cena;}
    public double getIlosc(){return this.ilosc;}
    public double getSuma(){return this.cena* this.ilosc;}
}
