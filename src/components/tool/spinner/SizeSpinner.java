package components.tool.spinner;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

public abstract class SizeSpinner extends JSpinner {

  public SizeSpinner(SpinnerModel model) {
    super(model);

    if (this.getEditor() instanceof JSpinner.DefaultEditor) {
      JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) this.getEditor();
      editor.getTextField().setEnabled(true);
      editor.getTextField().setEditable(false);
    }
  }

  public void setMaximumSize() {
    super.setMaximumSize(getPreferredSize());
  }
}
