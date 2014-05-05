import org.scalatest.{Matchers, FlatSpec}
import scala.collection.mutable
import scala.io.Source

/**
 * Created by petr on 4.5.14.
 */
class WordFreqSpec extends FlatSpec with Matchers {

  val sentence1 = "It is really really nice day but it's so cold there. So whats up up up?"
  val result1 = Map("it" -> 1, "is" -> 1, "really" -> 2, "nice" -> 1, "day" -> 1, "but" -> 1, "it" -> 2,
    "so" -> 2, "cold" -> 1, "there" -> 1, "whats" -> 1, "up" -> 3, "s" -> 1)

  val sentence2 = "We’ve mentioned in past posts that we use Scala extensively at StackMob," +
    " and as you can see based on our product, we use HTTP extensively, both for our internal and " +
    "external facing infrastructure.Since Scala and HTTP are critical to our success, we " +
    "need a good HTTP client built for Scala that is easy to read, easy to use, and is reasonably performant."

  val result2Path = "src/test/resources/data/result2.dat"
  val result2 = loadResultMap(result2Path)

  def loadResultMap(path: String): Map[String, Int] = {
    val result = mutable.Map[String, Int]()

    val lines = Source.fromFile(result2Path).getLines()
    lines.foreach {
      line =>
        val split = line.split("\\s+")
        result(split(1)) = split(0).toInt
    }

    return result.toMap
  }

  "'sentence1'" should "return 'result1'" in {
    val result = WordFreqCounter.calculateWordFreq(sentence1)
    result shouldEqual result1
  }

  "'sentence2'" should "return 'result2' from 'result2.dat'" in {
    val result = WordFreqCounter.calculateWordFreq(sentence2)
    result shouldEqual result2
  }

  "Empty sentence" should "return empty word map" in {
    val result = WordFreqCounter.calculateWordFreq("")
    result shouldEqual Map()
  }

  "PrettyPrint" should "looks like in document" in {
    WordFreqCounter.prettyPrint("123456", result1)
  }

}
