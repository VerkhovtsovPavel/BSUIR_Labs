package by.pavelverk.hardwrite.core

import scala.concurrent.Future

class InMemoryStorage[Key, Value <: {val id: Key}] {
  private var state: Seq[Value] = Nil

  def getAll(): Future[Seq[Value]] =
    Future.successful(state)

  def get(id: Key): Future[Option[Value]] =
    Future.successful(state.find(_.id == id))

  def save(value: Value): Future[Value] =
    Future.successful {
      state = state.filterNot(_.id == value.id)
      state = state :+ value
      value
    }

  def find(query: Value => Boolean): Future[Option[Value]] = {
    Future.successful(state.find(query))
  }
}
