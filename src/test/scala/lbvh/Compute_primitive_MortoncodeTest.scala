package lbvh

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Compute_primitive_MortoncodeTest
    extends AnyFlatSpec
    with ChiselScalatestTester {
  "Computer_primitive_MortoncodeTest" should "pass test" in {
    test(new Compute_primitive_Morton)
      .withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
        dut.io.input_centres.x.poke("hBFC00000".U) // -0.5
        dut.io.input_centres.y.poke("h3FC00000".U) // 1.5
        dut.io.input_centres.z.poke("h3F800000".U) //

        dut.io.input_grid_dim.x.poke(3.U) // 3
        dut.io.input_grid_dim.y.poke(3.U)
        dut.io.input_grid_dim.z.poke(3.U)

        dut.io.input_global_bbox.maxPoint.x.poke("h40000000".U) // 2
        dut.io.input_global_bbox.maxPoint.y.poke("h40000000".U) //
        dut.io.input_global_bbox.maxPoint.z.poke("h3F800000".U) // 1

        dut.io.input_global_bbox.minPoint.x.poke("hC0000000".U) // -2
        dut.io.input_global_bbox.minPoint.y.poke("hC0000000".U) //
        dut.io.input_global_bbox.minPoint.z.poke("h3F800000".U) //

        dut.clock.step(5)

        dut.io.input_centres.x.poke("h3FC00000".U) // 1.5
        dut.io.input_centres.y.poke("h3F000000".U) // 0.5
        dut.io.input_centres.z.poke("h3F800000".U) //
        dut.clock.step(1)

        dut.io.input_centres.x.poke("h3F000000".U) // 0.5
        dut.io.input_centres.y.poke("h3F000000".U) // 0.5
        dut.io.input_centres.z.poke("h3F800000".U) //

        dut.clock.step(30)

      }
  }
}
