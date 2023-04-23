package hardfloat

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Float_DivTest extends AnyFlatSpec with ChiselScalatestTester {
  "My_FloatDiv1" should "pass" in {
    test(new Float_DIV()).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      dut.io.b.poke(1077936128.U) // 3
      dut.io.valid.poke(1.B)
      dut.io.a.poke("h3f7d70a4".U) // 0.99
      dut.clock.step(5)
      dut.io.valid.poke(1.B)
      dut.io.a.poke("h42040000".U) // 33
      dut.clock.step(5)
      dut.clock.step(10)
      dut.io.valid.poke(1.B)
      dut.io.a.poke("h42113333".U) // 36.3

      dut.clock.step(50)
    }
  }
}
