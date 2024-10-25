import de.medieninformatik.listeserialisieren.NichtSerialisierteListe;
import de.medieninformatik.listeserialisieren.Serialisierung;
import org.junit.jupiter.api.Test;

import java.io.NotSerializableException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SerialisierungTest {

    @Test
    public void testSerializeAndDeserialize() throws Exception {
        List<String> liste = new ArrayList<>();
        liste.add("Test");
        liste.add("Hallo");

        Serialisierung<String> serializer = new Serialisierung<>();
        byte[] data = serializer.serialize(liste);

        List<String> deserializedList = serializer.deserialize(data);
        System.out.println("Liste wurde serialisiert und deserialisert.");
        assertEquals(liste, deserializedList);
        System.out.println("Ergebnisse stimmen überein: " + liste + deserializedList);
    }

    @Test
    public void testNotSerializableList() {
        List<String> liste = new NichtSerialisierteListe<>("A", "B", "C");

        Serialisierung<String> serializer = new Serialisierung<>();
        assertThrows(NotSerializableException.class, () -> serializer.serialize(liste));
        System.out.println("Achtung: Die Liste ist nicht serialisierbar.");

        // Die Elemente müssten für die Serialiserung in eine neue Liste umkopiert werden
        List<String> neueListe = new ArrayList<>(liste);
        System.out.println("Listenelemente wurden in neue Liste kopiert.");
        System.out.println( "Neue Liste: " + neueListe);
    }

    @Test
    public void testNonSerializableElement() {
        List<Object> liste = new ArrayList<>();
        liste.add("Serializable Element");
        liste.add(new Object());  // Object ist nicht serialisierbar

        Serialisierung<Object> serializer = new Serialisierung<>();
        assertThrows(NotSerializableException.class, () -> serializer.serialize(liste));
        System.out.println("Achtung: Das Element ist nicht serialisierbar.");
    }
}
