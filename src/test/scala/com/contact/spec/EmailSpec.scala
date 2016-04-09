package com.contact.spec

import akka.testkit.TestKit
import akka.testkit.ImplicitSender
import akka.actor.Props
import com.contact.email.successEmail
import org.scalatest.BeforeAndAfterAll
import com.contact.email.EmailActor
import akka.testkit.DefaultTimeout
import org.scalatest.Matchers
import scala.util.Try
import scala.io.Source
import org.scalatest.WordSpecLike
import akka.actor.ActorSystem
import com.contact.email.sendGMail
import scala.concurrent.duration._

class EmailSpec extends TestKit(ActorSystem("TestKitUsageSpec")) with DefaultTimeout with ImplicitSender with WordSpecLike with BeforeAndAfterAll with Matchers {
  //You didn't think I was silly enough to put my password here did you?
  lazy val passwordFile = "/home/ra41p/workspace/TusharContact/ContactTusharLib/password"
  val emailActor = system.actorOf(Props(classOf[EmailActor], "testEmailActor"))
  val password =
    Try(Source.fromFile(passwordFile)
      .getLines()
      .toList(0))
      .toOption
      .getOrElse(throw new Exception("Create a non-empty password file noob"))

  override def afterAll = shutdown()
  
  "An EmailActor" should {
    "Respond with the a success message after sending e-mail" in {
      within(15 seconds) {
        emailActor ! sendGMail("tushar.emailer@gmail.com",
          password,
          "ramith.honeypot",
          "Hey, trying to contact you via contact-lib",
          "Test Email from contact-lib. I can contact you via e-mail now :D. -Ramith")
        expectMsg(successEmail)
      }
    }
  }
}