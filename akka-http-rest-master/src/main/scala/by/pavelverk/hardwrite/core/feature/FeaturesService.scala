package by.pavelverk.hardwrite.core.feature

import by.pavelverk.hardwrite.core.Features
import scala.concurrent.{ExecutionContext, Future}

class FeaturesService(featuresStorage: FeaturesStorage)(implicit executionContext: ExecutionContext) {

  def getFeatures(id: String): Future[Option[Features]] =
    featuresStorage.getFeatures(id)

  def createSample(sample: Features): Future[Features] =
    featuresStorage.saveFeatures(sample)
}