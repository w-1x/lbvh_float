package hardfloat

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Float_MulTest extends AnyFlatSpec with ChiselScalatestTester {
  "My_Mul" should "pass" in {
    test(new Float_MUL()).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      // dut.io.a.poke("h41233333".U) // 10.2
      dut.io.a.poke("h3F800000".U) // 1

      dut.io.b.poke("h40600000".U) // 3.5
      // dut.io.b.poke("h38f00000".U) // 1
      //  dut.io.c.poke("h3F47AE14".U) // 0.78

      dut.clock.step(10)
    }
  }
}
