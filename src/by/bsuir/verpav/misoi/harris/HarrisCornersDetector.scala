package by.bsuir.verpav.misoi.harris

import java.awt.image.BufferedImage
import javax.swing.JFrame

import by.bsuir.verpav.misoi.harris.steps.{CalculateDifference, GaussFilter, _}
import by.bsuir.verpav.misoi.pipeline.{PipelineEngine, PipelineStep}

/**
  * Created by Pavel_Verkhovtsov on 10/19/16.
  */
object HarrisCornersDetector extends PipelineEngine{

  object AllNonUISteps extends PipelineStep {
    import by.bsuir.verpav.misoi.pipeline.PipelineEngine.globalState
    override def perform(baseImage: BufferedImage): BufferedImage = ( CalculateDifference.apply _  andThen
                                                                      GaussFilter.apply            andThen
                                                                      HarrisCornersResponse.apply  andThen
                                                                      NonMaxFilter.apply)(baseImage)

    override def requestParameters(frame: JFrame): Unit = ???
  }

  override def steps: Array[PipelineStep] = Array(GrayScaleImage, AllNonUISteps, HighlightCorners)
}
