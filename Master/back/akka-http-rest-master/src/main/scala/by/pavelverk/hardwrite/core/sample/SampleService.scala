package by.pavelverk.hardwrite.core.sample

import by.pavelverk.hardwrite.core.{Sample, UserId}

import scala.concurrent.{ExecutionContext, Future}

class SampleService(sampleStorage: SampleStorage)(implicit executionContext: ExecutionContext) {

  def getAllSamples(userId: UserId) = sampleStorage.getAllSamples(userId)

  def getSample(id: String): Future[Option[Sample]] =
    sampleStorage.getSample(id)

  def createSample(sample: Sample): Future[Sample] =
    sampleStorage.saveSample(sample)
}
