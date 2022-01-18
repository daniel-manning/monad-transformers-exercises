package eitherT

import scala.concurrent.Await
import io.circe.{Json, parser}
import Database._
import cats.implicits.catsSyntaxApplicativeError

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps
import io.circe.literal._

object Exercise extends App {

 val json : Json =
    json"""{
          "id": 123,
          "status": "Done"
          }"""

  val result = (for {
    dao <- json.as[JobStatus]
    r <- Right(Await.result(writeResultToJobRepo(dao), 2 seconds))
    } yield r
    ).recoverWith {
    case e => Left(e)
  }

}
