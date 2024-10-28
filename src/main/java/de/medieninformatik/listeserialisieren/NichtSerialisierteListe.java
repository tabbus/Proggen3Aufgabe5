package de.medieninformatik.listeserialisieren;

import java.util.AbstractList;

/**
 * Die Klasse legt fest, wie eine nicht-serialisierbare
 * Liste aussehen soll.
 *
 * @param <T> generischer Datentyp
 */

public class NichtSerialisierteListe<T> extends AbstractList<T>{

    private final T[] elemente;

    /**
     * Konstruktor der nicht-serialisierbaren Liste.
     *
     * @param elemente Inhalte der Liste
     */

    @SuppressWarnings("unchecked")
    public NichtSerialisierteListe(T... elemente) {
        this.elemente = elemente;
    }

    /**
     * Get-Methode des Indexes der Elemente.
     *
     * @param index Index des zurückgegebenen Elements
     * @return gibt die Elemente an der Stelle des Index zurück
     */

    @Override
    public T get(int index) {
        return elemente[index];
    }

    /**
     * Größe der Liste.
     *
     * @return Länge des Feldes elemente
     */

    @Override
    public int size() {
        return elemente.length;
    }
}