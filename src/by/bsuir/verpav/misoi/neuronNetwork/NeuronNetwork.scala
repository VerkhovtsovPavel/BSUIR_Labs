package by.bsuir.verpav.misoi.neuronNetwork

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import by.bsuir.verpav.misoi.neuronNetwork.neurons.{HiddenNeuron, InputNeuron, OutputNeuron}
import by.bsuir.verpav.misoi.util.ImageUtils

import scala.collection.mutable

class NeuronNetwork(sampleHeigth : Int, sampleWidth : Int) {

  var inputNeurons: Array[InputNeuron[BufferedImage]] = new Array[InputNeuron[BufferedImage]](sampleHeigth*sampleWidth)

  var outputNeurons: Array[OutputNeuron] = _

  var classes: List[String] = _

  def learn(className: String, sampleSet: List[BufferedImage]) = {
    val outputNeuron = new OutputNeuron()
    outputNeurons
    for (x  <- 0 until sampleWidth; y <- 0 until sampleHeigth) {
      val (r, c) = calculateParametersGaussian(sampleSet, extractPixel(_: BufferedImage, x, y))
      val hiddenNeuron = new HiddenNeuron(NeuronNetworkUtils.gaussFunction(_ : Double, r, c))
      hiddenNeuron.addNeuronToNextLayer(outputNeuron)
      inputNeurons(x*sampleWidth+y).addNeuronToNextLayer(hiddenNeuron)
    }

    //inputNeurons = countOfParameters
    //hiddenNeurons += countOfParameters
    //outputNeurons += 1
  }

  def learn(sampleSets: mutable.Map[String, List[File]]) = {
    outputNeurons = new Array[OutputNeuron](sampleSets.size)
    for (x  <- 0 until sampleWidth; y <- 0 until sampleHeigth) {
      inputNeurons(x*sampleWidth+y) = new InputNeuron[BufferedImage](extractPixel(_: BufferedImage, x, y))
    }

    val transformedSampleSet = sampleSets.map(ent => (ent._1, ent._2.map(ImageIO.read(_).getScaledInstance(sampleWidth, sampleHeigth, Image.SCALE_SMOOTH).asInstanceOf[BufferedImage])))
    for(sampleSet <- transformedSampleSet){
      learn(sampleSet._1, sampleSet._2)
    }
  }

  def classify(image : BufferedImage) = {
    val preparedImage = image.getScaledInstance(sampleWidth, sampleHeigth, Image.SCALE_SMOOTH).asInstanceOf[BufferedImage]
    inputNeurons.foreach(_.pulse(preparedImage))
    outputNeurons.zipWithIndex.maxBy(_._1)._2
  }

  private def calculateParametersGaussian(sampleSet: List[BufferedImage], paramCalculator: BufferedImage => Double): (Double, Double) = {
    val params = sampleSet.map(paramCalculator)
    val r = params.max - params.min
    val c = params.sum / params.length
    (r, c)
  }

  private def extractPixel(image: BufferedImage, x: Int, y: Int) = {
    val pixel = ImageUtils.getPixel(image.getRaster, x, y)
    ImageUtils.pointBrightness(pixel._1, pixel._2, pixel._3)
  }
}
