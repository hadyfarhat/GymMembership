import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FormRadioButtonsPanel extends FormFieldPanel {
    private ButtonGroup group;
    private List<JRadioButton> buttons;

    public FormRadioButtonsPanel(String name, Dimension dimension, String button1Name, String buttont2Name) {
        super(name, dimension);

        group = new ButtonGroup();
        buttons = new ArrayList<>();

        buttons.add(new JRadioButton(button1Name));
        buttons.add(new JRadioButton(buttont2Name));

        for (int i = 0; i < buttons.size(); ++i) {
            group.add(buttons.get(i));
            this.add(buttons.get(i));
        }
    }
}
