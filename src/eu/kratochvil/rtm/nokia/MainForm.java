package eu.kratochvil.rtm.nokia;

import com.sun.lwuit.Button;
import com.sun.lwuit.Command;
import com.sun.lwuit.Component;
import com.sun.lwuit.Dialog;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.ToolkitHelper;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.events.FocusListener;

import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Log;
import eu.kratochvil.rtm.nokia.util.Asserts;
import eu.kratochvil.rtm.nokia.util.LangUtils;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author jirikratochvil
 */
public class MainForm extends Form implements FocusListener {

    static MainForm instance;
    /**
     * OK command without any action or command id (usable in dialogs).
     */
    static final Command okCommand = new Command("ok"); // it's used frequently (thus cached)
    /**
     * Exit command.
     */
    private static final Command exitCommand = new Command("exit") {

        public void actionPerformed(ActionEvent evt) {
            Application.exit(false);
        }
    };
    private static Command aboutCommand = new Command("About") {

        public void actionPerformed(ActionEvent evt) {
            final int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            final Form aboutForm = new Form(" Remmember The Milk for Nokia " + Application.getVersion() + ' ');
            aboutForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            aboutForm.addComponent(new Label(" "));
            TextArea aboutText = new TextArea("about_application", 5, 20);
            aboutText.setEditable(false);
            aboutText.setGrowByContent(true);
            aboutForm.addComponent(aboutText);
            aboutForm.addComponent(new Label(" "));
            Label label = new Label(" (c) Jiri Kratochvil " + year + ' ');
            label.setAlignment(Label.CENTER);
            aboutForm.addComponent(label);
            //aboutForm.addComponent(new Label(" "));
            label = new Label("all_rights_reserved");
            label.setAlignment(Label.CENTER);
            aboutForm.addComponent(label);
            aboutForm.addComponent(new Label(" "));
            aboutForm.addCommand(backToMainFormCommand);
            aboutForm.show();
        }
    };
    /**
     * A BACK command that returns to the {@link MainForm#instance}.
     */
    static final Command backToMainFormCommand = new Command("Back") {

        public void actionPerformed(ActionEvent event) {
            showMainForm(false);
        }
    };

    static Command settingsCommand = new Command("Settings") {

        public void actionPerformed(ActionEvent ev) {
            Log.p("Opening settings dialog", Log.DEBUG);
            Settings settings = new Settings();
            settings.show();
        }
    };

    private MainForm() {
        this("Form");
    }

    private MainForm(final String title) {
        super(title);
        //setCommandListener(this);
        addFocusListener(this);
        //softButton = null;
        // SOFT-MENU: kept just in-case we switch back !
        //softButton = getSoftButton(0);
        enableSoftButtonCustomAction(getSoftButton(0));
        //
        if (Display.getInstance().isThirdSoftButton()) {
            softButton = getSoftButton(1);
        } else {
            softButton = getSoftButton(0);
        }
    }
    private final Button softButton;
    private boolean softButtonAction = false;
    private int softButtonCommandId = 0;

    private void enableSoftButtonCustomAction(final Button softButton) {
        ActionListener menuBar = (ActionListener) getMenuBar();
        Asserts.assertNotNull(menuBar, "MenuBar not FOUND");
        softButton.removeActionListener(menuBar);
        //softButton.addActionListener(this);
        softButton.addActionListener(menuBar);
    }

    public void focusGained(Component arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void focusLost(Component arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /*
     * cache it; it's used on every menu show !
     */
    private transient Component menuBar = null;

    private Component getMenuBar() {
        if (menuBar == null) {
            menuBar = ToolkitHelper.getMenuBar(this);
        }
        return this.menuBar;
    }

    /**
     * Set the UI transitions.
     */
    private static void setDefaultTransitions() {
        int type = CommonTransitions.SLIDE_HORIZONTAL;
        instance.setTransitionInAnimator(CommonTransitions.createSlide(type, false, 550));
        instance.setTransitionOutAnimator(CommonTransitions.createSlide(type, true, 550));
        type = CommonTransitions.SLIDE_VERTICAL;
    }

    /**
     * MIDLet should call this to initialize the UI.
     *
     * @param midlet
     * @return main form (already shown on screen)
     */
    public static MainForm buildUI(final MIDlet midlet) {
        try {
            //SVGImplementation.init(); // will attemp to use SVG - with a fallback to canvas if not supported
            //EditStringCanvasImpl.init();

            Display.init(midlet);

            try {
                com.sun.lwuit.util.Resources res = com.sun.lwuit.util.Resources.open("/theme.res");
                com.sun.lwuit.plaf.UIManager.getInstance().setThemeProps(res.getTheme(res.getThemeResourceNames()[0]));
            } catch (java.io.IOException err) {
                err.printStackTrace();
            }

            //ResourcesHelper.initResources(midlet);
            initAndShowMainForm();
            setDefaultTransitions(); // after the LaF is set !
//            if ( Application.lastExitWithError() ) {
//                //final Thread sendThread = new Thread();
//                //sendThread.setPriority(Thread.MIN_PRIORITY);
//                //sendThread.start();
//                showSendApplicationLog();
//            }
//            checkLifeSyncStatus();
        } catch (Throwable e) {
            handleAppError(e, null);
        }
        return MainForm.instance;
    }

    static void initAndShowMainForm() throws Exception {

        final MainForm mainForm = new MainForm();
        final int width = Display.getInstance().getDisplayWidth();

        mainForm.addCommand(exitCommand);
        mainForm.addCommand(aboutCommand);
        mainForm.addCommand(settingsCommand);

        MainForm.instance = mainForm;
        mainForm.show();


    }

    /**
     * Application exception handler.
     *
     * @param e
     *            the unexpected error
     * @param title
     *            optional title to display
     */
    static void handleAppError(final Throwable e, String title) { // TODO NestedRE
        e.printStackTrace(); // for development
        // it might not yet been logged, thus make sure it's in the log :
        Log.p("handleAppError()", Log.ERROR);
        //
        String messg = e.getMessage();
        if (messg == null || messg.length() == 0) {
            messg = e.toString();
        }
        if (title == null) {
            title = LangUtils.getSimpleName(e.getClass());
        }
        // do some 'common' heuristics :
//        if ( e instanceof HttpException ) {
//            //final HttpException he = (HttpException) e;
//            /*
//            if ( he.hasHttpStatus() ) {
//                messg = l("server_unexpected_http_status") + '[' + he.getHttpStatus() + ']';
//            }
//            */
//        }
//        else
//        if ( e instanceof NestedRuntimeException) {
//            final NestedRuntimeException ne = (NestedRuntimeException) e;
//            if ( ne.hasCause() ) messg = ne.getCause().toString();
//        }
//        //
        final Command[] cmds = {exitCommand, okCommand};
        if (Dialog.show(title, messg, okCommand, cmds, Dialog.TYPE_ERROR, null, 0) == okCommand) {
            showMainForm(false);
        } else {
            if (showSendApplicationLog()) {
                Application.exit(true);
            } else {
                Application.exit(false);
            }
        }
    }

    /**
     * Shows the main form.
     * @param changed
     */
    static void showMainForm(final boolean changed) {
        final Display display = Display.getInstance();
        if (display.isEdt()) { // AWT-Like Event-Dispatch Thread
            MainForm.instance.show();
            if (changed) {
                MainForm.instance.currentStateChanged();
            }
        } else {
            Runnable instanceShowCall = new Runnable() {

                public void run() {
                    MainForm.instance.show();
                    if (changed) {
                        MainForm.instance.currentStateChanged();
                    }
                }
            };
            display.callSerially(instanceShowCall);
        }
    }

    private static boolean showSendApplicationLog() {
//        final Command sendCommand = new LocalizedCommand("send");
        final Command sendCommand = new Command("send");
        final Command[] cmds = {new Command("ignore"), sendCommand};
//        if (Dialog.show("send_error_log", t("send_error_log_text"), sendCommand, cmds, Dialog.TYPE_ERROR, null, 0) == sendCommand) {
        if (Dialog.show("send_error_log", "send_error_log_text", sendCommand, cmds, Dialog.TYPE_ERROR, null, 0) == sendCommand) {
            return true;//Application.sendApplicationLog();
        }
        return false;
    }

    /**
     * Helper callback to repaint the form and update the menu commands for the
     * currently focused button, this is useful in several scenarios e.g. life date changed.
     */
    void currentStateChanged() {
        //final ImageActionButton  focused = (ImageActionButton) getFocused();
        //updateMenuCommandForButton(focused, false);
        //refreshTheme();
        repaint(); // repaint this form
    }

    /**
     * @see com.sun.lwuit.Form#showMenuDialog(com.sun.lwuit.Dialog)
     */
    protected Command showMenuDialog(final Dialog menu) {
        final int h = Display.getInstance().getDisplayHeight() -
                getMenuBar().getPreferredH() - getTitleComponent().getPreferredH();
        final int w = Display.getInstance().getDisplayWidth();
        final int top = h / 100 * 15;
        final int bottom = h / 100 * 10;
        final int side = w / 100 * 20;
        return menu.show(top, bottom, side, side, false);
    }
}

