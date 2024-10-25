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
        List<String> list = new ArrayList<>();
        list.add("Test");
        list.add("Hello");

        Serialisierung<String> serializer = new Serialisierung<>();
        byte[] data = serializer.serialize(list);

        List<String> deserializedList = serializer.deserialize(data);
        System.out.println("Liste wurde serialisiert und deserialisert.");
        assertEquals(list, deserializedList);
        System.out.println("Ergebnisse stimmen überein: " + list + deserializedList);
    }

    @Test
    public void testNotSerializableList() {
        List<String> list = new NichtSerialisierteListe<>("A", "B", "C");

        Serialisierung<String> serializer = new Serialisierung<>();
        assertThrows(NotSerializableException.class, () -> serializer.serialize(list));
    }

    @Test
    public void testNonSerializableElement() {
        List<Object> list = new ArrayList<>();
        list.add("Serializable Element");
        list.add(new Object());  // Object ist nicht serialisierbar

        Serialisierung<Object> serializer = new Serialisierung<>();
        assertThrows(NotSerializableException.class, () -> serializer.serialize(list));
    }
}
