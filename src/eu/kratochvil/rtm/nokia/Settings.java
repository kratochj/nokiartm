package eu.kratochvil.rtm.nokia;

import com.sun.lwuit.Button;
import com.sun.lwuit.CheckBox;
import com.sun.lwuit.Command;
import com.sun.lwuit.Component;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.util.Log;

/**
 *
 * @author jirikratochvil
 */
public class Settings {


    Form form = null;

    TextArea username = new TextArea(1, 20, TextArea.EMAILADDR);
    TextArea password = new TextArea(1, 20, TextArea.PASSWORD | TextArea.NON_PREDICTIVE);
    CheckBox monitorGpsLocation = new CheckBox("Monitor GPS location");

    private Command saveCommand = new Command("Save") {
        public void actionPerformed(ActionEvent ev) {
                Log.p("Saving settings");
        }
    };

    private Command backCommand = new Command("Back") {
        public void actionPerformed(ActionEvent ev) {
                Log.p("Back");
        }
    };

    private Command showLog = new Command("Show log") {
       public void actionPerformed(ActionEvent ev) {
           Log.p("Displaying log", Log.DEBUG);
           Log.showLog();
       }
    };

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

        form = new Form("Settings");
        form.setLayout(new BoxLayout(BoxLayout.Y_AXIS));


        form.addComponent(new Label("User name"));
        form.addComponent(username);
        form.addComponent(new Label("Password"));
        form.addComponent(password);
        form.addComponent(monitorGpsLocation);
        form.addComponent(new Button(showLog));

        form.addCommand(saveCommand);
        form.addCommand(backCommand);

        form.show();
    }

}
