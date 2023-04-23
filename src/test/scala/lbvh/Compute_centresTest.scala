package lbvh

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Compute_centresTest extends AnyFlatSpec with ChiselScalatestTester {
  "Compute_centresTest" should "pass test" in {
    test(new Compute_centres).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      dut.io.input_data.p0.x.poke("h3f7d70a4".U) // 0.99
      dut.io.input_data.p1.x.poke("h3f7d70a4".U)
      dut.io.input_data.p2.x.poke("h3f7d70a4".U)
      dut.clock.step(50)
    }
  }
}
