package by.bsuir.verpav.misoi.neuronNetwork

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import by.bsuir.verpav.misoi.neuronNetwork.neurons.{HiddenNeuron, InputNeuron, OutputNeuron}
import by.bsuir.verpav.misoi.util.ImageUtils

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class NeuronNetwork(sampleHeigth: Int, sampleWidth: Int) {

  var inputNeurons: Array[InputNeuron[BufferedImage]] = new Array[InputNeuron[BufferedImage]](sampleHeigth * sampleWidth)

  val outputNeurons: ArrayBuffer[OutputNeuron] = ArrayBuffer[OutputNeuron]()

  var classes: ArrayBuffer[String] = ArrayBuffer[String]()

  def learn(className: String, sampleSet: List[Image]): Unit = {
    val outputNeuron = new OutputNeuron(sampleSet.length)
    outputNeurons.append(outputNeuron)
    classes.append(className)
    for (x <- 0 until sampleWidth; y <- 0 until sampleHeigth) {
      val (r, c) = calculateParametersGaussian(sampleSet, extractPixel(_: Image, x, y))
      val hiddenNeuron = new HiddenNeuron(NeuronNetworkUtils.gaussFunction(_: Double, r, c))
      hiddenNeuron.addNeuronToNextLayer(outputNeuron)
      inputNeurons(x * sampleWidth + y).addNeuronToNextLayer(hiddenNeuron)
    }
  }

  def learn(sampleSets: mutable.Map[String, List[File]]): Unit = {
    for (x <- 0 until sampleWidth; y <- 0 until sampleHeigth) {
      inputNeurons(x * sampleWidth + y) = new InputNeuron[BufferedImage](extractPixel(_: BufferedImage, x, y))
    }

    val transformedSampleSet = sampleSets.map(ent => (ent._1, ent._2.map(ImageIO.read(_).getScaledInstance(sampleWidth, sampleHeigth, Image.SCALE_SMOOTH))))
    for (sampleSet <- transformedSampleSet) {
      learn(sampleSet._1, sampleSet._2)
    }
  }

  def classify(image: BufferedImage) : String = {
    val preparedImage = convertToBufferedImage(image.getScaledInstance(sampleWidth, sampleHeigth, Image.SCALE_SMOOTH))
    inputNeurons.foreach(_.pulse(preparedImage))
    val results = outputNeurons.map(_.getResult)
    val className = classes(results.zipWithIndex.maxBy(_._1)._2)
    className
  }

  private def calculateParametersGaussian(sampleSet: List[Image], paramCalculator: Image => Double): (Double, Double) = {
    val params = sampleSet.map(paramCalculator)
    val r = params.max - params.min
    val c = params.sum / params.length
    (r, c)
  }

  private def extractPixel(image: BufferedImage, x: Int, y: Int): Int = {
    val pixel = ImageUtils.getPixel(image.getRaster, x, y)
    ImageUtils.pointBrightness(pixel._1, pixel._2, pixel._3)
  }

  private def extractPixel(image: Image, x: Int, y: Int): Int = {
    extractPixel(convertToBufferedImage(image), x, y)
  }


  private def convertToBufferedImage(image: Image): BufferedImage = {
    val newImage = new BufferedImage(
      image.getWidth(null), image.getHeight(null),
      BufferedImage.TYPE_INT_ARGB)
    val g = newImage.createGraphics()
    g.drawImage(image, 0, 0, null)
    g.dispose()
    newImage
  }
}
