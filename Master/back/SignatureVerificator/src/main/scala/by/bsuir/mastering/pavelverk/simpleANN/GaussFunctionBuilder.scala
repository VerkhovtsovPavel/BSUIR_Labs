package by.bsuir.mastering.pavelverk.simpleANN

object GaussFunctionBuilder {

  def forSample(list: List[Double]): (Double) => Double = {
    val (r,c) = calculateParametersGaussian(list)
    gaussFunction(_ : Double, c, r)
  }

  def gaussFunction(x : Double, c : Double, r : Double) ={
    if(r==0){
      if(x==c)
        1.0
      else
        0.0
    }
     else {
       Math.exp(-(Math.pow(x - c, 2) / Math.pow(r, 2)))
     }
  }

  def calculateParametersGaussian[T](sampleSet: List[Double]): (Double, Double) = {
    val r = sampleSet.max - sampleSet.min
    val c = sampleSet.sum / sampleSet.length
    (r, c)
  }
}
