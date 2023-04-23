package hardfloat

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Float_MulAddTest extends AnyFlatSpec with ChiselScalatestTester {
  "My_MulAdd" should "pass" in {
    test(new Float_MULADD()).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      dut.io.a.poke("h41233333".U) // 10.2
      dut.io.b.poke("hC2720000".U) // -60.5
      dut.io.c.poke("h41233333".U) // 10.2
      //  dut.io.c.poke("h3F47AE14".U) // 0.78
      dut.clock.step(10)
    }
  }
}
