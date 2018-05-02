package me.archdev.restapi.core.sample

import me.archdev.restapi.core.{Sample, UserProfile, UserProfileUpdate}
import me.archdev.restapi.utils.MonadTransformers._

import scala.concurrent.{ExecutionContext, Future}

class SampleService(sampleStorage: SampleStorage)(implicit executionContext: ExecutionContext) {

  def getSample(id: String): Future[Option[Sample]] =
    sampleStorage.getSample(id)

  def createSample(sample: Sample): Future[Sample] =
    sampleStorage.saveSample(sample)
}
