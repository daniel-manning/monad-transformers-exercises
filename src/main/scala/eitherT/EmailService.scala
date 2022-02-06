package eitherT

import scala.concurrent.Future

object EmailService {

 def emailJobOwner(email: Email): Future[Boolean] = {
    Thread.sleep(1000)
    Future.successful(true)
  }

}
