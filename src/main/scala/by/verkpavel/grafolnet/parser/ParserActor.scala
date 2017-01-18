package by.verkpavel.grafolnet.parser

import java.awt.Image

import akka.actor.{ Actor, Props }

object ParserActor {
  def props: Props = Props[ParserActor]
  def name = "parser"
}

class ParserActor extends Actor {

  def receive = {
    case ParseRequest("angel", image) => 15.6
    case ParseRequest("density", image) => 0.3
  }

}

case class ParseRequest(parameter: String, image: Image)

