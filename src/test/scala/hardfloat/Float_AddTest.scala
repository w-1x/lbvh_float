package hardfloat

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Float_AddTest extends AnyFlatSpec with ChiselScalatestTester {
  "My_Add" should "pass" in {
    test(new Float_Add()).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      dut.io.a.poke(1067450368.U)
      dut.io.b.poke(1067450368.U)
      dut.clock.step()
      dut.io.out.expect(1075838976.U)
    }
  }
}
