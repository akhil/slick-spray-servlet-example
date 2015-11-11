package db

import org.joda.time.DateTime

/**
 * Created by akhil on 11/10/15.
 */
case class Article(id: Option[Int], title: String, content: String, createdAt: DateTime)

case class Comment(articleId: Int, content: String, createdAt: DateTime)
