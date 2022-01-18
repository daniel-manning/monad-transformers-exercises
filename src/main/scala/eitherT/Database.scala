package eitherT

import scala.concurrent.Future

object Database {

  def writeResultToJobRepo(jobStatus: JobStatus): Future[Boolean] = {
    Thread.sleep(1000)
    Future.successful(true)
  }

}
