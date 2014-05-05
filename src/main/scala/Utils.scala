import scala.collection.mutable

/**
 * Created by petr on 4.5.14.
 */
object UrlUtils {
  val urlBegin = "http://concierge.top10.com/v1/destinations/"
  val urlEnd = "/hotels?hotel_attrs=id,name,description&count=20"

  def getDestinationCodeFromUrl(url: String): String =
    url.substring(urlBegin.length, url.length - urlEnd.length)

  def setDestinationCodeToUrl(code: String): String =
    urlBegin + code + urlEnd
}

object WordFreqCounter {

  def calculateWordFreq(sentence: String): Map[String, Int] = {
    if (sentence.trim.length == 0)
      Map()
    else
      sentence
        .toLowerCase //case insensitive
        .replaceAll("[\\(\\)\\{\\}[-:]/.,:;?!'\"’`\\\\]", " ") //removal of unwanted characters
        .split("\\s+")
        .foldLeft(mutable.Map[String, Int]()) {
        (m, w) =>
          m(w) = m.getOrElse(w, 0) + 1;
          m
      }.toMap
  }

  def prettyPrint(code: String, data: Map[String, Int]) = {
    val seq = data.toSeq.sortBy(-_._2)

    println(s"Destination $code")
    seq.foreach(l => println(s"${l._1}: ${l._2}"))
  }

}
