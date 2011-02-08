package eu.kratochvil.rtm.nokia;

import com.sun.lwuit.Button;
import com.sun.lwuit.CheckBox;
import com.sun.lwuit.Command;
import com.sun.lwuit.Dialog;
import com.sun.lwuit.Display;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.plaf.Border;
import com.sun.lwuit.util.Log;
import javax.microedition.rms.RecordStore;

import eu.kratochvil.rtm.nokia.components.InfiniteProgressIndicator;
import java.io.IOException;

/**
 *
 * @author jirikratochvil
 */
public class Settings {

    RecordStore rsPrefs = null;

    public static final String RS_PREFS = "nokiartm.preferences";

    public static final String PREFS_USERNAME = "preferences.username";
    public static final String PREFS_PASSWORD = "preferences.password";
    public static final String PREFS_LOCATION = "preferences.location";

    Configuration config = new Configuration(RS_PREFS);

    Dialog form = null;

    TextArea username = new TextArea(1, 20, TextArea.EMAILADDR);
    TextArea password = new TextArea(1, 20, TextArea.PASSWORD | TextArea.NON_PREDICTIVE);
    CheckBox monitorGpsLocation = new CheckBox("Monitor GPS location");

    private Command saveCommand = new Command("Save") {
        public void actionPerformed(ActionEvent ev) {
                try {
                    final Dialog progress = new Dialog();
                    progress.getDialogStyle().setBorder(Border.createRoundBorder(6, 6, 0xe3ef5a));
                    progress.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_VERTICAL, true, 400));
                    progress.addComponent(new Label("Please Wait"));
                    progress.addComponent(new InfiniteProgressIndicator(Image.createImage("/wait-circle.png")));
                    int height = Display.getInstance().getDisplayHeight() - (progress.getContentPane().getPreferredH() + progress.getTitleComponent().getPreferredH());
                    height /= 2;
                    progress.show(height, height, 20, 20, true, false);

                    new Thread() {
                        public void run() {
                            Log.p("Saving settings");
                            config.deleteRecStore();
                            config.openRecStore();
                            config.writeRecord(PREFS_USERNAME, username.getText());
                            config.writeRecord(PREFS_PASSWORD, password.getText());
                            config.writeRecord(PREFS_LOCATION, monitorGpsLocation.isSelected());
                            config.closeRecStore();

                            progress.dispose();
                            final Dialog saved = new Dialog();
                            saved.getDialogStyle().setBorder(Border.createRoundBorder(6, 6, 0xe3ef5a));
                            saved.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_VERTICAL, false, 400));
                            saved.addComponent(new Label("Configuration saved"));
                            saved.addCommand(new Command("OK") {
                                public void actionPerformed(ActionEvent ev) {
                                    saved.dispose();
                                    form.dispose();
                                }
                            });
                            int height = Display.getInstance().getDisplayHeight() - (progress.getContentPane().getPreferredH() + progress.getTitleComponent().getPreferredH());
                            height /= 2;
                            saved.show(height, height, 20, 20, true, false);

                        }
                    }.start();



                } catch (IOException e) {
                    Log.p("IOException" + e.getMessage(), Log.ERROR);
               }

        }
    };

    private Command backCommand = new Command("Back") {
        public void actionPerformed(ActionEvent ev) {
                Log.p("Back");
                config.openRecStore();
                config.readRecords();
                config.closeRecStore();
        }
    };

    private Command showLog = new Command("Show log") {
       public void actionPerformed(ActionEvent ev) {
           Log.p("Displaying log", Log.DEBUG);
           Log.showLog();
       }
    };


    private void readConfiguration() {
        config.openRecStore();
        username.setText(config.readRecordAsString(PREFS_USERNAME, "some@email.com"));
        password.setText(config.readRecordAsString(PREFS_PASSWORD, ""));
        monitorGpsLocation.setSelected(config.readRecordAsBoolean(PREFS_LOCATION, false));
        config.closeRecStore();
        Log.p("Configuration readed", Log.INFO);
    }

    private void setTheme(String name) {
        try {
            com.sun.lwuit.util.Resources res = com.sun.lwuit.util.Resources.open(name);
            com.sun.lwuit.plaf.UIManager.getInstance().setThemeProps(res.getTheme(res.getThemeResourceNames()[0]));
            Display.getInstance().getCurrent().refreshTheme();
        } catch(java.io.IOException err) {
            Log.p(err.getMessage(), Log.ERROR);
             err.printStackTrace();
        }
    }



    public void show() {
        Log.p("Showing setting pane");

        form = new Dialog("Settings");
        form.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, 400));
        form.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 400));
        form.setLayout(new BoxLayout(BoxLayout.Y_AXIS));


        form.addComponent(new Label("User name"));
        form.addComponent(username);
        form.addComponent(new Label("Password"));
        form.addComponent(password);
        form.addComponent(monitorGpsLocation);
        form.addComponent(new Button(showLog));

        readConfiguration();

        form.addCommand(saveCommand);
        form.addCommand(backCommand);

        form.show();
    }

}
