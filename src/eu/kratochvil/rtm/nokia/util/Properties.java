/**
 * 
 */
package eu.kratochvil.rtm.nokia.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Properties implementation as there is no in J2ME !
 * 
 * @author karol.bucek@jetminds.com
 *
 * @deprecated not used
 */
public class Properties extends Hashtable {

    private static final int BUFFER_SIZE = 192;
    
    /**
     * Creates new properties.
     */
    public Properties() {
        super(16);
    }
    
    /**
     * Properties load from a .properties file.
     * @param propertyFile
     * @throws IOException
     */
    public void load(final String propertyFile) throws IOException {
        final InputStream propertyFileStream = 
            getClass().getResourceAsStream(propertyFile);
        try {
            load(propertyFileStream);   
        }
        finally {
            try {
                if ( propertyFileStream != null ) propertyFileStream.close();
            }
            catch (IOException e) { /* silently ignore */ }
        }
    }
    
    //private final byte[] buffer = new byte[BUFFER_SIZE];
    //private final StringBuffer stringBuffer = new StringBuffer(2 * BUFFER_SIZE);
    
    /**
     * Properties load from a 'property' stream.
     * @param propertyStream 
     * @throws IOException 
     */
    public void load(final InputStream propertyStream) throws IOException {
        if ( propertyStream == null ) {
            throw new NullPointerException("no property stream");
        }
        
        String content = null;
        try {
            final byte[] buffer = new byte[BUFFER_SIZE];
            final StringBuffer contentBuffer = new StringBuffer(2 * BUFFER_SIZE);
            int readBytes = propertyStream.read(buffer);
            while ( readBytes > 0 ) {
                contentBuffer.append(new String(buffer, 0, readBytes, "UTF-8"));
                readBytes = propertyStream.read(buffer);
            }
            if ( contentBuffer.length() > 0 ) {
                content = contentBuffer.toString();
            }
        } 
        catch (IOException e) {
            throw e;
            //e.printStackTrace();
            //return;
        }
        
        if ( content == null ) return; // file found but nothing in it !
        // parse the string and put keys/values into the properties table
        int currentIndex = 0;

        while ( currentIndex < content.length() ) {
            // TODO there is no support to have \n as a content of the property value ...
            final int newLineIndex = content.indexOf('\n', currentIndex);
            if ( newLineIndex != -1 ) {
                int separatorIndex = indexOf(content, '=', currentIndex, newLineIndex);
                if ( separatorIndex != -1 && content.charAt(currentIndex) != '#' ) {
                    int endLineIndex = newLineIndex;
                    if ( content.charAt(endLineIndex - 1) == '\r' ) { // meta at the end
                        endLineIndex--;
                    }
                    // so here we're sure its a valid .properties line :
                    String propertyKey = content.substring(currentIndex, separatorIndex++);
                    String propertyValue = content.substring(separatorIndex, endLineIndex);
                    this.put(propertyKey, propertyValue);                    
                }
            }
            currentIndex = newLineIndex + 1;
        }
    }
    
    private static int indexOf(String content, char c, int fromIndex, int toIndex) {
        int index = content.indexOf(c, fromIndex);
        return index < toIndex ? index : -1;
    }
    
    /**
     * @see #get(Object)
     * @param key property key
     * @return string value
     */
    public String getProperty(final String key) {
        final Object value = super.get(key);
        return (value instanceof String) ? (String) value : null;
    }

    /**
     * @see #get(Object)
     * @param key property key
     * @param defValue default
     * @return string value
     */
    public String getProperty(final String key, String defValue) {
        final String value = getProperty(key);
        return (value == null) ? defValue : value;
    }
    
    // ADDED UTILITY METHODS FOR THIS PROJECT :
    
    public void putAll(final Hashtable properties) {
        putAll(properties, null, false);
    }

    public void putAll(final Hashtable properties, String keyPrefix, final boolean remPrefix) {
        for (Enumeration e = properties.keys(); e.hasMoreElements();) {
            final String nextKey = (String) e.nextElement();
            if ( keyPrefix != null ) {
                if ( nextKey.startsWith(keyPrefix) ) {
                    String nextKeyStr = nextKey; 
                    if ( remPrefix ) nextKeyStr = nextKey.substring(keyPrefix.length());
                    this.put(nextKeyStr, properties.get(nextKey));   
                }
            }
            else {
                this.put(nextKey, properties.get(nextKey));   
            }
        }
    }
    
}
