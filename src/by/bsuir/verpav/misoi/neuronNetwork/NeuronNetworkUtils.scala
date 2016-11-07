package by.bsuir.verpav.misoi.neuronNetwork

/**
  * Created by verkpavel on 06.11.16.
  */
object NeuronNetworkUtils {

  def gaussFunction(x : Double, c : Double, r : Double) ={
    Math.exp(-(Math.pow(x-c, 2)/Math.pow(r,2)))
  }
}
