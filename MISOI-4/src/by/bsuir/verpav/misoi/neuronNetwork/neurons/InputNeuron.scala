package by.bsuir.verpav.misoi.neuronNetwork.neurons

/**
  * Created by verkpavel on 06.11.16.
  */
class InputNeuron[T](transformationFunction : T => Double){

  var nextLayer : List[HiddenNeuron] = List[HiddenNeuron]()

  def pulse(x : T): Unit =
  {
    val value = transformationFunction(x)
    nextLayer.foreach(_.pulse(value))
  }

  def addNeuronToNextLayer(neuron : HiddenNeuron): Unit ={
    nextLayer = neuron :: nextLayer
  }
}