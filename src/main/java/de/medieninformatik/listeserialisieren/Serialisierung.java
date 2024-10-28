package de.medieninformatik.listeserialisieren;

import java.io.IOException;
import java.io.*;
import java.util.List;

/**
 * Hier werden Methoden zur Serialisierung und Deserialisierung definiert.
 *
 * @param <T> generischer Datentyp
 */

public class Serialisierung<T> {

    /**
     * Methode der Serialisierung.
     * Hier wird geprüft, ob die Liste den Anforderungen der Serialisierung genügt.
     * Wenn dies der Fall ist, soll die Serialisierung durchgeführt werden.
     *
     * @param liste übergebene Liste
     * @return speichert die Datensätze im serialisierten Feld
     * @throws IOException Fehlermeldung, falls Serialisierung nicht stattfinden kann
     */

    public byte[] serialize(List<T> liste) throws IOException {
        if (liste == null) {
            throw new IllegalArgumentException("Liste darf nicht null sein.");
        }
        // Überprüfen, ob die Liste selbst serialisierbar ist
        if (!(liste instanceof Serializable)) {
            throw new NotSerializableException("Die Liste ist nicht serialisierbar.");
        }

        // Überprüfen, ob jedes Element in der Liste serialisierbar ist
        for (T item : liste) {
            if (!(item instanceof Serializable)) {
                throw new NotSerializableException("Ein Element der Liste ist nicht serialisierbar: " + item);
            }
        }

        // Serialisieren der Liste
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            System.out.println("Jetzt wird serialisert :D");
            oos.writeObject(liste);
            return baos.toByteArray();
        }
    }

    /**
     * Methode der Deserialisierung.
     * Hier wird geprüft, ob das Feld beziehungsweise die
     * Daten in Feld den Anforderungen zur Deserialisierung genügt.
     *
     * @param data Datensätze des Feldes
     * @return deserialisierte Liste
     * @throws IOException Fehlermeldung, falls Deserialisierung nicht stattfinden kann
     * @throws ClassNotFoundException Fehlermeldung, falls Klasse nicht gefunden werden kann
     */

    @SuppressWarnings("unchecked")
    public List<T> deserialize(byte[] data) throws IOException, ClassNotFoundException {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Daten dürfen nicht null oder leer sein.");
        }

        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            System.out.println("Jetzt wird deserialisert :D");
            return (List<T>) ois.readObject();
        }
    }
}
