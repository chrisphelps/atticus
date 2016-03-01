import com.twitter.finagle.Http
import com.twitter.util.Await

import io.finch._
import io.finch.circe._
import io.circe.generic.auto._

object Beans extends App {

  val cool: Endpoint[String] = get("cool") 
    { Ok("Beans")}

  val hello: Endpoint[String] = get("hello") 
    { Ok("Hello, World!") }

  val echo: Endpoint[String] = get("echo" :ec: param("name")) 
    { name:String => Ok(s"Repeat $name")}

  case class Greeting(hello: String, name: String)

  val greet: Endpoint[String] = post("greet" :: body.as[Greeting]) 
    { greeting: Greeting => Ok(s"Here's your ${greeting.hello} ${greeting.name}")}

  case class User(id: Long, name: String, email: String)
  //case class PartialUser(name: String, email: String)

  val fulluser: Endpoint[User] =
    body.as[Long => User].map(_(5l))

  val makeUser: Endpoint[User] = post("user" :: fulluser)
    { puser: User => Created(puser)}

  val api = (cool :+: hello :+: echo :+: greet :+: makeUser).toService

  Await.ready(Http.server.serve(":8080", api))
}
