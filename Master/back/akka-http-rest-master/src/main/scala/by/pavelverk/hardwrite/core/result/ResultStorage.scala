package by.pavelverk.hardwrite.core.result

import by.pavelverk.hardwrite.core.{InMemoryStorage, Result, SampleId}
import by.pavelverk.hardwrite.utils.db.DatabaseConnector

import scala.concurrent.{ExecutionContext, Future}

sealed trait ResultStorage {

  def getResult(id: SampleId): Future[Option[Result]]

  def saveResult(result: Result): Future[Result]
}

class JdbcResultStorage(val databaseConnector: DatabaseConnector)(implicit executionContext: ExecutionContext)
  extends ResultTable with ResultStorage {

  import databaseConnector._
  import databaseConnector.profile.api._

  def getResult(id: SampleId): Future[Option[Result]] = db.run(results.filter(_.id === id).result.headOption)

  def saveResult(result: Result): Future[Result] =
    db.run(results.insertOrUpdate(result)).map(_ => result)

}

class InMemoryResultStorage extends InMemoryStorage[SampleId, Result] with ResultStorage {
  override def getResult(id: SampleId): Future[Option[Result]] = get(id)

  override def saveResult(result: Result): Future[Result] = save(result)
}