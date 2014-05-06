import _root_.scalaz.{Failure, Success}
import com.stackmob.newman._
import com.stackmob.newman.dsl._
import com.stackmob.newman.response._
import net.liftweb.json._
import scala.concurrent._
import scala.concurrent.duration._
import java.net.URL
import UrlUtils._
import WordFreqCounter._

import scala.concurrent.ExecutionContext.Implicits.global


/**
 * Created by petr on 4.5.14.
 */

object wordfreq {

  def main(args: Array[String]) = {

    val timeout = 20 seconds

    implicit val httpClient = new ApacheHttpClient

    val urls = args.toList.map(setDestinationCodeToUrl(_))

    val requests = urls.map(url => GET(new URL(url)))

    val responses = requests.map {
      r: HeaderBuilder =>
        r.apply.map {
          resp =>
            r.toRequest -> resp
        }
    }.toList

    val resp = Future.sequence(responses)

    val reqResult = Await.result(resp, timeout)

    val results = for {
      res <- reqResult
      (req, resp) = res
      if resp.code == HttpResponseCode.Ok
      code = getDestinationCodeFromUrl(req.url.toString)
      jsonBody = resp.bodyAs[JValue]
      if jsonBody.isSuccess
      text = jsonBody match {
        case Success(v) => v \\ "text"
        case _ => ???
      }
      //removal of json structure
      descriptions = compact(render(text)).replaceAll("\\{?\"text\"\\:\"", "").replaceAll("\"\\}", "")
    } yield (code, descriptions)

    results foreach {
      res =>
        val freqs = calculateWordFreq(res._2)
        prettyPrint(res._1, freqs)
        println
    }
  }
}
