package by.bsuir.verpav.misoi.histogram

import java.awt._
import java.awt.event.KeyEvent
import java.awt.geom.Rectangle2D
import javax.swing._

/**
  * Created by Pavel_Verkhovtsov on 9/14/16.
  */
object SimpleHistogram {

  def build(data: Map[Int, Int], prefix : String): Unit = {

    val frame = new JFrame(prefix + " Histogram")
    frame.setLayout(new BorderLayout())

    val color = prefix match {
      case "Red" => Some(Color.RED)
      case "Blue" => Some(Color.BLUE)
      case "Green" => Some(Color.GREEN)
      case _ => None
    }

        frame.add(new JScrollPane(new Graph(data, color)))
        frame.setJMenuBar(createMenuBar())
        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.setVisible(true)

        def createMenuBar() = {
          val menuBar = new JMenuBar()
          val menu = new JMenu("File")
          menuBar.add(menu)
          val menuItem1 = new JMenuItem(" Save...   ")
          menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
            Toolkit.getDefaultToolkit.getMenuShortcutKeyMask))
          menu.add(menuItem1)
          menuBar
        }
    }

  def |-|-| (data: Map[Int, Int], prefix : String = ""): Unit = {
    build(data, prefix)
  }
}

class Graph(mapHistory: Map[Int, Int], color : Option[Color]) extends JPanel {
  val MIN_BAR_WIDTH = 4
  val graphWidth = (mapHistory.size * MIN_BAR_WIDTH) + 11
  val graphMinSize = new Dimension(graphWidth, 128)
  val graphPrefSize = new Dimension(graphWidth, 256)
  val xOffset = 5
  val yOffset = 5
  setMinimumSize(graphMinSize)
  setPreferredSize(graphPrefSize)

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    if (mapHistory != null) {
      val width = getWidth - 1 - (xOffset * 2)
      val height = getHeight - 1 - (yOffset * 2)
      val g2d = g.create().asInstanceOf[Graphics2D]
      g2d.setColor(Color.DARK_GRAY)
      g2d.drawRect(xOffset, yOffset, width, height)
      val barWidth = Math.max(MIN_BAR_WIDTH, Math.floor(width / mapHistory.size.toFloat).toInt)
      println("width = " + width + "; size = " + mapHistory.size + "; barWidth = " + barWidth)

      val maxValue = mapHistory.values.max

      var xPos = xOffset
      for (key <- mapHistory.keys.toArray.sorted) {
        val value = mapHistory.get(key).get
        val barHeight = Math.round((value / maxValue.toFloat) * height)
        val yPos = height + yOffset - barHeight
        val bar = new Rectangle2D.Float(xPos, yPos, barWidth, barHeight)
        if(color.isEmpty)
          g2d.setColor(new Color(key, key, key))
        else
          g2d.setColor(color.get)
        g2d.fill(bar)
        g2d.setColor(Color.WHITE)
        g2d.draw(bar)
        xPos += barWidth
      }
      g2d.dispose()
    }
  }
}