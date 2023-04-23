package lbvh

import chisel3._
import hardfloat._
import config.Configs._

class Compute_centres extends Module { // 32周期出一个结果
  val io = IO(new Bundle {
    val input_data = Input(new TriangleModule(DATA_WIDTH))
    val out_cetres = Output(new VectorModule(DATA_WIDTH))
    val out_ready = Output(Bool())
  })

  val clock_count_reg = RegInit(0.U(DATA_WIDTH.W)) // 时钟计数用来控制除法器valid信号
  clock_count_reg := clock_count_reg + 1.U

  val add_3 = Reg(new VectorModule(DATA_WIDTH)) // 用来保存第三个加数

  add_3.x := io.input_data.p2.x
  add_3.y := io.input_data.p2.y
  add_3.z := io.input_data.p2.z

// 先求和
  val Fadd_1_x = Module(new Float_Add)
  val Fadd_2_x = Module(new Float_Add)

  val Fadd_1_y = Module(new Float_Add)
  val Fadd_2_y = Module(new Float_Add)

  val Fadd_1_z = Module(new Float_Add)
  val Fadd_2_z = Module(new Float_Add)

  val centr_1 = Reg(new VectorModule(32))
  val centr_2 = Reg(new VectorModule(32))

  Fadd_1_x.io.a := io.input_data.p0.x
  Fadd_1_x.io.b := io.input_data.p1.x
  centr_1.x := Fadd_1_x.io.out

  Fadd_1_y.io.a := io.input_data.p0.y
  Fadd_1_y.io.b := io.input_data.p1.y
  centr_1.y := Fadd_1_y.io.out

  Fadd_1_z.io.a := io.input_data.p0.z
  Fadd_1_z.io.b := io.input_data.p1.z
  centr_1.z := Fadd_1_z.io.out

  Fadd_2_x.io.a := centr_1.x
  Fadd_2_x.io.b := add_3.x
  centr_2.x := Fadd_2_x.io.out

  Fadd_2_y.io.a := centr_1.y
  Fadd_2_y.io.b := add_3.y
  centr_2.y := Fadd_2_y.io.out

  Fadd_2_z.io.a := centr_1.z
  Fadd_2_z.io.b := add_3.z
  centr_2.z := Fadd_2_z.io.out

  // 再平均

  val Fdiv_x = Module(new Float_DIV)
  val Fdiv_y = Module(new Float_DIV)
  val Fdiv_z = Module(new Float_DIV)

  Fdiv_x.io.a := centr_2.x
  Fdiv_x.io.b := 1077936128.U
  Fdiv_x.io.valid := (clock_count_reg % 4.U === 0.U) // 前面都求和计算共需要4个周期
  io.out_cetres.x := Fdiv_x.io.out

  Fdiv_y.io.a := centr_2.y
  Fdiv_y.io.b := 1077936128.U
  Fdiv_y.io.valid := (clock_count_reg % 4.U === 0.U)
  io.out_cetres.y := Fdiv_y.io.out

  Fdiv_z.io.a := centr_2.z
  Fdiv_z.io.b := 1077936128.U
  Fdiv_z.io.valid := (clock_count_reg % 4.U === 0.U)
  io.out_cetres.z := Fdiv_z.io.out

  io.out_ready := (Fdiv_x.io.ready && Fdiv_x.io.valid && Fdiv_x.io.out =/= 0.U)

}
