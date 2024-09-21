package snunit

import zio.*
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.zserver.ZServerEndpoint

trait ZioTapirApp extends ZioAppDefault {
  def serverEndpoints: IOLayer[IO, List[ZServerEndpoint[Any, IO]]]

  override def run = serverEndpoints.use { se =>
    tapir.SNUnitServerBuilder
      .default[IO]
      .withServerEndpoints(se)
      .run
  }
}
