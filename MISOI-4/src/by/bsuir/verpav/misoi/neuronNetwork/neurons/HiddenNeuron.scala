package by.bsuir.verpav.misoi.neuronNetwork.neurons

/**
  * Created by verkpavel on 06.11.16.
  */
class HiddenNeuron(RBFunction : Double => Double) {

  var nextLayer : List[OutputNeuron] = List[OutputNeuron]()

  def pulse(x : Double): Unit =
  {
    val value = RBFunction(x)
    nextLayer.foreach(_.pulse(value))
  }

  def addNeuronToNextLayer(neuron : OutputNeuron): Unit ={
    nextLayer = neuron :: nextLayer
  }
}
