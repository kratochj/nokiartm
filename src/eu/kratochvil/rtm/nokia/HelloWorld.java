package eu.kratochvil.rtm.nokia;

import com.sun.lwuit.*;
import com.sun.lwuit.events.*;
import com.sun.lwuit.impl.midp.VKBImplementationFactory;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.util.Log;
import javax.microedition.midlet.*;

/**
 * @author jirikratochvil
 */
public class HelloWorld extends MIDlet  implements ActionListener {
    // The Displayable. This component is displayed on the
    // screen.
    private Form form;

    private boolean started = false;

    // The Display. This object manages all Displayable
    // components.
    private Display display;
    
    public void startApp() {

        VKBImplementationFactory.init();
        Display.init(this);

        // distinguish between start and resume from pause
        if(!started) {
            started = true;
            Log.p("Application started");

            // show your LWUIT form here e.g.: new MyForm().show();
            // this is a good place to set your default theme using
            // the UIManager class e.g.:
            try {
                com.sun.lwuit.util.Resources res = com.sun.lwuit.util.Resources.open("/theme.res");
                com.sun.lwuit.plaf.UIManager.getInstance().setThemeProps(res.getTheme(res.getThemeResourceNames()[0]));
            } catch(java.io.IOException err) {
                 err.printStackTrace();
            }
            Settings settings = new Settings();
            settings.show();
        }



//        Form mainForm = new Form("Hello, LWUIT!");
//        mainForm.setLayout(new BorderLayout());
//
//        TabbedPane tabbedPane = new TabbedPane(TabbedPane.TOP);
//        tabbedPane.addTab("Tab 1", new Label("I am a TabbedPane!"));
//        tabbedPane.addTab("Tab 2", new Label("Tab number 2"));
//
//        mainForm.addComponent(BorderLayout.NORTH, tabbedPane);
//
//        mainForm.show();
        Settings settings = new Settings();
        settings.show();
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void actionPerformed(ActionEvent arg0) {
     notifyDestroyed();
    }
}
