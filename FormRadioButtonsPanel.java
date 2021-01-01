import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class FormRadioButtonsPanel extends FormFieldPanel {

    public FormRadioButtonsPanel(String name, Dimension dimension, String button1Name, String buttont2Name) {
        super(name, dimension);
        addButtonsAndAssignToGroup(button1Name, buttont2Name);
    }

    public FormRadioButtonsPanel(String name, String button1Name, String buttont2Name) {
        super(name);
        addButtonsAndAssignToGroup(button1Name, buttont2Name);
    }

    private void addButtonsAndAssignToGroup(String button1Name, String button2Name) {
        ButtonGroup group = new ButtonGroup();
        List<JRadioButton> buttons = new ArrayList<>();

        buttons.add(createRadioButton(button1Name));
        buttons.add(createRadioButton(button2Name));

        for (int i = 0; i < buttons.size(); ++i) {
            group.add(buttons.get(i));
            this.add(buttons.get(i));
            RegistrationForm.components.put(buttons.get(i).getName(), buttons.get(i));
        }
    }

    private JRadioButton createRadioButton(String name) {
        JRadioButton button = new JRadioButton(name);
        button.setName(name);
        return button;
    }
}
