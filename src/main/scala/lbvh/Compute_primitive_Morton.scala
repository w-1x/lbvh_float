package lbvh

import chisel3._
import hardfloat._
import config.Configs._

class Compute_primitive_Morton extends Module {
  // 实现(centres - bbox.min)* grid_dim(也就是一个方向的morton码的最大值)
  //  /bbox(max - min)
  val io = IO(new Bundle {
    val input_global_bbox = Input(new BoundingBoxModule(DATA_WIDTH))
    val input_grid_dim = Input(new VectorModule(Morton_WIDTH_by_per_axis))
    val input_centres = Input(new VectorModule(DATA_WIDTH))
    val output_Morton_code = Output(new VectorModule(Morton_WIDTH_by_per_axis))
  })
  val clock_count_reg = RegInit(0.U(DATA_WIDTH.W)) // 时钟计数用来控制除法器valid信号
  clock_count_reg := clock_count_reg + 1.U

  val Fadd_1 = Module(new Float_Vector_sub)
  val Fadd_2 = Module(new Float_Vector_sub)

  Fadd_1.io.input_vector1 := io.input_global_bbox.maxPoint
  Fadd_1.io.input_vector2 := io.input_global_bbox.minPoint

  val bbox_sub_Reg = Reg(new VectorModule(DATA_WIDTH))
  bbox_sub_Reg := Fadd_1.io.out_vector

  Fadd_2.io.input_vector1 := io.input_centres
  Fadd_2.io.input_vector2 := io.input_global_bbox.minPoint

  val centres_sub_Reg = Reg(new VectorModule(DATA_WIDTH))
  centres_sub_Reg := Fadd_2.io.out_vector

  val FInverter = Module(new Float_Vector_inverter) // 初始化需要4个周期
  FInverter.io.input_vector := bbox_sub_Reg

  val Fmul_1 = Module(new Float_Vector_mul)
  val Fmul_2 = Module(new Float_Vector_mul)
  Fmul_1.io.input_vector1 := centres_sub_Reg
  Fmul_1.io.input_vector2 := FInverter.io.out_vector

  Fmul_2.io.input_vector1 := Fmul_1.io.out_vector
  Fmul_2.io.input_vector2 := io.input_grid_dim

  io.output_Morton_code := Fmul_2.io.out_vector
}
