package by.verkpavel.grafolnet.database.dao

import by.verkpavel.grafolnet.database.domain.Sample
import com.mongodb.casbah.Imports._
import com.novus.salat.dao.SalatDAO

class SampleDAO extends SalatDAO[Sample, ObjectId](collection = MongoConnection()("grapholDB")("samples"))
