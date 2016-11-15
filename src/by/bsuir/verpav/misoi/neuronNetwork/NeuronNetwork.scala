package by.bsuir.verpav.misoi.neuronNetwork

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import by.bsuir.verpav.misoi.neuronNetwork.neurons.{HiddenNeuron, InputNeuron, OutputNeuron}
import by.bsuir.verpav.misoi.neuronNetwork.NeuronNetworkUtils.calculateParametersGaussian
import by.bsuir.verpav.misoi.util.ImageUtils
import by.bsuir.verpav.misoi.util.ImageUtils.getPixelBrightness

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class NeuronNetwork(sampleHeigth: Int, sampleWidth: Int) {

  var inputNeurons: Array[InputNeuron[BufferedImage]] = new Array[InputNeuron[BufferedImage]](sampleHeigth * sampleWidth)

  val outputNeurons: ArrayBuffer[OutputNeuron] = ArrayBuffer[OutputNeuron]()

  var classes: ArrayBuffer[String] = ArrayBuffer[String]()

  def learn(className: String, sampleSet: List[BufferedImage]): Unit = {
    val outputNeuron = new OutputNeuron(sampleHeigth * sampleWidth, className)
    outputNeurons.append(outputNeuron)
    for (x <- 0 until sampleWidth; y <- 0 until sampleHeigth) {
      val (r, c) = calculateParametersGaussian(sampleSet, getPixelBrightness(_: BufferedImage, x, y))
      val hiddenNeuron = new HiddenNeuron(NeuronNetworkUtils.gaussFunction(_: Double, r, c))
      hiddenNeuron.addNeuronToNextLayer(outputNeuron)
      inputNeurons(x * sampleWidth + y).addNeuronToNextLayer(hiddenNeuron)
    }
  }

  def learn(sampleSets: mutable.Map[String, List[File]]): Unit = {
    for (x <- 0 until sampleWidth; y <- 0 until sampleHeigth) {
      inputNeurons(x * sampleWidth + y) = new InputNeuron[BufferedImage](getPixelBrightness(_: BufferedImage, x, y))
    }

    val transformedSampleSet = sampleSets.map(ent => (ent._1, ent._2.map((i) => ImageUtils.imageBinarization(convertToBufferedImage(ImageIO.read(i).getScaledInstance(sampleWidth, sampleHeigth, Image.SCALE_SMOOTH))))))
    for (sampleSet <- transformedSampleSet) {
      learn(sampleSet._1, sampleSet._2)
    }

    val zippedSet = new Random().shuffle(for (key <- transformedSampleSet.keys; value <- transformedSampleSet(key)) yield (key, value))

    //colibrateWeightVectors(zippedSet, 0)
  }

  def classify(image: BufferedImage) : String = {
    val preparedImage = ImageUtils.imageBinarization(convertToBufferedImage(image.getScaledInstance(sampleWidth, sampleHeigth, Image.SCALE_SMOOTH)))
    inputNeurons.foreach(_.pulse(preparedImage))
    val results = outputNeurons.map(_.getResult)
    val className = outputNeurons.zip(results).maxBy(_._2)._1.className
    className
  }

  @tailrec
  private def colibrateWeightVectors(zippedSet: Iterable[(String, BufferedImage)], currentIteration: Int): Unit = {
    var isFailed = false
    for(sample <- zippedSet){
      val prognosesClass = classify(sample._2)
      if(prognosesClass!=sample._1){
        isFailed = true
        val correctClass = outputNeurons.filter(_.className==sample._1)
        val incorrectClasses = outputNeurons.filter(_.className==prognosesClass)
        correctClass.foreach(_.modifyWeights((x,y) => x+y, incorrectClasses.head.weights))
        incorrectClasses.foreach(_.modifyWeights((x,y) => x-y, correctClass.head.weights))
      }
    }
    if(currentIteration < 100 && isFailed)
      colibrateWeightVectors(zippedSet, currentIteration+1)
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
