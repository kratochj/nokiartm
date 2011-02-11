package eu.kratochvil.rtm.nokia.util;

import eu.kratochvil.rtm.nokia.TodoList;

/**
 *
 * @author jirikratochvil
 */
public class FormUtils {
  public static void showNextTodoList(TodoList[] todos, int index){
        int i = index;
        i++;
        if (i > todos.length - 1) {
            i = 0;
        }
        todos[i].setTransition(false);
        todos[i].show();
  }
  public static void showPrevTodoList(TodoList[] todos, int index){
        int i = index;
        i--;
        if (i < 0) {
            i = todos.length - 1;
        }
        todos[i].setTransition(true);
        todos[i].show();
  }
}
