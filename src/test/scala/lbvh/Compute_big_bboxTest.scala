package lbvh

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Compute_big_bboxTest extends AnyFlatSpec with ChiselScalatestTester {
  "My_compute_big_bboxTest" should "pass test" in {
    test(new Compute_bigger_bbox).withAnnotations(Seq(WriteVcdAnnotation)) {
      dut =>
        dut.io.input_bbox1.minPoint.x.poke("h7f7fffff".U)
        dut.io.input_bbox2.minPoint.x.poke("h80800000".U)

        dut.clock.step(5)
    }
  }
}
