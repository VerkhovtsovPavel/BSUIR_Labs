package by.bsuir.verpav.misoi.ui

import java.awt.event.{ActionEvent, ActionListener}
import java.awt._
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing._

import by.bsuir.verpav.misoi.util.ImageUtils.extractColors
import by.bsuir.verpav.misoi.histogram.SimpleHistogram.|-|-|
import by.bsuir.verpav.misoi.util.ImageUtils

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
  * Created by Pavel_Verkhovtsov on 9/14/16.
  */
class MainForm extends JFrame {

  var baseImage: BufferedImage = _

  var redFuture : Future[Map[Int, Int]] = _
  var greenFuture : Future[Map[Int, Int]] = _
  var blueFuture : Future[Map[Int, Int]] = _
  var brightnessFuture : Future[Map[Int, Int]] = _

  val imageLabel : JLabel = new JLabel()

  createMenu(this)
  createUIElements(this)
  setVisible(true)

  def setImageToLabel(): Unit = {
    //TODO Use related scale coefficients
    //TODO Review label size and location
    val resizedImage = baseImage.getScaledInstance(imageLabel.getWidth, imageLabel.getHeight, Image.SCALE_SMOOTH)
    val icon = new ImageIcon(resizedImage)
    imageLabel.setIcon(icon)
  }

  def startCalculateFrequencies(): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global

    Future { extractColors(baseImage)} onSuccess {
      case data =>
        redFuture = Future {
          data.map(x => x._1).foldLeft(Map[Int, Int]())((map, word) => map + ((word, map.getOrElse(word, 0) + 1)))
        }
        greenFuture = Future {
          data.map(x => x._2).foldLeft(Map[Int, Int]())((map, word) => map + ((word, map.getOrElse(word, 0) + 1)))
        }
        blueFuture = Future {
          data.map(x => x._3).foldLeft(Map[Int, Int]())((map, word) => map + ((word, map.getOrElse(word, 0) + 1)))
        }
        brightnessFuture = Future {
          data.map((ImageUtils.pointBrightness _).tupled).foldLeft(Map[Int, Int]())((map, word) => map + ((word, map.getOrElse(word, 0) + 1)))
        }
    }
  }

  def loadImage(): Unit = {
    val chooser = new FileDialog(this, "Use a .png or .jpg extension", FileDialog.LOAD)
    chooser.setVisible(true)
    val filename = chooser.getDirectory + chooser.getFile
    baseImage = ImageIO.read(new File(filename))
    setImageToLabel()
    startCalculateFrequencies()
  }

  def saveImage(): Unit = {
    val chooser = new FileDialog(this, "Use a .png extension", FileDialog.SAVE)
    chooser.setVisible(true)
    val filename = chooser.getDirectory + chooser.getFile
    ImageIO.write(baseImage, "png", new File(filename))
  }

  def buildHistogram(): Unit = {
    |-|-|(Await.result(redFuture, 60.seconds), "Red")
    |-|-|(Await.result(greenFuture, 60.seconds), "Green")
    |-|-|(Await.result(blueFuture, 60.seconds), "Blue")
    |-|-|(Await.result(brightnessFuture, 60.seconds), "Brightness")
  }

  def createUIElements(frame: JFrame): Unit = {
    setResizable(false)
    setTitle("Image processor")
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    val mainPanel = new JPanel()
    setContentPane(mainPanel)
    mainPanel.setLayout(null)
    setSize(460, 310)
    imageLabel.setSize(460, 310)
    imageLabel.setLocation(0, 0)
    this.add(imageLabel)
    val dimension = Toolkit.getDefaultToolkit.getScreenSize
    val x1 = (dimension.getWidth - getWidth / 2).toInt
    val y1 = (dimension.getHeight - getHeight / 2).toInt
    setLocation(x1, y1)
  }

  def createMenu(frame: JFrame): Unit = {
    val menuBar = new JMenuBar()
    val font = new Font("Verdana", Font.PLAIN, 11)

    val fileMenu = new JMenu("File")
    fileMenu.setFont(font)

    val loadItem = new JMenuItem("Load Image")
    loadItem.setFont(font)
    loadItem.addActionListener(loadImage _)
    fileMenu.add(loadItem)

    val saveItem = new JMenuItem("Save Image")
    saveItem.setFont(font)
    saveItem.addActionListener(saveImage _)
    fileMenu.add(saveItem)

    val exitItem = new JMenuItem("Exit")
    exitItem.setFont(font)
    exitItem.addActionListener(() => { frame.dispose(); System.exit(0)})
    fileMenu.add(exitItem)

    val histogram = new JMenu("Histogram")
    histogram.setFont(font)

    val buildHistogramItem = new JMenuItem("Build histogram")
    buildHistogramItem.setFont(font)
    buildHistogramItem.addActionListener(buildHistogram _)
    histogram.add(buildHistogramItem)

    val filters = new JMenu("Filters")
    filters.setFont(font)

    val preparationFilters = new JMenu("Preparation filters")
    preparationFilters.setFont(font)
    filters.add(preparationFilters)

    val thresholdPreparationFilter = new JMenuItem("Threshold")
    thresholdPreparationFilter.setFont(font)
    thresholdPreparationFilter.addActionListener(() => {
      val threshold = JOptionPane.showInputDialog("Please input threshold for 0 to 255: ")
      baseImage = ImageUtils.binaryImageTransformation(baseImage,
        (r : Int, g : Int, b : Int) => ImageUtils.pointBrightness(r, g, b) > threshold.toInt,
        (r : Int, g : Int, b : Int) => Array(255, 255, 255),
        (r : Int, g : Int, b : Int) => Array(0, 0, 0))
      setImageToLabel()
      startCalculateFrequencies()
    })
    preparationFilters.add(thresholdPreparationFilter)

    val luminanceSlicePreparationFilter = new JMenuItem("Luminance slice")
    luminanceSlicePreparationFilter.setFont(font)
    luminanceSlicePreparationFilter.addActionListener(() => {
      val lowerBound = JOptionPane.showInputDialog("Please input lower bound for 0 to 255: ")
      val upperBound = JOptionPane.showInputDialog("Please input upper bound for 0 to 255: ")
      baseImage = ImageUtils.binaryImageTransformation(baseImage,
        (r: Int, g: Int, b: Int) => {
          val res = ImageUtils.pointBrightness(r, g, b);
          lowerBound.toInt < res && res < upperBound.toInt
        },
        (r: Int, g: Int, b: Int) => Array(255, 255, 255),
        (r: Int, g: Int, b: Int) => Array(0, 0, 0))
      setImageToLabel()
      startCalculateFrequencies()
    })

    preparationFilters.add(luminanceSlicePreparationFilter)

    val minMaxFilters = new JMenu("Min-Max filters")
    minMaxFilters.setFont(font)
    filters.add(minMaxFilters)

    val minFilter = new JMenuItem("Min")
    minFilter.setFont(font)
    minFilter.addActionListener(() => {
      baseImage = ImageUtils.spatialImageTransformation(baseImage, 3,
        (x : Array[(Int, Int, Int)]) => x.minBy((ImageUtils.pointBrightness _).tupled))
      setImageToLabel()
      startCalculateFrequencies()
    })
    minMaxFilters.add(minFilter)

    val maxFilter = new JMenuItem("Max")
    maxFilter.setFont(font)
    maxFilter.addActionListener(() => {
      baseImage = ImageUtils.spatialImageTransformation(baseImage, 3,
        (x : Array[(Int, Int, Int)]) => x.maxBy((ImageUtils.pointBrightness _).tupled))
      setImageToLabel()
      startCalculateFrequencies()
    })
    minMaxFilters.add(maxFilter)

    val minMaxFilter = new JMenuItem("Min-Max")
    minMaxFilter.setFont(font)
    minMaxFilters.addActionListener(() => {
      baseImage = ImageUtils.spatialImageTransformation(baseImage, 3,
        (x : Array[(Int, Int, Int)]) => x.minBy((ImageUtils.pointBrightness _).tupled))
      baseImage = ImageUtils.spatialImageTransformation(baseImage, 3,
        (x : Array[(Int, Int, Int)]) => x.maxBy((ImageUtils.pointBrightness _).tupled))
      setImageToLabel()
      startCalculateFrequencies()
    })
    minMaxFilters.add(minMaxFilter)

    menuBar.add(fileMenu)
    menuBar.add(histogram)
    menuBar.add(filters)
    frame.setJMenuBar(menuBar)
  }


  implicit def paramLambdaToActiveListener(lambda: (ActionEvent) => Unit): ActionListener = new ActionListener() {
    override def actionPerformed(e: ActionEvent): Unit = {
      lambda(e)
    }
  }

  implicit def lambdaToActiveListener(lambda: () => Unit): ActionListener = new ActionListener() {
    override def actionPerformed(e: ActionEvent): Unit = {
      lambda()
    }
  }
}

object MainForm {
  def apply(): MainForm = new MainForm()
}
