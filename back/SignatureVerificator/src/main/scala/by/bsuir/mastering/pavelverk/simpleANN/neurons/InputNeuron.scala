package by.bsuir.mastering.pavelverk.simpleANN.neurons

class InputNeuron(transformationFunction : List[Double] => Double) extends Serializable{

  var nextLayer : List[HiddenNeuron] = List[HiddenNeuron]()

  def pulse(x : List[Double]): Unit =
  {
    val value = transformationFunction(x)
    nextLayer.foreach(_.pulse(value))
  }

  def addNeuronToNextLayer(neuron : HiddenNeuron): Unit ={
    nextLayer = neuron :: nextLayer
  }
}