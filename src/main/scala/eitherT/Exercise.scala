package eitherT

import cats.implicits.catsSyntaxApplicativeError
import eitherT.Database._
import eitherT.EmailService.emailJobOwner
import io.circe.literal._
import io.circe.{DecodingFailure, Json}

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object Exercise extends App {

 val json : Json =
    json"""{
          "id": 123,
          "userId": 141,
          "status": "Done"
          }"""



  val result: Either[DecodingFailure, Boolean] = (for {
    dao <- json.as[JobStatus]
    email <- Right(Await.result(lookupEmail(dao.userId), 2 seconds))
    _ <- Right(Await.result(emailJobOwner(email.get), 2 seconds))
    r <- Right(Await.result(writeResultToJobRepo(dao), 2 seconds))
    } yield r
    ).recoverWith {
    case e => Left(e)
  }



  println(s"Ran job 123 with result: $result")
}
