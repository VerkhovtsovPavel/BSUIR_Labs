package by.bsuir.verpav.misoi.util

import java.awt.image.BufferedImage
import javax.swing.{JFrame, JOptionPane}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer


object ImageСlusteringUtils {

  trait ClusteringStep {
    val params = mutable.Map[String, Int]()
    def perform(baseImage: BufferedImage): BufferedImage
    def requestParameters(frame: JFrame): Unit
    def apply(baseImage: BufferedImage): BufferedImage = perform(baseImage)
  }

  object ImageBinarization extends ClusteringStep {

    override def perform(baseImage: BufferedImage): BufferedImage = {
      val threshold = params.getOrElse("Threshold", 200)
      ImageUtils.binaryImageTransformation(baseImage,
        (r: Int, g: Int, b: Int) => ImageUtils.pointBrightness(r, g, b) > threshold,
        (r: Int, g: Int, b: Int) => Array(255, 255, 255),
        (r: Int, g: Int, b: Int) => Array(0, 0, 0))
    }

    override def requestParameters(frame: JFrame): Unit = {
      val threshold = JOptionPane.showInputDialog(frame, "Please input threshold for 0 to 255: ")
      params.put("Threshold", threshold.toInt)
    }
  }


  object BrightnessCorrection extends ClusteringStep {

    override def perform(baseImage: BufferedImage): BufferedImage = {
      val isUseCorrection = params.getOrElse("isUseCorrection", 1)
      if (isUseCorrection == 1) {
        val colors = ImageUtils.extractColors(baseImage)
        val red = colors.map(_._1)
        val redMin = red.min
        val redMax = red.max
        val green = colors.map(_._2)
        val greenMin = green.min
        val greenMax = green.max
        val blue = colors.map(_._3)
        val blueMin = blue.min
        val blueMax = blue.max

        def newPixelValue(pixel: Int, max: Int, min: Int) = ((pixel - min).toDouble / (max - min).toDouble * 255).toInt

        ImageUtils.binaryImageTransformation(baseImage,
          (r: Int, g: Int, b: Int) => true,
          (r: Int, g: Int, b: Int) => Array(newPixelValue(r, redMax, redMin), newPixelValue(g, greenMax, greenMin), newPixelValue(b, blueMax, blueMin)),
          (r: Int, g: Int, b: Int) => Array(0, 0, 0))
      }
      else
        baseImage
    }

    override def requestParameters(frame: JFrame): Unit = {
      val isUseCorrection = JOptionPane.showInputDialog(frame, "Use correction 0 or 1: ")
      params.put("isUseCorrection", isUseCorrection.toInt)
    }
  }

  object FilterNoise extends ClusteringStep {
    override def perform(baseImage: BufferedImage): BufferedImage = {
      val coreSize = params.getOrElse("coreSize", 3)
      val min = ImageUtils.spatialImageTransformation(_: BufferedImage, coreSize,
        (x: Array[(Int, Int, Int)]) => x.minBy((ImageUtils.pointBrightness _).tupled))
      val max = ImageUtils.spatialImageTransformation(_: BufferedImage, coreSize,
        (x: Array[(Int, Int, Int)]) => x.maxBy((ImageUtils.pointBrightness _).tupled))
      min.andThen(max)(baseImage)
    }

    override def requestParameters(frame: JFrame): Unit = {
      val coreSize = JOptionPane.showInputDialog(frame, "Please input core size for 3 to 17: ")
      params.put("coreSize", coreSize.toInt)
    }
  }

  object HighlightConnectedDomains extends ClusteringStep{
   override def perform(baseImage : BufferedImage) : BufferedImage = {
     val regions = mutable.Map[Int, ArrayBuffer[(Int, Int)]]()
     val cm = baseImage.getColorModel
     val isAlphaPremultiplied = cm.isAlphaPremultiplied
     val raster = baseImage.copyData(null)
     for(x <- 0 until baseImage.getWidth; y <- 0 until baseImage.getHeight) {
       val rgb = raster.getPixel(x, y, null.asInstanceOf[Array[Int]])
       if(rgb(0)==255){
         val firstRegion = regions.find((e : (Int, ArrayBuffer[(Int, Int)])) => e._2.contains((x-1, y)))
         val secondRegion = regions.find((e : (Int, ArrayBuffer[(Int, Int)])) => e._2.contains((x, y-1)))

         if(firstRegion.isEmpty && secondRegion.isEmpty){
           regions.put(if (regions.keys.isEmpty) 0 else regions.keys.max+1, ArrayBuffer[(Int, Int)]((x,y)))
         }
         else if(firstRegion.isDefined && secondRegion.isEmpty){
           firstRegion.get._2.append((x,y))
         }
         else if(firstRegion.isEmpty && secondRegion.isDefined){
           secondRegion.get._2.append((x,y))
         }
         else if(firstRegion.isDefined && secondRegion.isDefined){
           if(firstRegion!=secondRegion) {
             firstRegion.get._2.appendAll(secondRegion.get._2)
             regions.remove(secondRegion.get._1)
           }else{
             firstRegion.get._2.append((x,y))
           }
         }
       }
     }

     val regionsCount = regions.keys.size
     var currentRegion = 0
     for(v <- regions.values if v.size > 10){
       currentRegion+=1
       val regionColor = (255.0 / regionsCount * currentRegion).toInt
       for((x,y) <- v){
         raster.setPixel(x,y, Array[Int](regionColor, 255-regionColor, 0))
       }
     }
     new BufferedImage(cm, raster, isAlphaPremultiplied, null)
   }

    override def requestParameters(frame: JFrame): Unit = {}
  }
  //  def determineObjectsProperties(baseImage : BufferedImage, params : Map[String, Int]) : BufferedImage = {}
  //  def clusteringObjects(baseImage : BufferedImage, params : Map[String, Int]) : BufferedImage = {}
  val stepsMap = Array[ClusteringStep](BrightnessCorrection, ImageBinarization, FilterNoise, HighlightConnectedDomains)
  //                                                DetermineObjectsProperties, ClusteringObjects)
  var possition = 0
  var currentStep: ClusteringStep = stepsMap.head

  def nextStep(): Boolean = {
    if(possition==stepsMap.length-1) {
      false
    }else{
      possition+=1
      currentStep = stepsMap(possition)
      true
    }
  }

  //TODO Fix uncorrect return from last position
  def previousStep(): Boolean = {
    if(possition==0)
      false
    else {
      possition -= 1
      currentStep = stepsMap(possition)
      true
    }
  }
}
