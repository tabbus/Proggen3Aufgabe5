package de.medieninformatik.listeserialisieren;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luk Weiser m30971
 * @author Tabea Sudrow m30902
 * date: 21.10.2024
 * Programmierung 3 Aufgabe 5
 *
 * Beschreibung der Klasse:
 * In der Klasse wird eine Liste erstellt die als Grundlage dient, die Serialisierung
 * und Deserialisierung darzustellen.
 */
public class Main {

    /**
     * In der main-Methode wird zunächst die Liste befüllt, wobei die Methoden
     * der Serialisierung und Deserialisierung angewandt werden.
     * Falls die Klasse nicht gefunden wird, soll eine Exception
     * geworfen werden.
     *
     * @param args
     */
    public static void main(String[] args) {
        // Erstellen einer serialisierbaren Liste mit generischen Typen
        List<String> liste = new ArrayList<>();
        liste.add("Test");
        liste.add("Hallo!");
        liste.add("Dies ist eine serialisierbare Liste :D");

        System.out.println(liste);

        // Erstellen einer Instanz von ListSerializer
        Serialisierung<String> serializer = new Serialisierung<>();

        try {
            // Serialisieren der Liste
            byte[] serializedData = serializer.serialize(liste);
            System.out.println("Liste wurde erfolgreich serialisiert!");

            // Deserialisieren der Liste
            List<String> deserializedList = serializer.deserialize(serializedData);
            System.out.println("Liste wurde erfolgreich deserialisiert!");

            // Ausgabe der deserialisierten Liste
            System.out.println("Deserialisierte Liste: " + deserializedList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}