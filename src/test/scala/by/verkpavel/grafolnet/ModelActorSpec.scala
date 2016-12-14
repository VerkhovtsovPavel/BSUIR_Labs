package by.verkpavel.grafolnet

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import by.verkpavel.grafolnet.model.{Item, ModelActor}
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike}

class ModelActorSpec extends TestKit(ActorSystem()) with FlatSpecLike with ImplicitSender with BeforeAndAfterAll {

  import ModelActor._

  override def afterAll() {
    system.shutdown()
  }

  val model = TestActorRef(Props[ModelActor])

  "A Model" should "return a list of 5 ItemSummaries" in {
    model ! 'list
    val lst = expectMsgType[ItemSummaries]
    assert(lst.items.size === 5)
  }

  it should "return 3 items containing 'Qu'" in {
    model ! ('query, "Qu")
    val lst = expectMsgType[ItemSummaries]
    assert(lst.items.size === 3)
  }

  it should "return item 1 when asked" in {
    model ! 1
    val item = expectMsgType[Item]
    assert(item.id === 1)
    assert(item.title === "foo")
  }

  it should "return ItemNotFound when requested item is not found" in {
    model ! 10
    expectMsg(ItemNotFound)
  }

}
