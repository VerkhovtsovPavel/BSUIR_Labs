package by.pavelverk.hardwrite.core.feature

import by.pavelverk.hardwrite.core.{Features, Sample}

import scala.concurrent.{ExecutionContext, Future}

class FeaturesService(featuresStorage: FeaturesStorage)(implicit executionContext: ExecutionContext) {

  def getFeatures(id: String): Future[Option[Features]] =
    featuresStorage.getFeatures(id)

  def saveFeatures(features: Features): Future[Features] =
    featuresStorage.saveFeatures(features)

  def extractFeatures(sample: Sample) : Future[Features] =
      saveFeatures(calculateFeatures(sample))

  def calculateFeatures(sample: Sample) : Features = {
      Features("12", sample.id, 10.0, 3, 23.5, 3, 5, 8, 65.4, 23.4, 123.5,143.5, 12, 3, 24, 26, 54, 0.8)
    }
}