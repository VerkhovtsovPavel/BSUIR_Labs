package by.pavelverk.hardwrite.core.sample

import by.pavelverk.hardwrite.core.{InMemoryStorage, Sample, UserId}
import by.pavelverk.hardwrite.utils.db.DatabaseConnector

import scala.concurrent.{ExecutionContext, Future}

sealed trait SampleStorage {
  def getAllSamples(userId: UserId):Future[Seq[Sample]]
  def getSample(id: String): Future[Option[Sample]]

  def saveSample(sample: Sample): Future[Sample]
}

class JdbcSampleStorage(val databaseConnector: DatabaseConnector)(implicit executionContext: ExecutionContext)
  extends SampleTable with SampleStorage {

  import databaseConnector._
  import databaseConnector.profile.api._

  def getSample(id: String): Future[Option[Sample]] = db.run(samples.filter(_.id === id).result.headOption)

  def saveSample(sample: Sample): Future[Sample] =
    db.run(samples.insertOrUpdate(sample)).map(_ => sample)

  def getAllSamples(userId: UserId): Future[Seq[Sample]] = db.run(samples.filter(_.user_id === userId).result)
}

class InMemorySampleStorage extends InMemoryStorage[String, Sample] with SampleStorage {

  override def getSample(id: String): Future[Option[Sample]] = get(id)

  override def getAllSamples(userId: UserId): Future[Seq[Sample]] = getAll()

  override def saveSample(sample: Sample): Future[Sample] = save(sample)

}