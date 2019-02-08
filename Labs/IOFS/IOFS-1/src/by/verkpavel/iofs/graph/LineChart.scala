package by.verkpavel.iofs.graph

import java.awt.Color

import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.{ChartFactory, ChartPanel}
import org.jfree.data.xy.{XYSeries, XYSeriesCollection}
import org.jfree.ui.{ApplicationFrame, RefineryUtilities}


class LineChart(title: String) extends ApplicationFrame(title) {
  val dataset = new XYSeriesCollection()

  def addDataset(values: List[Double]) {

    val series = new XYSeries("Graph")
    for (i <- values.indices) {
      series.add(i + 1.0, values(i))
    }
    dataset.addSeries(series)
  }

  def draw(x : String , y : String ) {
    val chart = ChartFactory.createXYLineChart("Line Charts", x, y, dataset,
      PlotOrientation.VERTICAL, true, true, false)

    chart.setBackgroundPaint(Color.white)

    val plot = chart.getXYPlot
    plot.setBackgroundPaint(Color.lightGray)
    plot.setDomainGridlinePaint(Color.white)
    plot.setRangeGridlinePaint(Color.white)

    val chartPanel = new ChartPanel(chart)
    chartPanel.setPreferredSize(new java.awt.Dimension(500, 270))
    setContentPane(chartPanel)

    this.pack()
    RefineryUtilities.centerFrameOnScreen(this)
    this.setVisible(true)
  }
}