package lbvh

import chisel3._
import hardfloat._
import config.Configs._

class Float_Vector_inverter extends Module { // 单周期
  val io = IO(new Bundle {
    val input_vector = Input(new VectorModule(DATA_WIDTH))
    val out_vector = Output(new VectorModule(DATA_WIDTH))
  })

  val FInverter_1 = Module(new Float_Inverter)
  val FInverter_2 = Module(new Float_Inverter)
  val FInverter_3 = Module(new Float_Inverter)

  FInverter_1.io.in1 := io.input_vector.x
  FInverter_2.io.in1 := io.input_vector.y
  FInverter_3.io.in1 := io.input_vector.z

  io.out_vector.x := FInverter_1.io.out
  io.out_vector.y := FInverter_2.io.out
  io.out_vector.z := FInverter_3.io.out

}
