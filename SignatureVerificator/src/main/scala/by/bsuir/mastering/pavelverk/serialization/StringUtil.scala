package by.bsuir.mastering.pavelverk.serialization

object StringUtil {
   def generateRandomString(length : Int, mode : Mode) : String = {
        val buffer = new StringBuffer()
        val chars = mode.value
        val charsLength = chars.length()
        for (i <- 0 until length) {
            val index : Int = (Math.random() * charsLength).toInt
            buffer.append(chars.charAt(index))
        }
        buffer.toString
    }
}

sealed class Mode(val value : String)
case object ALPHA extends Mode("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
case object NUMERIC extends Mode("1234567890")
case object ALPHANUM extends Mode(ALPHA.value+NUMERIC.value)
case object MULTILINEALPHANUM extends Mode(ALPHANUM.value+"\n")