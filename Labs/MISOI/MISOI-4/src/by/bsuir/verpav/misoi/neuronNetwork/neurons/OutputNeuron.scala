package by.bsuir.verpav.misoi.neuronNetwork.neurons

import by.bsuir.verpav.misoi.neuronNetwork.NeuronNetworkUtils

/**
  * Created by verkpavel on 06.11.16.
  */
class OutputNeuron(size : Int, val className : String){

  var result = 0.0
  var signalCounter = 0

  val weights  = NeuronNetworkUtils.generateWeightVector(size)

  def pulse(x : Double): Unit =
  {
    result+= x
    signalCounter+=1
  }

  def modifyWeights(op : (Double, Double) => Double, correctionVector : Array[Double]) = {
    for(i <- weights.indices){
      weights(i) = op(weights(i), correctionVector(i))
    }
  }

  def getResult: Double ={
    val res = result / signalCounter
    result = 0
    signalCounter = 0
    res
  }
}
