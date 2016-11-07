package by.bsuir.verpav.misoi.neuronNetwork.neurons

/**
  * Created by verkpavel on 06.11.16.
  */
class HiddenNeuron(RBFunction : Double => Double, nextLayer : List[OutputNeuron]) {

  def pulse(x : Double): Unit =
  {
    val value = RBFunction(x)
    nextLayer.foreach(_.pulse(value))
  }
}
