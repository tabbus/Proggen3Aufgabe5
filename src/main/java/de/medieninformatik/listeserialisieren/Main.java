package de.medieninformatik.listeserialisieren;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Erstellen einer serialisierbaren Liste mit generischen Typen
        List<String> liste = new ArrayList<>();
        liste.add("Test");
        liste.add("Hello");
        liste.add("Serializable List");

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