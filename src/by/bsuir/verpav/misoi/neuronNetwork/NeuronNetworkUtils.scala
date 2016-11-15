package by.bsuir.verpav.misoi.neuronNetwork

import scala.util.Random

/**
  * Created by verkpavel on 06.11.16.
  */
object NeuronNetworkUtils {

  def gaussFunction(x : Double, c : Double, r : Double) ={
    if(r==0){
      if(x==c)
        1.0
      else
        0.0
    }
     else {
       Math.exp(-(Math.pow(x - c, 2) / Math.pow(r, 2)))
     }
  }

  def generateWeightVector(size : Int): Array[Double] = {
    val random = new Random()
    Array.fill[Double](size)(random.nextDouble * 2 - 1)
  }


  def calculateParametersGaussian[T](sampleSet: List[T], paramCalculator: T => Double): (Double, Double) = {
    val params = sampleSet.map(paramCalculator)
    val r = params.max - params.min
    val c = params.sum / params.length
    (r, c)
  }
}
