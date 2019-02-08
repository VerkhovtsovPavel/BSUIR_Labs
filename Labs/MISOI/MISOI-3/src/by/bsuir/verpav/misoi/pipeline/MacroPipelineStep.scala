package by.bsuir.verpav.misoi.pipeline

import java.awt.image.BufferedImage
import javax.swing.JFrame

import by.bsuir.verpav.misoi.pipeline.PipelineEngine.globalState

/**
  * Created by Pavel_Verkhovtsov on 10/26/16.
  */
trait MacroPipelineStep{
  self: PipelineStep =>
    override def perform(baseImage: BufferedImage): BufferedImage = stepList.tail.foldLeft(stepList(0).apply _)((acc, x) => acc andThen x.apply)(baseImage)
    override def requestParameters(frame: JFrame): Unit = stepList.foreach(_.requestParameters(frame))

    def stepList : Array[PipelineStep]
}
