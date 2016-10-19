package by.bsuir.verpav.misoi.pipeline

import scala.collection.mutable

abstract class PipelineEngine {
  implicit val globalState  = mutable.Map[String, Any]()
  val steps : Array[PipelineStep]

  private var possition = 0
  var currentStep: PipelineStep = steps.head

  def nextStep(): Boolean = {
    if(possition == steps.length)
      false
    else if (possition == steps.length - 1) {
      possition+=1
      false
    } else {
      possition += 1
      currentStep = steps(possition)
      true
    }
  }

  def previousStep(): Boolean = {
    if (possition == 0)
      false
    else {
      possition -= 1
      currentStep = steps(possition)
      true
    }
  }

  def init(): Unit ={
    possition = 0
    currentStep = steps.head
    globalState.clear()
  }
}
