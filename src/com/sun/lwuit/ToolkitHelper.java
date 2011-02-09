/**
 * mobile-client created at Oct 24, 2008
 */
package com.sun.lwuit;

import eu.kratochvil.rtm.nokia.util.Asserts;
import java.util.Date;


/**
 * LWUIT helper (for non-accessible) methods.
 * 
 * @author karol.bucek@jetminds.com
 */
public abstract class ToolkitHelper {
    
    /**
     * @see Display#blockEvents(boolean)
     * @param block
     */
//    public static void blockEvents(final boolean block) {
//        Display.getInstance().blockEvents(block);
//    }

    /**
     * Set the {@link Dialog} to "behave" like a menu.
     * @param dialog
     */
    public static void setDialogMenu(final Dialog dialog) {
        dialog.setMenu(true);
    }

    /**
     * @param dialog
     * @param modal
     */
    /*
    public static void showDialog(final Form dialog, boolean modal) {
        dialog.showDialog(modal);
    }
    */
    
    /**
     * Setter that's missing from {@link Calendar}.
     * @param calendar 
     * @param date
     */
    public static void setCalendarDate(final Calendar calendar, final Date date) {
        final Calendar.MonthView monthView = (Calendar.MonthView) calendar.getComponentAt(1);
        monthView.setSelectedDay(date.getTime());
    }
    
    /**
     * Invokes {@link Calendar#componentChanged()}.
     * @param calendar
     */
    /*
    public static void invokeCalendarChanged(final Calendar calendar) {
        calendar.componentChanged();
    }
    */
    
    /*
    public static Form getUpcomingForm() {
        return Display.getInstance().getCurrentUpcoming();
    }
    
    public static void disposeDialog(final Dialog dialog) {
        dialog.disposeImpl();
    }
    */
    
    /**
     * Get the menu bar component of a form.
     * @param form
     * @return menu bar
     */
    public static Component getMenuBar(final Form form) {
        // Form has 3 components : 
        // super.addComponent(BorderLayout.NORTH, title);
        // super.addComponent(BorderLayout.CENTER, contentPane);
        // super.addComponent(BorderLayout.SOUTH, menuBar);
        Component menuBar = null;
        // the menu is added 'South' : super.addComponent("South", menuBar);
        for (int i = 2; i >= 0; i++) {
            final Component formComp = form.getComponentAt(i);
            if ( formComp instanceof Form.MenuBar ) {
                menuBar = formComp; break;
            }
        }
        Asserts.assertNotNull(menuBar, "menuBar not found for form: " + form);
        return menuBar;
    }
    
    public static void initFocused(final Form form) {
        form.initFocused();
    }
    
//    public static void setCurrentDisplay(final Form form) {
//        Display.getInstance().setCurrent(form);
//    }
    
    public static void setCurrentForm(final Form form) {
        Display.getInstance().setCurrentForm(form);
    }

    public static void repaint(final Form form) {
        Display.getInstance().repaint(form);
    }
    
    /*
    public static void invokeAndBlock(final Form form, final Painter painter) {
        Display.getInstance().invokeAndBlock(new RunnableWrapper(form, painter));
    }
    */
    
}
