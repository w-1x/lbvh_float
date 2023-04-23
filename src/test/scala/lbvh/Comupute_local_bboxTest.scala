package lbvh

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Compute_local_bboxTest extends AnyFlatSpec with ChiselScalatestTester {
  "Compute_local_bboxTest" should "pass test" in {
    test(new Compute_local_bbox).withAnnotations(Seq(WriteVcdAnnotation)) {
      dut =>
        dut.io.input_data.p0.x.poke("h3f7d70a4".U) // 0.99
        dut.io.input_data.p1.x.poke(1067450368.U) // 1.25
        dut.io.input_data.p2.x.poke("hc2493333".U) // -50.3
        dut.clock.step(1)
    }
  }
}
