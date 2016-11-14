package by.bsuir.verpav.misoi.neuronNetwork.neurons

import by.bsuir.verpav.misoi.neuronNetwork.NeuronNetworkUtils

/**
  * Created by verkpavel on 06.11.16.
  */
class OutputNeuron(size : Int){

  var result = 0.0
  var signalCounter = 0

  var weights  = NeuronNetworkUtils.generateWeightVector(size)

  def pulse(x : Double): Unit =
  {
    result+=weights(signalCounter) * x
    signalCounter+=1
  }

  def getResult: Double ={
    val res = result / signalCounter
    result = 0
    signalCounter = 0
    res
  }
}
