package lbvh

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class RegTest extends AnyFlatSpec with ChiselScalatestTester {
  "RegTest" should "pass test" in {
    test(new A).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      dut.clock.step(20)
    }
  }
}
