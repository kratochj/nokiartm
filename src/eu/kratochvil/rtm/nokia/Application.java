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
public class Application {

    static String getVersion() {
        return "0.1";
    }
    // The Displayable. This component is displayed on the
    // screen.

    private Form form;
    private static Application instance;
    private boolean started = false;

    private List todos = new List();

//    public void startApp() {
//
//        VKBImplementationFactory.init();
//        Display.init(this);
//
//        // distinguish between start and resume from pause
//        if (!started) {
//            started = true;
//            Log.p("Application started");
//
//            // show your LWUIT form here e.g.: new MyForm().show();
//            // this is a good place to set your default theme using
//            // the UIManager class e.g.:
//
//
//            form = new Form("Dashboard");
//            form.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
//
//
//            form.addComponent(new Label("Test"));
//
//            form.addCommand(new Command("AAAAA"));
//
//
//            form.addCommand(settingsCommand);
//            form.addCommand(new Command("BBBBB"));
//            form.addCommand(new Command("CCCCC"));
//            form.addCommand(new Command("DDDDD"));
//            form.addCommand(new Command("EEEEE"));
//
//            form.show();
//
//        }
//
//
//    }

    /**
     * MIDlet call-back.
     * @param midlet
     */
    public static void pause(final MIDlet midlet) {
        //saveInstance(); // not required at all just to make sure
    }

    /**
     * MIDlet call-back.
     * @param midlet
     */
    public static void destroy(final MIDlet midlet) {
        //pause(midlet);
        Application.midlet = null;
    }

    /**
     * Exit application.
     * @param error remember an error occurred for the next start
     */
    public static void exit(final boolean error) {
//        if (error) {
//            getInstance().put(EXIT_WITH_ERROR, Boolean.TRUE);
//            saveInstance();
//        }
        if (midlet != null) {
            midlet.notifyDestroyed();
        }
    }
    private static MIDlet midlet;

    /**
     * @return J2ME application MIDlet
     */
    public static MIDlet getMidlet() {
        return midlet;
    }

    /**
     * MIDlet call-back.
     * @param midlet
     */
    public static void start(final MIDlet midlet) {
        //getInstance().doConfigure(midlet);
        Application.midlet = midlet;
        //midletVersion = midlet.getAppProperty("MIDlet-Version");
    }

    /**
     * Get application singleton instance.
     * @return application
     */
    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
            //instance.loadApplication();
        }
        return instance;
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
