package config

import chisel3._
import lbvh.VectorModule

object Configs {
  val InputFileName = "src/main/scala/lbvh/1.txt"
  val DEPTH = 108
  val DATA_WIDTH = 32
  val ADDR_WIDTH = 32
  val Morton_WIDTH = 6
  val Morton_WIDTH_by_per_axis = 2

}
