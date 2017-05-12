package by.verkpavel.grafolnet.database.dao

import by.verkpavel.grafolnet.database.domain.User
import com.mongodb.casbah.Imports._
import com.novus.salat.dao.SalatDAO

class UserDAO extends SalatDAO[User, ObjectId](collection = MongoConnection()("grapholDB")("users"))
