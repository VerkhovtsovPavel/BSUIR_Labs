package by.bsuir.verpav.misoi.neuronNetwork

import java.awt.image.BufferedImage

import by.bsuir.verpav.misoi.neuronNetwork.neurons.{OutputNeuron, InputNeuron, HiddenNeuron}
import by.bsuir.verpav.misoi.util.ImageUtils

class NeuronNetwork {

  var inputNeurons: Array[InputNeuron[BufferedImage]] = new Array[InputNeuron[BufferedImage]](400)

  var hiddenNeurons: List[HiddenNeuron] = _

  var classes: List[String] = _

  def learn(className: String, sampleSet: List[BufferedImage]) = {
    val outputNeuron = new OutputNeuron()
    for (x <- sampleSet.head.getWidth; y <- sampleSet.head.getHeight) {
      val (r, c) = calculateParametersGaussian(sampleSet, extractPixel(_: BufferedImage, x, y))
      new HiddenNeuron(NeuronNetworkUtils.gaussFunction(_ : Double, r, c), List(outputNeuron))
    }

    //inputNeurons = countOfParameters
    //hiddenNeurons += countOfParameters
    //outputNeurons += 1
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
