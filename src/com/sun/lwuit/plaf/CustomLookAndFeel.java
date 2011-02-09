/**
 * mobile-client created at Jul 24, 2008
 */
package com.sun.lwuit.plaf;

import com.sun.lwuit.Component;
import com.sun.lwuit.Graphics;
import com.sun.lwuit.Label;
import com.sun.lwuit.plaf.DefaultLookAndFeel;
import com.sun.lwuit.plaf.UIManager;

/**
 * Here to have package visible stuff accessible !
 * 
 * @author karol.bucek@jetminds.com
 * 
 */
public final class CustomLookAndFeel extends DefaultLookAndFeel {
    
    /**
     * Custom look and feel for PeersOne.
     */
    public CustomLookAndFeel() {
        super();
    }
    
    /**
     * @see com.sun.lwuit.plaf.DefaultLookAndFeel#drawLabelText(com.sun.lwuit.Graphics,
     *      com.sun.lwuit.Label, java.lang.String, int, int, int)
     */
    public int drawLabelText(final Graphics g, Label label, String text, int x, int y, int txtSpaceW) {
        // make this method accessible (used by ImageActionButton) :
        return super.drawLabelText(g, label, text, x, y, txtSpaceW);
    }

    /**
     * @see LookAndFeel#drawBorder(Graphics, Component, int, int, int)
     */
    public void drawBorder(Graphics g, Component c, int topAndRightColor, int bottomAndLeftColor, int borderWidth) {
        // make this method accessible (used by "customized" Calendar) :
        super.drawBorder(g, c, topAndRightColor, bottomAndLeftColor, borderWidth);
    }
    
    /**
     * @see com.sun.lwuit.plaf.DefaultLookAndFeel#getTextAreaPreferredSize(com.sun.lwuit.TextArea)
     */
    // ... it seemed slow on performance :
    /*
    public Dimension getTextAreaPreferredSize(final TextArea textArea) {
        int prefW = 0;
        int prefH = 0;
        final Style style = textArea.getStyle();
        final Font font = style.getFont();
        final int rows = textArea.getRows();
        final int columns = textArea.getColumns();
        if ( rows == 1 ) {
            prefW = font.stringWidth(textArea.getText());
        }
        else {
            prefW = font.stringWidth("W") * columns;
        }
        prefH = (font.getHeight() + 2) * rows;
        final StringBuffer str = new StringBuffer(columns);
        for (int i = 0; i < columns; i++) str.append('W');        
        prefW = Math.max(prefW, font.stringWidth(str.toString()));
        prefH = Math.max(prefH, rows * font.getHeight());
        return new Dimension(prefW + style.getPadding(3)
                + style.getPadding(1), prefH
                + style.getPadding(0) + style.getPadding(2));
    }
    */
    
    /**
     * Package friendly helper.
     * @return out custom L&F
     */
    public static CustomLookAndFeel get() {
        return (CustomLookAndFeel) UIManager.getInstance().getLookAndFeel();
    }
    
}
