package play.api

import org.slf4j.{ LoggerFactory, Logger => Slf4jLogger }

/**
 * A Play logger.
 *
 * @param logger the underlying SL4FJ logger
 */
class Logger(val logger: Slf4jLogger) extends LoggerLike

/**
 * High-level API for logging operations.
 *
 * For example, logging with the default application logger:
 * {{{
 * Logger.info("Hello!")
 * }}}
 *
 * Logging with a custom logger:
 * {{{
 * Logger("my.logger").info("Hello!")
 * }}}
 *
 */
object Logger extends LoggerLike {

  println("Play logs now directly handled by this application server")

  /**
   * Initialize the Logger in a brut way.
   */
  def init(home: java.io.File) {
    Logger.configure(
      Map("application.home" -> home.getAbsolutePath),
      Map.empty,
      Mode.Test)
  }

  /**
   * The 'application' logger.
   */
  val logger = LoggerFactory.getLogger("application")

  /**
   * Obtains a logger instance.
   *
   * @param name the name of the logger
   * @return a logger
   */
  def apply(name: String): Logger = new Logger(LoggerFactory.getLogger(name))

  /**
   * Obtains a logger instance.
   *
   * @param clazz a class whose name will be used as logger name
   * @return a logger
   */
  def apply[T](clazz: Class[T]): Logger = new Logger(LoggerFactory.getLogger(clazz))

  /**
   * Reconfigures the underlying logback infrastructure.
   *
   * @param configFile the logback configuration file
   * @param properties these properties will be added to the logger context (for example `application.home`)
   * @see http://logback.qos.ch/
   */
  def configure(properties: Map[String, String] = Map.empty, levels: Map[String, ch.qos.logback.classic.Level] = Map.empty, mode: Mode.Value) {


  }

  /**
   * Shutdown the logger infrastructure.
   */
  def shutdown() {

  }


}

