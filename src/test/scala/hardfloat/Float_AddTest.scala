package hardfloat

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Float_AddTest extends AnyFlatSpec with ChiselScalatestTester {
  "My_Add" should "pass" in {
    test(new Float_Add()).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      dut.io.a.poke(1067450368.U) // 1.25
      dut.io.b.poke("hC12E6666".U) // -10.9)
      dut.clock.step()
    }
  }
}
