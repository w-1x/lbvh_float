package lbvh

import chisel3._
import hardfloat._
import config.Configs._

class Compute_local_bbox extends Module {
  val io = IO(new Bundle {
    val input_data = Input(new TriangleModule(DATA_WIDTH))
    val out_bbox = Output(new BoundingBoxModule(DATA_WIDTH))
  })

  val clock_count_reg = RegInit(0.U(DATA_WIDTH.W)) // 时钟计数用来控制除法器valid信号
  clock_count_reg := clock_count_reg + 1.U

  val compute_bbox_x = Module(new Compute_vector)
  val compute_bbox_y = Module(new Compute_vector)
  val compute_bbox_z = Module(new Compute_vector)

  compute_bbox_x.io.input_vec.x := io.input_data.p0.x
  compute_bbox_x.io.input_vec.y := io.input_data.p1.x
  compute_bbox_x.io.input_vec.z := io.input_data.p2.x

  compute_bbox_y.io.input_vec.x := io.input_data.p0.y
  compute_bbox_y.io.input_vec.y := io.input_data.p1.y
  compute_bbox_y.io.input_vec.z := io.input_data.p2.y

  compute_bbox_z.io.input_vec.x := io.input_data.p0.z
  compute_bbox_z.io.input_vec.y := io.input_data.p1.z
  compute_bbox_z.io.input_vec.z := io.input_data.p2.z

  io.out_bbox.maxPoint.x := compute_bbox_x.io.out_vec_max
  io.out_bbox.minPoint.x := compute_bbox_x.io.out_vec_min

  io.out_bbox.maxPoint.y := compute_bbox_y.io.out_vec_max
  io.out_bbox.minPoint.y := compute_bbox_y.io.out_vec_min

  io.out_bbox.maxPoint.z := compute_bbox_z.io.out_vec_max
  io.out_bbox.minPoint.z := compute_bbox_z.io.out_vec_min
}
