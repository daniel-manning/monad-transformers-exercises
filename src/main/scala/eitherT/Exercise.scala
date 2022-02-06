package eitherT

import cats.data.EitherT
import eitherT.Database._
import eitherT.EmailService.emailJobOwner
import io.circe.literal._
import io.circe.{DecodingFailure, Json}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.language.postfixOps

object Exercise extends App {

 val json : Json =
    json"""{
          "id": 123,
          "userId": 141,
          "status": "Done"
          }"""



  val jobRunner = (for {
    dao <- EitherT(Future(json.as[JobStatus]))
    email <- EitherT[Future, DecodingFailure, Email](lookupEmail(dao.userId).map(_.toRight[DecodingFailure](throw new Exception())))
    _ <- EitherT[Future, DecodingFailure, Boolean](emailJobOwner(email).map(a => Right(a)))
    r <- EitherT[Future, DecodingFailure, Boolean](writeResultToJobRepo(dao).map(a => Right(a)))
    } yield r
    ).value

  val result = Await.result(jobRunner, 5.seconds)


  println(s"Ran job 123 with result: $result")
}
