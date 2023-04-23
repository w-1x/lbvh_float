package hardfloat

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Float_CMP_leTest extends AnyFlatSpec with ChiselScalatestTester {
  "My_FloatCMP_le" should "pass" in {
    test(new Float_CMP_le()).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      // dut.io.a.poke(1067450368.U) // 1.25
      // dut.io.b.poke(1045220556.U) // 0.2

      dut.io.a.poke(1093245337.U) // 10.6
      dut.io.b.poke("hc2493333".U) // -50.3
      dut.clock.step(1)
      dut.io.b.poke("h41799999".U) // 15.6

      dut.clock.step(5)
      dut.io.a.poke(1067450368.U) // 1.25
      dut.clock.step(3)

      // dut.io.out.expect("h40700000".U) // 3.75
    }
  }
}
