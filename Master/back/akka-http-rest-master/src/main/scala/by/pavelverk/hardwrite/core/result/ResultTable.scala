package by.pavelverk.hardwrite.core.result

import by.pavelverk.hardwrite.core.{Result, SampleId}
import by.pavelverk.hardwrite.utils.db.DatabaseConnector

private[result] trait ResultTable {

  protected val databaseConnector: DatabaseConnector
  import databaseConnector.profile.api._

  class Results(tag: Tag) extends Table[Result](tag, "results") {
    def id = column[SampleId]("id", O.PrimaryKey)
    def neuro_results = column[Double]("neuro_results")
    def psicho_result = column[Int]("psicho_result")

    def * = (id, neuro_results, psicho_result) <> ((Result.apply _).tupled, Result.unapply)
  }

  protected val results = TableQuery[Results]
}
