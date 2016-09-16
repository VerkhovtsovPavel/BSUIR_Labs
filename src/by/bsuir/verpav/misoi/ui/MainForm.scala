package by.bsuir.verpav.misoi.ui

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{FileDialog, Font, Label, Toolkit}
import java.io.File
import javax.imageio.ImageIO
import javax.swing._

/**
  * Created by Pavel_Verkhovtsov on 9/14/16.
  */
class MainForm extends JFrame {
    setResizable(false)
    setTitle("Image processor")
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    val mainPanel = new JPanel()
    setContentPane(mainPanel)
    mainPanel.setLayout(null)
    setSize(460, 310)
    createMenu(this)
    val imageLabel = new JLabel()
    imageLabel.setSize(460, 310)
    imageLabel.setLocation(0, 0)
    this.add(imageLabel)

    createUIElements(this)
    val dimension = Toolkit.getDefaultToolkit.getScreenSize
    val x1 = (dimension.getWidth - getWidth / 2).toInt
    val y1 = (dimension.getHeight - getHeight / 2).toInt
    setLocation(x1,y1)
    setVisible(true)

  def loadImage() = {
    val chooser = new FileDialog(this, "Use a .png or .jpg extension", FileDialog.LOAD)
    chooser.setVisible(true)
    val filename = chooser.getDirectory+chooser.getFile
    val img = ImageIO.read(new File(filename))
    img.
    val icon = new ImageIcon(img)
    imageLabel.setIcon(icon)
  }

  def saveImage() = ???

  def buildHistogram() = ???

  def createUIElements(frame: JFrame) = {


  }

  def createMenu(frame : JFrame) {
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
    exitItem.addActionListener(frame.dispose _)
    fileMenu.add(exitItem)

    val histogram = new JMenu("Histogram")
    histogram.setFont(font)

    val buildHistogramItem = new JMenuItem("Build histogram")
    buildHistogramItem.setFont(font)
    buildHistogramItem.addActionListener(buildHistogram _)
    histogram.add(buildHistogramItem)

    menuBar.add(fileMenu)
    menuBar.add(histogram)
    frame.setJMenuBar(menuBar)
  }


  implicit def paramLambdaToActiveListener(lambda: (ActionEvent) => Unit) : ActionListener = new ActionListener(){
    override def actionPerformed(e : ActionEvent): Unit = {
      lambda(e)
    }
  }

  implicit def lambdaToActiveListener(lambda: () => Unit) : ActionListener = new ActionListener(){
    override def actionPerformed(e : ActionEvent): Unit = {
      lambda()
    }
  }
}

object  MainForm{
  def apply(): MainForm = new MainForm()
}
