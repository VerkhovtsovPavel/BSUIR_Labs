package by.pavelverk.hardwrite.core.result

import java.util.Random

import by.pavelverk.hardwrite.core.{Result, Sample, SampleId}

import scala.concurrent.{ExecutionContext, Future}

class ResultService(resultStorage: ResultStorage)(implicit executionContext: ExecutionContext) {

  def getResult(id: SampleId): Future[Option[Result]] =
    resultStorage.getResult(id)

  def saveResult(result: Result): Future[Result] =
    resultStorage.saveResult(result)

  def getNeuroAnalysis(sampleId: SampleId)  = calculateNeuroAnalysis(sampleId).flatMap(saveResult)

  def getPsychoAnalysis(sampleId: SampleId)  = calculatePsycoAnalysis(sampleId).flatMap(saveResult)

  def getCyberSecAnalysis(sampleId: SampleId): Future[Boolean] = calculateCyberSecAnalysis(sampleId)


  private def calculateNeuroAnalysis(sampleId: SampleId): Future[Result] =
    getResult(sampleId).map {
       case Some(result) => Result(sampleId, 0.5, result.psicho_result)
       case None => Result(sampleId, 0.5, 0)
    }

  private def calculatePsycoAnalysis(sampleId: SampleId): Future[Result] =
    getResult(sampleId).map {
      case Some(result) => Result(sampleId, result.neuro_results, 12)
      case None => Result(sampleId, 0.0, 12)
    }


  private def calculateCyberSecAnalysis(sampleId: SampleId): Future[Boolean] = Future(true)
}
