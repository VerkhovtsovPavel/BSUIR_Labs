package by.bsuir.verpav.misoi.pipeline

import scala.collection.mutable

abstract class PipelineEngine {
  def steps : Array[PipelineStep]

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

  def clear(): Unit = {
    possition = 0
    currentStep = steps.head
    PipelineEngine.globalState.clear()
  }
}

object PipelineEngine{
  implicit val globalState = mutable.Map[String, Any]()
}
