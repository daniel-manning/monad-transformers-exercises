package readerT

import cats.Id
import cats.data.ReaderT
import org.joda.time.DateTime
import org.joda.time.format._

import scala.xml.NodeSeq

object Exercise extends App {

  val fmt = DateTimeFormat.forPattern("dd-MM-yyyy")

  val testData = <xml>
    <user>1233</user>
    <creationDate>17-3-2005</creationDate>
  </xml>

  def validateUser: ReaderT[Id, NodeSeq, Boolean] =
    ReaderT[Id, NodeSeq, Boolean](xml =>
      (xml \ "user").text.nonEmpty
    )

  def validateCreationDate: ReaderT[Id, NodeSeq, Boolean] =
    ReaderT[Id, NodeSeq, Boolean] { xml =>
      val dateS = (xml \ "creationDate").text
      val startDate = DateTime.parse("25-02-2005", fmt)

      fmt.parseDateTime(dateS).isAfter(startDate)
    }

  val validate: ReaderT[Id, NodeSeq, Boolean] = for {
    user <- validateUser
    date <- validateCreationDate
  } yield user && date

  val result = validate(testData)
  println(s"The result of validating the xml is $result")
}
