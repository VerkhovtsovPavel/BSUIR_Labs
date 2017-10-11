package by.bsuir.mastering.pavelverk.simpleANN

import by.bsuir.mastering.pavelverk.simpleANN.neurons.{HiddenNeuron, InputNeuron, OutputNeuron}

class NeuronNetwork[T](featureCount : Int, threshold: Double) {

  var inputNeurons = new Array[InputNeuron](featureCount)
  val outputNeuron: OutputNeuron = new OutputNeuron(threshold)

  def learn(sampleSet: List[List[Double]]) {
    val features = sampleSet.transpose
    for(i <- features.indices){
      val input = new InputNeuron(x=>x(i))
      val hidden = new HiddenNeuron(GaussFunctionBuilder.forSample(features(i)))
      hidden.addNeuronToNextLayer(outputNeuron)
      input.addNeuronToNextLayer(hidden)
      inputNeurons(i) = input
    }
  }

  def classify(sample: List[Double]) : Boolean = {
    assert(sample.size!=featureCount, "Sample size not equal network size")
    inputNeurons.foreach(_.pulse(sample))
    outputNeuron.getSolution
  }
}
