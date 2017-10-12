package by.bsuir.mastering.pavelverk.simpleANN.neurons

class HiddenNeuron(RBFunction : Double => Double) extends Serializable{

  var nextLayer : List[OutputNeuron] = List[OutputNeuron]()

  def pulse(x : Double): Unit = {
    val value = RBFunction(x)
    nextLayer.foreach(_.pulse(value))
  }

  def addNeuronToNextLayer(neuron : OutputNeuron): Unit ={
    nextLayer = neuron :: nextLayer
  }
}
