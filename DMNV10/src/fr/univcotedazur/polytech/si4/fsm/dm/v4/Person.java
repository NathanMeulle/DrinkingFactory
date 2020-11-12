package fr.univcotedazur.polytech.si4.fsm.dm.v4;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String id;
    private List<Integer> achats;

    public Person(String id, int achat){
        this.id = id;
        achats = new ArrayList<>();
        achats.add(achat);
    }

    public String getId() {
        return id;
    }

    public List<Integer> getAchats() {
        return achats;
    }

    public void addAchat(int achat){
        achats.add(achat);
    }

    public int remise(){
        int somme = 0;
        for (int achat: achats) {
            somme += achat;
        }
        return somme/10;
    }

    public void clearAchats(){
        achats.clear();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", achats=" + achats +
                '}';
    }
}
