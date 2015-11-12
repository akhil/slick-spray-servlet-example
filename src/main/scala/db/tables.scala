package db

import com.github.tototoshi.slick.{GenericJodaSupport, H2JodaSupport}
import org.joda.time.DateTime
import slick.driver.H2Driver

/**
 * Created by akhil on 11/10/15.
 */

trait Tables {
  val profile: slick.driver.JdbcProfile
  val jodaSupport: GenericJodaSupport
  import profile.api._
  import jodaSupport._

  class Articles(tag: Tag) extends Table[Article](tag, "ARTICLES") {
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

    def title = column[String]("TITLE")

    def content = column[String]("CONTENT")

    def createdAt = column[DateTime]("CREATED_AT")

    def * = (id.?, title, content, createdAt) <> (Article.tupled, Article.unapply)
  }

  val articles = TableQuery[Articles]

  class Comments(tag: Tag) extends Table[Comment](tag, "COMMENT") {
    def articleId = column[Int]("ARTICLE_ID", O.PrimaryKey)

    def content = column[String]("CONTENT")

    def createdAt = column[DateTime]("CREATED_AT")

    val article = foreignKey("ARTICLE_ID_FK", articleId, articles)(_.id)

    def * = (articleId, content, createdAt) <> (Comment.tupled, Comment.unapply)
  }

  val comments = TableQuery[Comments]


}