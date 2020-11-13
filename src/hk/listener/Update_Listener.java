package hk.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Update_Listener implements ActionListener {
    JButton button;

    public void setButton(JButton button) {
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.doClick();
    }
}
