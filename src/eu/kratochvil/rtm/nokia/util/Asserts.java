/**
 * mobile-model created at Aug 10, 2008
 */
package eu.kratochvil.rtm.nokia.util;

/**
 * Assertion utility class.
 * 
 * @author karol.bucek@jetminds.com
 *
 */
public abstract class Asserts {

    /**
     * Assert.
     * @param obj
     */
    public static void assertNull(Object obj) {
        assertNull(obj, null);
    }
    
    /**
     * Assert.
     * @param obj
     * @param msg
     */
    public static void assertNull(Object obj, String msg) {
        if ( obj != null ) throw new AssertionError(msg);
    }
    
    /**
     * Assert.
     * @param obj
     */
    public static void assertNotNull(Object obj) {
        assertNotNull(obj, null);
    }
    
    /**
     * Assert.
     * @param obj
     * @param msg
     */
    public static void assertNotNull(Object obj, String msg) {
        if ( obj == null ) throw new AssertionError(msg);
    }

    /**
     * Assert.
     * @param bool
     */
    public static void assertTrue(boolean bool) {
        assertTrue(bool, null);
    }

    /**
     * Assert.
     * @param bool
     * @param msg
     */
    public static void assertTrue(boolean bool, String msg) {
        if ( ! bool ) throw new AssertionError(msg);
    }
    
    /**
     * Assert.
     * @param bool
     */
    public static void assertFalse(boolean bool) {
        assertFalse(bool, null);
    }

    /**
     * Assert.
     * @param bool
     * @param msg
     */
    public static void assertFalse(boolean bool, String msg) {
        if ( bool ) throw new AssertionError(msg);
    }
    
    /**
     * Assert.
     * @param exp
     * @param val
     */
    public static void assertEquals(int exp, int val) {
        assertEquals(exp, val, null);
    }

    /**
     * Assert.
     * @param exp
     * @param val
     * @param msg
     */
    public static void assertEquals(int exp, int val, String msg) {
        if ( exp != val ) throw new AssertionError(msg);
    }
    
    /**
     * Assert.
     * @param msg
     */
    public static void fail(String msg) {
        throw new AssertionError(msg);
    }
    
    private static class AssertionError extends Error {
        
        AssertionError() {
            super();
        }

        AssertionError(String msg) {
            super(msg);
        }
        
    }
    
}
