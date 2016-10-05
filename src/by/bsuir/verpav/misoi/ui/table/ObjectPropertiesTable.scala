package by.bsuir.verpav.misoi.ui.table

import java.awt.{BorderLayout, Color, Component, Dimension}
import javax.swing.{JFrame, JLabel, JScrollPane, JTable}
import javax.swing.table.{AbstractTableModel, TableCellRenderer}

class ObjectPropertiesTable(data: Array[Array[Any]]) extends JFrame("Objects properties") {

  setLayout(new BorderLayout())
  val table = new JTable(TableModel(data))
  table.setPreferredScrollableViewportSize(new Dimension(500, 70))
  table.setFillsViewportHeight(true)
  table.setDefaultRenderer(classOf[Color], ColorRenderer)
  add(new JScrollPane(table))

  setPreferredSize(new Dimension(500, 70))
  pack()
  setLocationRelativeTo(null)
  setVisible(true)


  object TableModel extends AbstractTableModel {
    var data: Array[Array[Any]] = _

    val columnNames = Array[String]("Color", "Square", "Perimeter", "Density", "Central moment", "Elongation")

    def getColumnCount = { columnNames.length }

    def getRowCount = { data.length }

    override def getColumnName(col: Int) = { columnNames(col) }

    def getValueAt(row: Int, col: Int) = { data(row)(col).asInstanceOf[AnyRef] }

    override def getColumnClass(c: Int) = { getValueAt(0, c).getClass }

    override def isCellEditable(row: Int, col: Int) = { false }

    override def setValueAt(value: AnyRef, row: Int, col: Int) = {}

    private def setData(data: Array[Array[Any]]) = {this.data = data; this}

    def apply(data: Array[Array[Any]]) = TableModel.setData(data)
  }

  object ColorRenderer extends JLabel with TableCellRenderer {
    setOpaque(true)

    def getTableCellRendererComponent(table: JTable, color: Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component = {
      val newColor: Color = color.asInstanceOf[Color]
      setBackground(newColor)
      this
    }
  }


}
