package lbvh

import chisel3._
import hardfloat._
import config.Configs._

class Compute_global_bbox extends Module { // 多少个数花费多少个周期
  val io = IO(new Bundle {
    val input_bbox1 = Input(new BoundingBoxModule(DATA_WIDTH))
    val output_global_bbox = Output(new BoundingBoxModule(DATA_WIDTH))
  })
  val clock_count_reg = RegInit(0.U(DATA_WIDTH.W)) // 时钟计数用来控制除法器valid信号
  clock_count_reg := clock_count_reg + 1.U
  /*
  tem_Init.minPoint.x := "h7f7fffff".U
  tem_Init.minPoint.y := "h7f7fffff".U
  tem_Init.minPoint.z := "h7f7fffff".U

  tem_Init.maxPoint.x := "h80800000".U
  tem_Init.maxPoint.x := "h80800000".U
  tem_Init.maxPoint.x := "h80800000".U
   */
  val temp_bigger_bbox =
    Reg(new BoundingBoxModule(DATA_WIDTH))
  /*val temp_input_bbox1 =
    Reg(new BoundingBoxModule(DATA_WIDTH))

  temp_input_bbox1 := io.input_bbox1
   */
  val compute_big_bbox = Module(new Compute_bigger_bbox)
  compute_big_bbox.io.input_bbox1 := io.input_bbox1
  compute_big_bbox.io.input_bbox2 := temp_bigger_bbox

  when(clock_count_reg === 0.U) {
    temp_bigger_bbox := io.input_bbox1
  }.otherwise {
    temp_bigger_bbox := compute_big_bbox.io.out_update_bbox
  }
  io.output_global_bbox := temp_bigger_bbox

}
