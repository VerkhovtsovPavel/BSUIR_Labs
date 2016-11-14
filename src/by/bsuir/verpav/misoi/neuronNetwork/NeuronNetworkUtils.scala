package by.bsuir.verpav.misoi.neuronNetwork

import scala.util.Random

/**
  * Created by verkpavel on 06.11.16.
  */
object NeuronNetworkUtils {

  def gaussFunction(x : Double, c : Double, r : Double) ={
    Math.exp(-(Math.pow(x-c, 2)/Math.pow(r,2)))
  }

  def generateWeightVector(size : Int): Array[Double] = {
    val random = new Random()
    Array.fill[Double](size)(random.nextDouble)
  }
}
