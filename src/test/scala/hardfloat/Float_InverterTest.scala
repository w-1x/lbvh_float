package hardfloat

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Float_InverterTest extends AnyFlatSpec with ChiselScalatestTester {
  "My_Float_Inverter" should "pass" in {
    test(new Float_Inverter()).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      /*  dut.io.in1.poke(1096155136.U) // 13.375
           dut.clock.step()
      dut.io.in1.poke(1091829760.U) // 9.25
      dut.clock.step()
      dut.io.in1.poke("h3F47AE14".U) // 0.78

      dut.clock.step()*/
      dut.io.in1.poke("hC2720000".U) // -60.5
      dut.clock.step(10)
    }
  }
}
