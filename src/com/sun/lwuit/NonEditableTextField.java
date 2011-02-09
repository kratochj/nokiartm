/**
 * mobile-client created at May 27, 2009
 */
package com.sun.lwuit;

/**
 * A text filed hack with disabled "native" edit string capability.
 * 
 * @author karol.bucek@jetminds.com
 * 
 */
public final class NonEditableTextField extends TextField {
    
    /**
     * Construct text field/area depending on whether native in place editing is supported 
     * 
     * @param text the text of the field
     * @param columns - the number of columns
     * @return a text field if native in place editing is unsupported and a text area if it is
     */
    public static TextArea create(String text, int columns) {
        if ( Display.getInstance().getImplementation().isNativeInputSupported() ) {
            return new TextArea(text, 1, columns);
        }
        return new NonEditableTextField(text, columns);
    }
    
    /**
     * Construct text field 
     * 
     * @param text the text of the field
     * @param columns - the number of columns
     */
    public NonEditableTextField(String text, int columns) {
        super(text, columns);
    }
    
    /**
     */
    void editString() {
        // NOOP
    }
    
}
