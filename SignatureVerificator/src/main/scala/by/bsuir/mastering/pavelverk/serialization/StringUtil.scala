package by.bsuir.mastering.pavelverk.serialization


object StringUtil {
   def generateRandomString(length : Int, mode : Mode) : String = {
        val buffer = new StringBuffer()
        val chars = mode match{
            case ALPHA              => "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            case ALPHANUM           => "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
            case MULTILINEALPHANUM  => "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890\n"
            case NUMERIC            => "1234567890"
        }
        val charsLength = chars.length()
        for (i <- 0 until length) {
            val index : Int = (Math.random() * charsLength).toInt
            buffer.append(chars.charAt(index))
        }
        buffer.toString
    }
}

sealed trait Mode
case object ALPHA extends Mode
case object ALPHANUM extends Mode
case object NUMERIC extends Mode
case object MULTILINEALPHANUM extends Mode