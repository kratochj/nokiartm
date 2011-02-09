package eu.kratochvil.rtm.nokia;

import javax.microedition.midlet.MIDlet;

/**
 * PeersOne MIDlet entry point.
 * 
 * @author jiri@kratochvil.eu
 *
 */
public class NokiaRtmMIDlet extends MIDlet {

    /**
     * The one and only instance of this class.
     */
    //private static PeersOneApp instance;
    /**
     * This is called before <code>startApp</code>.
     */
    public NokiaRtmMIDlet() {
        //instance = this;
    }

    /**
     * @see javax.microedition.midlet.MIDlet#startApp()
     */
    protected void startApp() {
        Application.start(this);
        MainForm.buildUI(this);
    }

    /**
     * @see javax.microedition.midlet.MIDlet#pauseApp()
     */
    protected void pauseApp() {
        Application.pause(this);
    }

    /**
     * @see javax.microedition.midlet.MIDlet#destroyApp(boolean)
     */
    protected void destroyApp(final boolean unconditional) {
        Application.destroy(this);
        notifyDestroyed();
    }
    /**
     * Display splash screen.
     */
//    void showSplashScreen() {
//        final InputStream splash = getClass().getResourceAsStream("/splash.png");
//        if ( splash != null ) {
//            final Display display = Display.getDisplay(this);
//            SplashScreen splashScreen = new SplashScreen(display);
//            //splashScreen.setTitle("Welcome to PeersOne");
//            //splashScreen.setText(" (c) PeersONE 2009 ");
//            //splashScreen.setCommandListener(this);
//            //splashScreen.setAllowTimeoutInterrupt(true);
//            //splashScreen.setFullScreenMode(true);
//            try {
//                splashScreen.setImage(Image.createImage(splash));
//                display.setCurrent(splashScreen);
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    /*
    public static void exit() {
    try {
    instance.destroyApp(true);
    }
    catch (MIDletStateChangeException e) {
    // ignore.
    }
    }
     */
}
