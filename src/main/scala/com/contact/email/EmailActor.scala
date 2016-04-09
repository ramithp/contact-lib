package com.contact.email

import akka.actor.ActorLogging
import akka.actor.Actor
import scala.util.Failure
import scala.util.Success

sealed trait emailResponse
case object successEmail extends emailResponse
case object failureEmail extends emailResponse

case class sendGMail(fromID: String, fromPassword: String, toID: String, subject: String, body: String)

class EmailActor extends Actor with ActorLogging {
  def receive = {
    case msg: sendGMail =>
      import courier._, Defaults._
      val mailer = Mailer("smtp.gmail.com", 587)
        .auth(true)
        .as(msg.fromID, msg.fromPassword)
        .debug(true)
        .startTtls(true)()

      //Extract just the email-id for api call
      val Array(fromEmailID, domain) = msg.fromID.split("@").map(_.trim())

      mailer(Envelope.from(fromEmailID `@` "gmail.com")
        .to(msg.toID `@` "gmail.com")
        .subject(msg.subject)
        .content(Text(msg.body)))
        .onComplete {
          case Success(value) =>
            log.info("Successful email sent")
            sender ! successEmail
          case Failure(e) =>
            log.info("Failure in sending email")
            sender ! failureEmail
        }
      
    case _ => log.warning("You sent me a random message")
  }
}