package lbvh

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Float_vec_addTest extends AnyFlatSpec with ChiselScalatestTester {
  "Float_vec_addTest" should "pass test" in {
    test(new Float_Vector_add).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      dut.io.input_vector1.x.poke("h41233333".U) // 10.2
      dut.io.input_vector1.y.poke("hC2720000".U) // -60.5
      dut.io.input_vector1.z.poke("h44DF3333".U) // 1785.6

      dut.io.input_vector2.x.poke("h43124CCC".U) // 146.3
      dut.io.input_vector2.y.poke("h3F47AE14".U) // 0.78
      dut.io.input_vector2.z.poke("hBF4CCCCC".U) // -0.8
      dut.clock.step(5)
    }
  }
}
