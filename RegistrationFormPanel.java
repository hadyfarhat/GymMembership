import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * An abstract class which all registration form panels extend from.
 * It provides basic functionality that every registration form panel will use.
 * An example of a "registration form panel" is {@link PersonalRegistrationFormPanel}.
 */
public abstract class RegistrationFormPanel extends JPanel {

    /**
     * Sets the size (width and height) of this Panel
     * Sets this panel's layout to BoxLayout and it also sets its orientation to vertical or "PAGE_AXIS"
     */
    public RegistrationFormPanel() {
        setSize(MembershipForm.WIDTH, MembershipForm.HEIGHT);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    /**
     * Adds the provded panel to this panel with a rigid area underneath it.
     * It accepts a height parameter to be used for setting the height of the rigid area.
     * However, the width of the rigid area is taken from {@link MembershipForm}
     *
     * @param panel the panel to be added to this panel
     * @param rigidAreaHeight the height of the rigid area that will be added underneath the provided panel
     */
    public void addPanelWithRigidArea(JPanel panel, int rigidAreaHeight) {
        add(panel);
        add(Box.createRigidArea(new Dimension(MembershipForm.WIDTH, rigidAreaHeight)));
    }

    /**
     * Adds the provded panel to this panel with a rigid area underneath it.
     * Both the height and width of the rigid area are taken from {@link MembershipForm}
     *
     * @param panel the panel to be added to this panel
     */
    public void addPanelWithRigidArea(JPanel panel) {
        add(panel);
        add(Box.createRigidArea(new Dimension(MembershipForm.WIDTH, MembershipForm.FIELD_RIGID_AREA_HEIGHT)));
    }

    /**
     * It loops over the provided panels parameter and adds them one by one to this panel
     * with a corresponding rigid area underneath them.
     * However, it doesn't add a rigid area for the last panel in the list.
     * If there's only one panel in the list, then it will be added without a rigid area.
     *
     * @param panels the panels to be added to this panel
     */
    public void addPanels(List<JPanel> panels) {
        if (panels.size() > 0) {
            for (int i = 0; i < panels.size() - 1; ++i) {
                addPanelWithRigidArea(panels.get(i));
            }
            add(panels.get(panels.size() - 1));
        }
    }
}
