package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ActionHandler implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    invokeMethod(e.getActionCommand());
  }

  public abstract void invokeMethod(String actionCommand);
}
