package com.vmware.hello

import javax.sql.DataSource

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import HomeController._
import org.cloudfoundry.runtime.env._
import org.springframework.ui.Model

import org.springframework.beans.factory.annotation._

import scala.collection._

@Controller
class HomeController {
  val env = new CloudEnvironment

  @Autowired var dataSource: DataSource = null
  /**
   * Simply selects the home view to render by returning its name.
   */
  @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
  def home(model: mutable.Map[String, String]): String = {
    model("dataSource") = dataSource.getConnection.toString

    logger.info("Welcome home from " + env.getInstanceInfo().getHost() + ":" + env.getInstanceInfo().getPort())
    "home"
  }

}

object HomeController {
  val logger = LoggerFactory.getLogger(classOf[HomeController])
}

class ColorString(val str: String) {
  def red = str + "red"
  
  val num: Int = 5
  
  val odd = if (num % 2 == 0) {
    false
  } else {
    true
  }
  
  val list = List(1,2,3)
  val doubleList = list.map(x => x*2)
}

object ColorString {
  implicit def x(orig: String) : ColorString = { new ColorString(orig) }
}

import ColorString._

object Demo {
  "xxx".red
}