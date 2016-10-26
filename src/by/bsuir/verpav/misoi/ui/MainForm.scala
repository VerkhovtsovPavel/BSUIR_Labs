package by.bsuir.verpav.misoi.ui

import java.awt.event.{ActionEvent, ActionListener}
import java.awt._
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing._

import by.bsuir.verpav.misoi.harris.HarrisCornersDetector
import scala.collection.mutable

/**
  * Created by Pavel_Verkhovtsov on 9/14/16.
  */
object MainForm extends JFrame with App{

  var baseImage: BufferedImage = _
  val imageLabel : JLabel = new JLabel()

  val editingHistory = mutable.Stack[BufferedImage]()

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
    editingHistory.clear()
    HarrisCornersDetector.clear()
    editingHistory push baseImage
    setImageToLabel()
  }

  private def saveImage(): Unit = {
    val chooser = new FileDialog(this, "Use a .png extension", FileDialog.SAVE)
    chooser.setVisible(true)
    val filename = chooser.getDirectory + chooser.getFile
    ImageIO.write(baseImage, "png", new File(filename))
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

  def performClusteringStep(useCustomParams : Boolean, e : ActionEvent): Unit = {
    import by.bsuir.verpav.misoi.pipeline.PipelineEngine.globalState
    if(useCustomParams)
      HarrisCornersDetector.currentStep.requestParameters(this)
    baseImage = HarrisCornersDetector.currentStep(baseImage)
    editingHistory push baseImage
    if(!HarrisCornersDetector.nextStep()){
      JOptionPane.showConfirmDialog(this,"Clustering finish", "Clustering finish", JOptionPane.CLOSED_OPTION)
    }
    setImageToLabel()
  }

  def performAllClustering(): Unit = {
    import by.bsuir.verpav.misoi.pipeline.PipelineEngine.globalState
    baseImage = HarrisCornersDetector.currentStep(baseImage)
    while(HarrisCornersDetector.nextStep()){
      editingHistory push baseImage
      baseImage = HarrisCornersDetector.currentStep(baseImage)
    }
    editingHistory push baseImage
    JOptionPane.showConfirmDialog(this,"Clustering finish", "Clustering finish", JOptionPane.CLOSED_OPTION)
    setImageToLabel()
  }

  def undo(): Unit ={
    HarrisCornersDetector.previousStep()
    editingHistory.pop()
    baseImage = editingHistory.top
    setImageToLabel()
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

    val clustering = new JMenu("Clustering")
    clustering.setFont(font)

    val performClusteringStepItem = new JMenuItem("Perform step")
    performClusteringStepItem.setFont(font)
    performClusteringStepItem.addActionListener(performClusteringStep(false, _ : ActionEvent))
    performClusteringStepItem.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit.getMenuShortcutKeyMask))
    clustering.add(performClusteringStepItem)

    val performClusteringStepItemWithCustomParameters = new JMenuItem("Perform step with custom paramaters")
    performClusteringStepItemWithCustomParameters.setFont(font)
    performClusteringStepItemWithCustomParameters.addActionListener(performClusteringStep(true, _ : ActionEvent))
    performClusteringStepItemWithCustomParameters.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit.getMenuShortcutKeyMask))
    clustering.add(performClusteringStepItemWithCustomParameters)

    val performAllClusteringItem = new JMenuItem("Perform all process")
    performAllClusteringItem.setFont(font)
    performAllClusteringItem.addActionListener(performAllClustering _)
    performAllClusteringItem.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit.getMenuShortcutKeyMask))
    clustering.add(performAllClusteringItem)

    val edit = new JMenu("Edit")
    edit.setFont(font)

    val undoItem = new JMenuItem("Undo")
    undoItem.setFont(font)
    undoItem.addActionListener(undo _)
    undoItem.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit.getMenuShortcutKeyMask))
    edit.add(undoItem)

    menuBar.add(fileMenu)
    menuBar.add(clustering)
    menuBar.add(edit)
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