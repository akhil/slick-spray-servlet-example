package example

/**
 * Created by akhil on 8/18/15.
 */
import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http
import spray.servlet.WebBoot
import spray.http._

// This class is instantiated by the servlet initializer.
// It can either define a constructor with a single
// `javax.servlet.ServletContext` parameter or a
// default constructor.
// It must mplement the spray.servlet.WebBoot trait.
class Boot extends WebBoot {

  // we need an ActorSystem to host our application in
  val system = ActorSystem("example")

  // the service actor replies to incoming HttpRequests
  override val serviceActor = system.actorOf(Props[DemoActor])

  //IO(Http) ! Http.Bind(demoActor, "0.0.0.0", 8080)

  system.registerOnTermination {
    // put additional cleanup code here
    system.log.info("Application shut down")
  }
}
