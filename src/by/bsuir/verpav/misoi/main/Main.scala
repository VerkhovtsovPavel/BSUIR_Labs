package by.bsuir.verpav.misoi.main

import java.io.File
import javax.imageio.ImageIO

import by.bsuir.verpav.misoi.histogram.SimpleHistogram.|-|-|
import by.bsuir.verpav.misoi.ui.MainForm

/**
  * Created by Pavel_Verkhovtsov on 9/14/16.
  */
object Main extends App{

  MainForm()

//  val data = extractColors("./BSUIR45.jpg").map(rgb => (rgb._1+rgb._2+rgb._3) / 3)
//  val dataFrequencies = data.foldLeft(Map[Int, Int]())((map, word) => map + ((word, map.getOrElse(word, 0) + 1)))
//
//  |-|-|(dataFrequencies)

  def extractColors (imageName : String) = {
    val image = ImageIO.read(new File(imageName))
    for(x <- 0 until image.getWidth; y <- 0 until image.getHeight()) yield {
      val rgb = image.getRGB(x,y)
      ((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF)
    }
  }
}