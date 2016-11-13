package by.bsuir.verpav.misoi.neuronNetwork.neurons

/**
  * Created by verkpavel on 06.11.16.
  */
class OutputNeuron(){

  var result = 0.0
  var signalCounter = 0

  def pulse(x : Double): Unit =
  {
    result+=x
    signalCounter+=1
  }

  def getResult: Double ={
    val res = result / signalCounter
    result = 0
    signalCounter = 0
    res
  }
}
