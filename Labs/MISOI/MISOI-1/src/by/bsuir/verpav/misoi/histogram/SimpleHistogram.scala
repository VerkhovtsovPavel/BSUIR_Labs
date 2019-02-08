package by.bsuir.verpav.misoi.histogram

import java.awt._
import java.awt.event.KeyEvent
import java.awt.geom.Rectangle2D
import javax.swing._

/**
  * Created by Pavel_Verkhovtsov on 9/14/16.
  */
object SimpleHistogram {

  def build(data: Array[Int], prefix : String): Unit = {

    val frame = new JFrame(prefix + " Histogram")
    frame.setLayout(new BorderLayout())

    val color = prefix match {
      case "Red" => Some(Color.RED)
      case "Blue" => Some(Color.BLUE)
      case "Green" => Some(Color.GREEN)
      case _ => None
    }

        frame.add(new JScrollPane(new Graph(data, color)))
        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.setVisible(true)
    }

  def |-|-| (data: Array[Int], prefix : String = ""): Unit = {
    build(data, prefix)
  }
}

class Graph(mapHistory: Array[Int], color : Option[Color]) extends JPanel {
  val MIN_BAR_WIDTH = 4
  val graphWidth = (mapHistory.length * MIN_BAR_WIDTH) + 11
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
      val barWidth = Math.max(MIN_BAR_WIDTH, Math.floor(width / mapHistory.length.toFloat).toInt)
      println("width = " + width + "; size = " + mapHistory.length + "; barWidth = " + barWidth)

      val maxValue = mapHistory.max

      var xPos = xOffset
      for (i <- mapHistory.indices) {
        val value = mapHistory(i)
        val barHeight = Math.round((value / maxValue.toFloat) * height)
        val yPos = height + yOffset - barHeight
        val bar = new Rectangle2D.Float(xPos, yPos, barWidth, barHeight)
        if(color.isEmpty)
          g2d.setColor(new Color(i, i, i))
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