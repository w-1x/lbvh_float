/*package lbvh

import chisel3._
import chisel3.util._
import hardfloat._

class Top_1 extends Module {

  val io = IO(new Bundle {
    // val inputFileName = Input(String) // 从文本中读取的三角形图元的坐标文件名
    // val outputFileName = Input(String) // 输出 BVH 树的文件名
    val out = Output(new Primitive(width))
    val out_ready = Output(Bool())
  })

  val clock_count_reg = RegInit(0.U(64.W))
  val addr_in_reg = RegInit(0.U(64.W))

  clock_count_reg := clock_count_reg + 1.U

  when(clock_count_reg % 26.U === 21.U) {
    addr_in_reg := addr_in_reg + 9.U
  }
  // 步骤 1：从文本中读取三角形图元的坐标

  val triangles_memory = Module(
    new ReadTriangles_1(depth, width, inputFileName)
  )

  val triangles = Reg(new TriangleModule(width))

  triangles_memory.io.addr := addr_in_reg
  triangles := triangles_memory.io.mem_out

  val add_3 = Reg(new VectorModule(width)) // 用来保存第三个加数

  add_3.x := triangles_memory.io.mem_out.p2.x
  add_3.y := triangles_memory.io.mem_out.p2.y
  add_3.z := triangles_memory.io.mem_out.p2.z

  // 步骤 2：计算单个图元的包围盒和中心坐标,
//先求和
  val primitives = Reg(new Primitive(32))

  val Fadd_1_x = Module(new Float_Add)
  val Fadd_2_x = Module(new Float_Add)

  val Fadd_1_y = Module(new Float_Add)
  val Fadd_2_y = Module(new Float_Add)

  val Fadd_1_z = Module(new Float_Add)
  val Fadd_2_z = Module(new Float_Add)

  val centr_1 = Reg(new VectorModule(32))
  val centr_2 = Reg(new VectorModule(32))

  Fadd_1_x.io.a := triangles.p0.x
  Fadd_1_x.io.b := triangles.p1.x
  centr_1.x := Fadd_1_x.io.out

  Fadd_1_y.io.a := triangles.p0.y
  Fadd_1_y.io.b := triangles.p1.y
  centr_1.y := Fadd_1_y.io.out

  Fadd_1_z.io.a := triangles.p0.z
  Fadd_1_z.io.b := triangles.p1.z
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

//再平均

  val Fdiv_x = Module(new Float_DIV)
  val Fdiv_y = Module(new Float_DIV)
  val Fdiv_z = Module(new Float_DIV)

  Fdiv_x.io.a := centr_2.x
  Fdiv_x.io.b := 1077936128.U
  Fdiv_x.io.valid := (clock_count_reg % 26.U === 7.U && clock_count_reg =/= 1.U)
  primitives.centr.x := Fdiv_x.io.out

  Fdiv_y.io.a := centr_2.y
  Fdiv_y.io.b := 1077936128.U
  Fdiv_y.io.valid := (clock_count_reg % 26.U === 7.U && clock_count_reg =/= 1.U)
  primitives.centr.y := Fdiv_y.io.out

  Fdiv_z.io.a := centr_2.z
  Fdiv_z.io.b := 1077936128.U
  Fdiv_z.io.valid := (clock_count_reg % 26.U === 7.U && clock_count_reg =/= 1.U)
  primitives.centr.z := Fdiv_z.io.out

  primitives.triangle := triangles

  io.out.centr := primitives.centr
  io.out.triangle := primitives.triangle
  io.out_ready := Fdiv_x.io.ready

}
 */
