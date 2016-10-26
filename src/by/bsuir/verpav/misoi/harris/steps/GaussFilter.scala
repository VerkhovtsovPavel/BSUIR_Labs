package by.bsuir.verpav.misoi.harris.steps

import java.awt.image.BufferedImage
import javax.swing.JFrame

import by.bsuir.verpav.misoi.pipeline.PipelineStep

/**
  * Created by Pavel_Verkhovtsov on 10/19/16.
  */
object GaussFilter extends PipelineStep{

  val baseRadius = 1

  override def perform(baseImage: BufferedImage): BufferedImage = {

    val differences = stepContext.get("difference").get.asInstanceOf[Array[Array[(Double, Double, Double)]]]
    val radius = params.getOrElse("Radius", baseRadius)

    val filter = makeKernel(radius)

    for (y <- 0 until differences(0).length) {
      for ( x <- 0 until differences.length) {
        var nDx  = 0.0
        var nDy  = 0.0
        var nDxy = 0.0
        var index = 0
        for(dy <- -radius to radius) {
          for(dx <- -radius to radius) {
            val xk = x + dx
            val yk = y + dy
            if (!(xk < 0 || xk >= differences(0).length || yk < 0 || yk >= differences.length)) {

              val cv = differences(xk)(yk)
              val f = filter(dx+radius)(dy+radius)

              nDx += cv._1 * f
              nDy += cv._2 * f
              nDxy += cv._3 * f
            }
            index += 1
          }
        }
        differences(x)(y) = (nDx, nDy, nDxy)
      }
    }

    stepContext.put("gaussDifference", differences)
    baseImage }//TODO Maybe add

  private def makeKernel(r : Int) = {
    val filter = Array.ofDim[Double](2*r+1, 2*r+1)
    var filtersum = 0.0
    for(j<- -r to r) {
      for(i <- -r to r) {
        val g = gaussian(i,j,r)
        filter(i+r)(j+r)=g
        filtersum+=g
      }
    }
    filter.map(_.map(_ / filtersum))
  }

  private def gaussian(x : Int, y : Int, radius : Int) = {
    val sigma = radius.toDouble/3
    val sigma2 = sigma*sigma
    val t = (x*x+y*y)/(2*sigma2)
    val u = 1.0/(2*Math.PI*sigma2)
    u*Math.exp( -t )
  }

  override def requestParameters(frame: JFrame): Unit = ???
}
