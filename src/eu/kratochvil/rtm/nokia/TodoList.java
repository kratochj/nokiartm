package eu.kratochvil.rtm.nokia;

import com.sun.lwuit.Form;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.util.Log;
import eu.kratochvil.rtm.nokia.util.FormUtils;
import javax.microedition.lcdui.Canvas;

/**
 *
 * @author jirikratochvil
 */
public class TodoList extends Form {

    TodoList[] todos;
    int index;

    public TodoList(TodoList[] todos, int index, String label) {
        super(label);
        this.todos = todos;
        this.index = index;
    }

    public void setTransition(boolean forward) {
        setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, forward, 400));
    }

    public void show() {
        super.show();
        MainForm.generateMenu(this);
        MainForm.activeForm = this;
    }



    public void keyReleased(int keyCode) {
        super.keyReleased(keyCode);
//        Log.p("Key pressed: #" + keyCode, Log.DEBUG);

        switch (keyCode) {
            case Canvas.KEY_NUM4:
            case -3: // Left
                Log.p("Showing previous TodoList", Log.DEBUG);
                FormUtils.showPrevTodoList(todos, index);
                break;
            case -4: // Right
            case Canvas.KEY_NUM6:
                Log.p("Showing next TodoList", Log.DEBUG);
                FormUtils.showNextTodoList(todos, index);
                break;
        }


    }
}
