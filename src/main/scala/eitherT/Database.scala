package eitherT

import scala.concurrent.Future


object Database {

  def lookupEmail(userId: UserId): Future[Option[Email]] = {
    Thread.sleep(1000)
    Future.successful(Option(Email("not-real@test.com")))
  }

  def writeResultToJobRepo(jobStatus: JobStatus): Future[Boolean] = {
    Thread.sleep(1000)
    Future.successful(true)
  }

}
