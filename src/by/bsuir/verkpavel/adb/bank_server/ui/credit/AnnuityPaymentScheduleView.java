package by.bsuir.verkpavel.adb.bank_server.ui.credit;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import by.bsuir.verkpavel.adb.bank_server.data.entity.AnnuityPayment;
import by.bsuir.verkpavel.adb.bank_server.data.entity.Credit;
import by.bsuir.verkpavel.adb.bank_server.logic.credit.AnnuityCalculator;
import by.bsuir.verkpavel.adb.bank_server.logic.credit.SimpleAnnuityCalculator;

public class AnnuityPaymentScheduleView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private static AnnuityCalculator annuityCalculator;

    private AnnuityPaymentScheduleView(Credit credit) {
        super("График аннуитетных платежей");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        annuityCalculator = new SimpleAnnuityCalculator(credit);
        ArrayList<AnnuityPayment> beans =  annuityCalculator.paymentsSheduling();
        credit.annuityPayment = beans;

        TableModel model = new PaymentsTableModel(beans);
        JTable table = new JTable(model);

        getContentPane().add(new JScrollPane(table));

        setPreferredSize(new Dimension(260, 220));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void create(Credit credit) {
        new AnnuityPaymentScheduleView(credit);
    }

    public class PaymentsTableModel implements TableModel {

        private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

        private List<AnnuityPayment> payments;

        public PaymentsTableModel(List<AnnuityPayment> beans) {
            this.payments = beans;
        }

        public void addTableModelListener(TableModelListener listener) {
            listeners.add(listener);
        }

        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        public int getColumnCount() {
            return 4;
        }

        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
            case 0:
                return "Дата оплаты";
            case 1:
                return "Основной долг";
            case 2:
                return "Проценты";
            case 3:
                return "Сумма платеща";
            }
            return "";
        }

        public int getRowCount() {
            return payments.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            AnnuityPayment bean = payments.get(rowIndex);
            switch (columnIndex) {
            case 0:
                return bean.payDate.toString();
            case 1:
                return bean.principalAmount;
            case 2:
                return bean.percents;
            case 3:
                return bean.principalAmount + bean.percents;
            }
            return "";
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public void removeTableModelListener(TableModelListener listener) {
            listeners.remove(listener);
        }

        public void setValueAt(Object value, int rowIndex, int columnIndex) {

        }
    }
}
