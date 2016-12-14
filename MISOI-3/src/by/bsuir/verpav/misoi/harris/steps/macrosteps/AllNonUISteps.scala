package by.bsuir.verpav.misoi.harris.steps.macrosteps

import by.bsuir.verpav.misoi.harris.steps.{CalculateDifference, GaussFilter, HarrisCornersResponse, NonMaxFilter}
import by.bsuir.verpav.misoi.pipeline.{MacroPipelineStep, PipelineStep}

/**
  * Created by Pavel_Verkhovtsov on 10/26/16.
  */
object AllNonUISteps extends PipelineStep with MacroPipelineStep {
  override def stepList: Array[PipelineStep] = Array(CalculateDifference, GaussFilter, HarrisCornersResponse, NonMaxFilter)
}
