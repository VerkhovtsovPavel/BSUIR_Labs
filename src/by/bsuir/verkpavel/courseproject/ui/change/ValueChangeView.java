package by.bsuir.verkpavel.courseproject.ui.change;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ValueChangeView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private JPanel mainPanel;
    private JTextField oldValueTextField;
    private JTextField newValueTextField;
    public boolean isFinish;


    public void showView() {
        this.setSize(445, 200);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                isFinish = true;
            }
        });
    }

    public ValueChangeView(Object oldValue) {
        setResizable(false);
        configureDefaultLayot();
        this.oldValueTextField.setText(oldValue.toString());
        this.oldValueTextField.setEnabled(false);
    }

    private void configureDefaultLayot() {
        setTitle("Изменения значения");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);
        
        JLabel oldValueLabel = new JLabel("Старое значение");
        oldValueLabel.setBounds(24, 21, 114, 14);
        mainPanel.add(oldValueLabel);
        
        oldValueTextField = new JTextField();
        oldValueTextField.setBounds(172, 15, 255, 25);
        mainPanel.add(oldValueTextField);
        oldValueTextField.setColumns(10);
        
        JLabel newValueLabel = new JLabel("Новое значение");
        newValueLabel.setBounds(24, 70, 114, 14);
        mainPanel.add(newValueLabel);
        
        newValueTextField = new JTextField();
        newValueTextField.setColumns(10);
        newValueTextField.setBounds(172, 64, 255, 25);
        mainPanel.add(newValueTextField);
        
        JButton submitBtn = new JButton("Изменить");
        submitBtn.setBounds(158, 113, 93, 30);
        submitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isFinish = true;
                dispose();
            }
        });
        mainPanel.add(submitBtn);
    }
    
    public String getNewValue(){
        return newValueTextField.getText();
    }
}
