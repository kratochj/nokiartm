package eu.kratochvil.rtm.nokia;

import com.sun.lwuit.*;
import com.sun.lwuit.events.*;
import com.sun.lwuit.impl.midp.VKBImplementationFactory;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
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

            form = new Form("Remmember The Milk - Dashboard");
            form.setLayout(new BoxLayout(BoxLayout.Y_AXIS));


            form.addComponent(new Label("Test"));

            form.addCommand(new Command("Settings") {
                    public void actionPerformed(ActionEvent evt) {
                        Settings settings = new Settings();
                        settings.show();
                    }

            });

            form.show();

        }


    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void actionPerformed(ActionEvent arg0) {
     notifyDestroyed();
    }
}
