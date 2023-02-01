package KnowledgeBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class KnowledgeBoxApplication  {
    private ObservableList<String> skillList =
            FXCollections.observableArrayList("Java", "Hardware Interfacing", "P&OC", "UML", "Wiskunde");
    private ObservableList<String> realizedSkills = FXCollections.observableArrayList();
    public static void main(String[] args) {
    System.out.println("Hello world!");
    }
    

}
