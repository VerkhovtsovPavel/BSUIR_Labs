package by.verkpavel.iofs.classification

class DecisionFunction(parametersWord : Array[String]) {
  var polynomial = new Array[Double](parametersWord.length)

  def calculate(documentPolynomial: Array[Double]) : Double = {
    var result: Double = 0
    for (i <- polynomial.indices) {
      result += polynomial(i) * documentPolynomial(i)
    }
    result
  }

  def  punishment(documentPolynomial: Array[Double]) = {
    for (i <- polynomial.indices) {
      polynomial(i) -= documentPolynomial(i)
    }
  }

  def  promotion(documentPolynomial: Array[Double]) = {
    for (i <- polynomial.indices) {
      polynomial(i) += documentPolynomial(i)
    }
  }

}
