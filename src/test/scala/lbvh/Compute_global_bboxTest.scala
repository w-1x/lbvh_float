package lbvh

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Compute_global_bboxTest extends AnyFlatSpec with ChiselScalatestTester {
  "My_compute_global_bboxTest" should "pass test" in {
    test(new Compute_global_bbox).withAnnotations(Seq(WriteVcdAnnotation)) {
      dut =>
        dut.io.input_bbox1.maxPoint.x.poke("h44DF3333".U) // 1785.6
        dut.io.input_bbox1.minPoint.x.poke("h40600000".U) // 3.5
        dut.clock.step()

        dut.io.input_bbox1.maxPoint.x.poke("h41233333".U) // 10.2
        dut.io.input_bbox1.minPoint.x.poke("hC2720000".U) // -60.5
        dut.clock.step()

        dut.io.input_bbox1.maxPoint.x.poke("h3F47AE14".U) // 0.78
        dut.io.input_bbox1.minPoint.x.poke("hBF4CCCCC".U) // -0.8
        dut.clock.step()

        dut.io.input_bbox1.maxPoint.x.poke("h45134E66".U) // 2356.9
        dut.io.input_bbox1.minPoint.x.poke("hC2CF9999".U) // -103.8
        dut.clock.step(2)

    }
  }
}
