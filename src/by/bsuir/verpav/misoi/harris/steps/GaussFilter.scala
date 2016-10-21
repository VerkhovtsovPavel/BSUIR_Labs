package by.bsuir.verpav.misoi.harris.steps

import java.awt.image.BufferedImage
import javax.swing.JFrame

import by.bsuir.verpav.misoi.pipeline.PipelineStep

/**
  * Created by Pavel_Verkhovtsov on 10/19/16.
  */
object GaussFilter extends PipelineStep{
  override def perform(baseImage: BufferedImage): BufferedImage = {baseImage } //TODO Maybe add

  override def requestParameters(frame: JFrame): Unit = ???
}
