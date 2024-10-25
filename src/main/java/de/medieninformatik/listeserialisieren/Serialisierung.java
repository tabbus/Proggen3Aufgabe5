package de.medieninformatik.listeserialisieren;

import java.io.IOException;
import java.io.*;
import java.util.List;

public class Serialisierung<T> {

    public byte[] serialize(List<T> list) throws IOException {
        if (list == null) {
            throw new IllegalArgumentException("Liste darf nicht null sein.");
        }
        // Überprüfen, ob die Liste selbst serialisierbar ist
        if (!(list instanceof Serializable)) {
            throw new NotSerializableException("Die Liste ist nicht serialisierbar.");
        }

        // Überprüfen, ob jedes Element in der Liste serialisierbar ist
        for (T item : list) {
            if (!(item instanceof Serializable)) {
                throw new NotSerializableException("Ein Element der Liste ist nicht serialisierbar: " + item);
            }
        }

        // Serialisieren der Liste
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(list);
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
            return (List<T>) ois.readObject();
        }
    }
}
