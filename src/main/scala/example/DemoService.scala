package example

/**
 * Created by akhil on 8/18/15.
 */

import spray.routing.HttpService

import scala.concurrent.duration._
import akka.io.Tcp
import akka.actor._
import spray.http._
import MediaTypes._
import HttpMethods._

trait DemoRoute extends HttpService {

  val demoRoute = {
    path("ping") {
      complete("pong")
    }
  } ~
  path("articles") {
    complete("TODO: list of articles")
  } ~
  pathPrefix("article" / IntNumber) { articleId =>
    get {
      complete(s"Article content for $articleId")
    } ~
    put {
      complete(s"article $articleId updated")
    }
  } ~ path("index" | "") {
    complete(index)
  }

  lazy val index =
      <html>
        <body>
          <h1>Say hello to <i>spray-servlet</i>!</h1>
          <p>Defined resources:</p>
          <ul>
            <li><a href="/ping">/ping</a></li>
            <li><a href="/articles">articles</a></li>
          </ul>
        </body>
      </html>

}


class DemoActor extends Actor with DemoRoute with ActorLogging {
  override def actorRefFactory: ActorRefFactory = context
  override def receive = runRoute(demoRoute)
}