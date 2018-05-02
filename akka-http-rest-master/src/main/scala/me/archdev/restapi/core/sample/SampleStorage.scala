package me.archdev.restapi.core.sample

import me.archdev.restapi.core.{InMemoryStorage, Sample, UserProfile}
import me.archdev.restapi.utils.db.DatabaseConnector

import scala.concurrent.{ExecutionContext, Future}

sealed trait SampleStorage {

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

}

class InMemoryUserProfileStorage extends InMemoryStorage[String, Sample] with SampleStorage {

  override def getSample(id: String): Future[Option[Sample]] = get(id)

  override def saveSample(sample: Sample): Future[Sample] = save(sample)

}