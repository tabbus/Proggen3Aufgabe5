package de.medieninformatik.listeserialisieren;

import java.io.IOException;
import java.io.*;
import java.util.List;

public class Serialisierung<T> {

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
