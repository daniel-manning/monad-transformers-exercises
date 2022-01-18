package eitherT

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

sealed trait Status
case object Waiting extends Status
case object Working extends Status
case object Done extends Status

case class JobStatus(id: Int, status: Status)

object JobStatus {
  implicit val statusDecoder: Decoder[Status] = deriveDecoder[Status]
  implicit val jobStatusDecoder: Decoder[JobStatus] = deriveDecoder[JobStatus]
}