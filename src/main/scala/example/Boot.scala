package example

/**
 * Created by akhil on 8/18/15.
 */
import akka.actor.{Props, ActorSystem}
import akka.io.IO
import com.github.tototoshi.slick.H2JodaSupport
import db.{Article, Tables}
import org.joda.time.DateTime
import spray.can.Http
import spray.servlet.WebBoot

import scala.concurrent.Await
import scala.concurrent.duration.Duration

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

object Main extends App {
  val tables = new Tables {
    override val profile = slick.driver.H2Driver
    override val jodaSupport = H2JodaSupport
  }
  import tables.profile.api._

  val DB: Database = Database.forConfig("h2mem1")

  val ddl = DBIO.seq(
    (tables.articles.schema ++ tables.comments.schema).create
  )
  Await.result(DB.run(ddl), Duration.Inf)

  println("Created tables ---")
  val as = DBIO.seq(
    tables.articles += Article(None, "test", "test", DateTime.now),
    tables.articles += Article(None, "test1", "test1", DateTime.now)
  )
  Await.result(DB.run(as), Duration.Inf)
  println("Inserted .....")

  val getArticle = Compiled { title: Rep[String] =>
    tables.articles.filter(_.title === title)
  }

  val a = Await.result(DB.run(getArticle("test").result), Duration.Inf)

  println(a)

}