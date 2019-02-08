package by.bsuir.mastering.pavelverk.simpleANN.neurons

class OutputNeuron() extends Serializable{

  var sum = 0.0
  var result = 0.0
  var signalCounter = 0

  def pulse(x: Double): Unit = {
    sum += x
    signalCounter += 1
    result = sum / signalCounter
  }

  def getResult: Double = result
}
