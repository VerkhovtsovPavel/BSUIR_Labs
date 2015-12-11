package by.bsuir.verkpavel.courseproject.ui.change;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.Describable;
import by.bsuir.verkpavel.courseproject.dao.entity.Employee;
import by.bsuir.verkpavel.courseproject.dao.entity.Salary;
import by.bsuir.verkpavel.courseproject.resources.Messages;

public class ChangeSalaryView  extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private JPanel mainPanel;
    private JComboBox<String> employeeComboBox;
    private List<Employee> employees;
    private Salary currentSalary;
    private JSpinner baseRateSpinner;
    private JSpinner raisingFactorSpinner;
    private JLabel baseRateLabel;
    private JLabel raseFactorLabel;

    public void showView() {
        this.setSize(450, 290);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true);
    }

    public ChangeSalaryView() {
        setResizable(false);
        configureDefaultLayot();
        fillComboBoxes();
    }

    private void configureDefaultLayot() {
        setTitle("Изменение зарплаты");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);
        
        JLabel employeeLabel = new JLabel("Работник");
        employeeLabel.setBounds(24, 21, 61, 14);
        mainPanel.add(employeeLabel);
        
        employeeComboBox = new JComboBox<String>();
        employeeComboBox.setBounds(95, 16, 308, 25);
        employeeComboBox.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = employeeComboBox.getSelectedIndex();
                currentSalary = employees.get(index).getSalary();
                
                baseRateSpinner.setValue(currentSalary.getBaseRate());
                raisingFactorSpinner.setValue(currentSalary.getRaisingFactor());
            }
        });
        mainPanel.add(employeeComboBox);
        
        JButton submitBtn = new JButton("Сохранить");
        submitBtn.setBounds(155, 213, 93, 30);
        submitBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                double baseRate = (double) baseRateSpinner.getValue();
                double raisingFactor = (double) raisingFactorSpinner.getValue();
                currentSalary.setBaseRate(baseRate);
                currentSalary.setRaisingFactor(raisingFactor);
                DeliveryServiceDao.getInstance().updateRecord(currentSalary);
                JOptionPane.showMessageDialog(null, Messages.SALARY_CHANGED.get(), "Message",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        mainPanel.add(submitBtn);
        
        baseRateSpinner = new JSpinner();
        baseRateSpinner.setModel(new SpinnerNumberModel(1000000.0, 100000.0, 5000000.0, 100000.0));
        baseRateSpinner.setBounds(68, 86, 335, 40);
        mainPanel.add(baseRateSpinner);

        raisingFactorSpinner = new JSpinner();
        raisingFactorSpinner.setModel(new SpinnerNumberModel(1.0, 0.0, 3.0, 0.0));
        raisingFactorSpinner.setBounds(248, 137, 155, 40);
        mainPanel.add(raisingFactorSpinner);
        
        baseRateLabel = new JLabel("Оклад");
        baseRateLabel.setBounds(10, 105, 48, 14);
        mainPanel.add(baseRateLabel);
        
        raseFactorLabel = new JLabel("Повышающий коэффицент");
        raseFactorLabel.setBounds(10, 161, 178, 14);
        mainPanel.add(raseFactorLabel);
    }
    
    private void fillComboBox(JComboBox<String> target, List<? extends Describable> source) {
        for (Describable item : source) {
            target.addItem(item.getDescription());
        }
    }

    private void fillComboBoxes() {
        employees = DeliveryServiceDao.getInstance().getActiveRecords(Employee.class);
        fillComboBox(employeeComboBox, employees);
    }

}
