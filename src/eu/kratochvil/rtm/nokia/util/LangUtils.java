/**
 * mobile-model created at Aug 5, 2008
 */
package eu.kratochvil.rtm.nokia.util;

/**
 * 
 * @author karol.bucek@jetminds.com
 *
 */
public abstract class LangUtils {

    /**
     * @param str
     * @return true if a string is empty
     */
    public static boolean isEmpty(final String str) {
        return str == null || str.trim().length() == 0;
    }
    
    /**
     * Get the simple class name.
     * @param clazz
     * @return simple class name (without package)
     */
    public static String getSimpleName(final Class clazz) {
        // missing Class.getSimpleName() in J2ME
        final String className = clazz.getName();
        final int delim = className.lastIndexOf('.') + 1;
        return delim == 0 ? className : className.substring(delim);
    }
    
    /*
    public static String getMessage(Throwable e) {
        String msg = e.getMessage();
        if ( msg == null ) msg = "";
        if ( msg.length() == 0 ) {
            msg = getSimpleName(e.getClass());
        }
        else {
            msg = getSimpleName(e.getClass()) + " : " + msg;
        }
        return msg;
    }
    */

    /**
     * A shared string buffer.
     */
    //public static final StringBuffer SHARED_BUFFER = new StringBuffer();
    
}
