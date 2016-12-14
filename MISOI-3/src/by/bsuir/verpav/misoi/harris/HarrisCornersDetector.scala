package by.bsuir.verpav.misoi.harris

import by.bsuir.verpav.misoi.harris.steps.macrosteps.AllNonUISteps
import by.bsuir.verpav.misoi.harris.steps.{GrayScaleImage, HighlightCorners}
import by.bsuir.verpav.misoi.pipeline.{PipelineEngine, PipelineStep}

/**
  * Created by Pavel_Verkhovtsov on 10/19/16.
  */
object HarrisCornersDetector extends PipelineEngine{

  override def steps: Array[PipelineStep] = Array(GrayScaleImage, AllNonUISteps, HighlightCorners)
}
