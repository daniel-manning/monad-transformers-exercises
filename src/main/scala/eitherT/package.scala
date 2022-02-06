import io.circe.Decoder
import scala.language.implicitConversions

package object eitherT {

  import io.estatico.newtype.macros._

  @newtype case class UserId(value: Int)

  object UserId {
    implicit val useridDecoder: Decoder[UserId] = deriving
  }

  @newtype case class Email(value: String)

}
