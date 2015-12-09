package by.bsuir.verkpavel.courseproject.ui.change;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.Describable;
import by.bsuir.verkpavel.courseproject.dao.entity.Employee;
import by.bsuir.verkpavel.courseproject.dao.entity.Parcel;
import by.bsuir.verkpavel.courseproject.dao.entity.Salary;

public class ChangeSalaryView  extends JFrame {
    //TODO Change UI
    private static final long serialVersionUID = 2883993883146596569L;
    private JPanel mainPanel;
    private JComboBox<String> employeeComboBox;
    private List<Employee> employees;
    private Salary currentSalary;

    public void showView() {
        this.setSize(445, 200);
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
        
        JLabel parcelLabel = new JLabel("Посылка");
        parcelLabel.setBounds(24, 21, 114, 14);
        mainPanel.add(parcelLabel);
        
        employeeComboBox = new JComboBox<String>();
        employeeComboBox.setBounds(172, 15, 255, 25);
        mainPanel.add(employeeComboBox);
        
        JLabel deliveryLabel = new JLabel("Доставка");
        deliveryLabel.setBounds(24, 70, 114, 14);
        mainPanel.add(deliveryLabel);
        
        JButton submitBtn = new JButton("Сохранить");
        submitBtn.setBounds(158, 113, 93, 30);
        submitBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                double baseRate = 0;
                double raisingFactor = 0;
                currentSalary.setBaseRate(baseRate);
                currentSalary.setRaisingFactor(raisingFactor);
                DeliveryServiceDao.getInstance().updateRecord(currentSalary);
            }
        });
        mainPanel.add(submitBtn);
    }
    
    private void fillComboBox(JComboBox<String> target, List<? extends Describable> source) {
        for (Describable item : source) {
            target.addItem(item.getDescription());
        }
    }

    private void fillComboBoxes() {
        employees = DeliveryServiceDao.getInstance().getAllRecord(Parcel.class);
        fillComboBox(employeeComboBox, employees);
    }

}
