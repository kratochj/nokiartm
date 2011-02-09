/**
 * mobile-model created at Jul 31, 2008
 */
package eu.kratochvil.rtm.nokia.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Collection utilities.
 * 
 * @author karol.bucek@jetminds.com
 * 
 */
public abstract class CollectionUtils {
    
    /**
     * Add all elements from the map to the vector.
     * The map element is assumed to be an object or a {@link Vector}.
     * @param vec
     * @param map
     */
    public static void addAllWithNestableValues(final Vector vec, final Hashtable map) {
        for (Enumeration e = map.elements(); e.hasMoreElements();) {
            final Object next = e.nextElement();
            if ( next instanceof Vector ) { 
                // values might be nested in another vector :
                addAll(vec, (Vector) next);
            }
            else { // simple value just add it :
                vec.addElement(next);    
            }
        }
    }
    
    /**
     * Add all elements to the given vector.
     * @param vec
     * @param elems
     */
    public static void addAll(final Vector vec, final Vector elems) {
        for (int i=0; i<elems.size(); i++) {
            vec.addElement(elems.elementAt(i));
        }
    }
    
    /**
     * Print an array.
     * @param array
     */
    public static void printArray(Object[] array) {
        if ( array != null ) {
            for (int i=0; i<array.length; i++) {
                System.out.print(array[i] + " ");
            }
        }
        System.out.println("");
    }
    
}
