package eitherT

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

sealed trait Status
case object Waiting extends Status
case object Working extends Status
case object Done extends Status

object Status {
  def fromString(value: String): Status = value match {
    case "Waiting" => Waiting
    case "Working" => Working
    case "Done" => Done
    case _ => throw new Exception("Oh no!")
  }
}

case class JobStatus(id: Int, userId: UserId, status: Status)

object JobStatus {
  implicit val statusDecoder: Decoder[Status] = Decoder.decodeString.map(Status.fromString)

  implicit val jobStatusDecoder: Decoder[JobStatus] = deriveDecoder[JobStatus]
}