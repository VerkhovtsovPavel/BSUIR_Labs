package by.bsuir.verpav.misoi.harris

import by.bsuir.verpav.misoi.harris.steps.GrayScaleImage
import by.bsuir.verpav.misoi.pipeline.{PipelineEngine, PipelineStep}

/**
  * Created by Pavel_Verkhovtsov on 10/19/16.
  */
object HarrisCornersDetector extends PipelineEngine{
  override val steps: Array[PipelineStep] = Array(GrayScaleImage)
}
