package by.bsuir.verpav.misoi.neuronNetwork.neurons

/**
  * Created by verkpavel on 06.11.16.
  */
class InputNeuron[T](transformationFunction : T => Double, nextLayer : List[HiddenNeuron]){

  def pulse(x : T): Unit =
  {
    val value = transformationFunction(x)
    nextLayer.foreach(_.pulse(value))
  }
}
