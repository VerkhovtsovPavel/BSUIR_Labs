package by.bsuir.verpav.misoi.clustering

import java.awt.Color
import java.awt.image.BufferedImage
import javax.swing.{JFrame, JOptionPane}

import by.bsuir.verpav.misoi.clustering.steps._
import by.bsuir.verpav.misoi.ui.table.ObjectPropertiesTable
import by.bsuir.verpav.misoi.util.ImageUtils

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer


object ImageСlusteringEngine {
  implicit val globalState  = mutable.Map[String, Any]()
  val steps = Array[ClusteringStep](BrightnessCorrection, ImageBinarization, FilterNoise, HighlightConnectedDomains, DetermineObjectsProperties, ClusteringObjects)
  //                                                )
  private var possition = 0
  var currentStep: ClusteringStep = steps.head

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
