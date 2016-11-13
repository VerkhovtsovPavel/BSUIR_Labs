package by.bsuir.verpav.misoi.ui

import java.awt.{Font, Toolkit, Image, FileDialog}
import java.awt.event.{ActionEvent, ActionListener}
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing._

import by.bsuir.verpav.misoi.harris.HarrisCornersDetector
import by.bsuir.verpav.misoi.neuronNetwork.NeuronNetwork
import scala.collection.mutable

/**
  * Created by Pavel_Verkhovtsov on 9/14/16.
  */
object MainForm extends JFrame with App{

  var baseImage: BufferedImage = _
  val imageLabel : JLabel = new JLabel()

  val neuronNetwork = new NeuronNetwork(20, 20)

  val sampleSets = mutable.Map[String, List[File]]()

  createMenu(this)
  createUIElements(this)
  setVisible(true)

  def setImageToLabel(): Unit = {
    val resizedImage = baseImage.getScaledInstance(imageLabel.getWidth, imageLabel.getHeight, Image.SCALE_SMOOTH)
    val icon = new ImageIcon(resizedImage)
    imageLabel.setIcon(icon)
    JOptionPane.showConfirmDialog(this,"Image updated", "Image updated", JOptionPane.CLOSED_OPTION)
  }

   private def loadImage(): Unit = {
    val chooser = new FileDialog(this, "Use a .png or .jpg extension", FileDialog.LOAD)
    chooser.setVisible(true)
    val filename = chooser.getDirectory + chooser.getFile
    baseImage = ImageIO.read(new File(filename))
    setImageToLabel()
  }

  private def loadSampleSetFolder(): Unit = {
    val chooser = new FileDialog(this, "Use a .png or .jpg extension", FileDialog.LOAD)
    chooser.setVisible(true)
    val folderName = chooser.getDirectory + chooser.getFile
    sampleSets.put(chooser.getFile, new File(folderName).listFiles().toList)
  }

  private def createUIElements(frame: JFrame): Unit = {
    setResizable(false)
    setTitle("Image processor")
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    val mainPanel = new JPanel()
    setContentPane(mainPanel)
    mainPanel.setLayout(null)
    val dimension = Toolkit.getDefaultToolkit.getScreenSize
    setSize((dimension.getWidth / 2).toInt , (dimension.getHeight / 2).toInt)
    imageLabel.setSize((dimension.getWidth / 2).toInt, (dimension.getHeight / 2).toInt)
    imageLabel.setLocation(0, 0)
    this.add(imageLabel)
    val x1 = (dimension.getWidth - getWidth / 2).toInt
    val y1 = (dimension.getHeight - getHeight / 2).toInt
    setLocation(x1, y1)
  }

  def learn() : Unit = {
    neuronNetwork.learn(sampleSets)
  }

  def classify() : Unit = {
    neuronNetwork.classify(baseImage)
  }

  def createMenu(frame: JFrame): Unit = {
    val menuBar = new JMenuBar()
    val font = new Font("Verdana", Font.PLAIN, 11)

    val fileMenu = new JMenu("File")
    fileMenu.setFont(font)

    val loadItem = new JMenuItem("Add class")
    loadItem.setFont(font)
    loadItem.addActionListener(loadSampleSetFolder _)
    fileMenu.add(loadItem)

    val exitItem = new JMenuItem("Exit")
    exitItem.setFont(font)
    exitItem.addActionListener(() => { frame.dispose(); System.exit(0)})
    fileMenu.add(exitItem)

    val clustering = new JMenu("Neurons network")
    clustering.setFont(font)

    val performClusteringStepItem = new JMenuItem("Learn")
    performClusteringStepItem.setFont(font)
    performClusteringStepItem.addActionListener(learn _)
    performClusteringStepItem.setAccelerator(KeyStroke.getKeyStroke('L', Toolkit.getDefaultToolkit.getMenuShortcutKeyMask))
    clustering.add(performClusteringStepItem)

    val performClusteringStepItemWithCustomParameters = new JMenuItem("Classify")
    performClusteringStepItemWithCustomParameters.setFont(font)
    performClusteringStepItemWithCustomParameters.addActionListener(classify _)
    performClusteringStepItemWithCustomParameters.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit.getMenuShortcutKeyMask))
    clustering.add(performClusteringStepItemWithCustomParameters)


    menuBar.add(fileMenu)
    menuBar.add(clustering)
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