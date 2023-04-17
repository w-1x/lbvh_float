package lbvh

import chisel3._
import chisel3.util._
import hardfloat._

class Top extends Module {
  val inputFileName = "src/main/scala/lbvh/newtriangle.hex_copy.txt"
  val depth = 1
  val width = 32
  val io = IO(new Bundle {
    // val inputFileName = Input(String) // 从文本中读取的三角形图元的坐标文件名
    // val outputFileName = Input(String) // 输出 BVH 树的文件名
    //  val in = Input(UInt(32.W))
    val out = Output(Vec(depth, new Primitive(width)))
  })

  // 步骤 1：从文本中读取三角形图元的坐标
  val triangles_memory = Module(
    new ReadTriangles(depth, width, inputFileName)
  )

  val triangles = Reg(Vec(depth, new TriangleModule(width)))

  triangles(0).p0.x := triangles_memory.io.mem_out(0).p0.x
  triangles(0).p0.y := triangles_memory.io.mem_out(0).p0.y
  triangles(0).p0.z := triangles_memory.io.mem_out(0).p0.z

  triangles(0).p1.x := triangles_memory.io.mem_out(0).p1.x
  triangles(0).p1.y := triangles_memory.io.mem_out(0).p1.y
  triangles(0).p1.z := triangles_memory.io.mem_out(0).p1.z

  triangles(0).p2.x := triangles_memory.io.mem_out(0).p2.x
  triangles(0).p2.y := triangles_memory.io.mem_out(0).p2.y
  triangles(0).p2.z := triangles_memory.io.mem_out(0).p2.z

  // 步骤 2：计算单个图元的包围盒和中心坐标,

  val primitives = Reg(Vec(depth, new Primitive(32)))

  val Fadd_1_x = Module(new Float_Add)
  val Fadd_2_x = Module(new Float_Add)

  val Fadd_1_y = Module(new Float_Add)
  val Fadd_2_y = Module(new Float_Add)

  val Fadd_1_z = Module(new Float_Add)
  val Fadd_2_z = Module(new Float_Add)

  val centr_1 = Reg(Vec(depth, new VectorModule(32)))

  Fadd_1_x.io.a := triangles(0).p0.x
  Fadd_1_x.io.b := triangles(0).p1.x
  centr_1(0).x := Fadd_1_x.io.out

  Fadd_2_x.io.a := centr_1(0).x
  Fadd_2_x.io.b := triangles(0).p2.x
  primitives(0).centr.x := Fadd_2_x.io.out

  Fadd_1_y.io.a := triangles(0).p0.y
  Fadd_1_y.io.b := triangles(0).p1.y
  centr_1(0).y := Fadd_1_y.io.out

  Fadd_2_y.io.a := centr_1(0).y
  Fadd_2_y.io.b := triangles(0).p2.y
  primitives(0).centr.y := Fadd_2_y.io.out

  Fadd_1_z.io.a := triangles(0).p0.z
  Fadd_1_z.io.b := triangles(0).p1.z
  centr_1(0).z := Fadd_1_z.io.out

  Fadd_2_z.io.a := centr_1(0).z
  Fadd_2_z.io.b := triangles(0).p2.z
  primitives(0).centr.z := Fadd_2_z.io.out

  primitives(0).triangle.p0.x := triangles(0).p0.x
  primitives(0).triangle.p0.y := triangles(0).p0.y
  primitives(0).triangle.p0.z := triangles(0).p0.z

  primitives(0).triangle.p1.x := triangles(0).p1.x
  primitives(0).triangle.p1.y := triangles(0).p1.y
  primitives(0).triangle.p1.z := triangles(0).p1.z

  primitives(0).triangle.p2.x := triangles(0).p2.x
  primitives(0).triangle.p2.y := triangles(0).p2.y
  primitives(0).triangle.p2.z := triangles(0).p2.z

  io.out(0).triangle.p0.x := primitives(0).triangle.p0.x
  io.out(0).triangle.p0.y := primitives(0).triangle.p0.y
  io.out(0).triangle.p0.z := primitives(0).triangle.p0.z
  io.out(0).centr.x := primitives(0).centr.x

  io.out(0).triangle.p1.x := primitives(0).triangle.p1.x
  io.out(0).triangle.p1.y := primitives(0).triangle.p1.y
  io.out(0).triangle.p1.z := primitives(0).triangle.p1.z
  io.out(0).centr.y := primitives(0).centr.y

  io.out(0).triangle.p2.x := primitives(0).triangle.p2.x
  io.out(0).triangle.p2.y := primitives(0).triangle.p2.y
  io.out(0).triangle.p2.z := primitives(0).triangle.p2.z
  io.out(0).centr.z := primitives(0).centr.z

//  val boundingBoxes = triangles.map(computeBoundingBox)
//  val centroids = boundingBoxes.map(computeCentroid)

  /*
  // 步骤 3：计算整个场景下的包围盒
  val sceneBoundingBox = computeSceneBoundingBox(boundingBoxes)

  // 步骤 4：对图元进行 Morton 编码及排序
  val mortonCodes = mortonEncode(centroids, sceneBoundingBox)
  val sortedTriangles = sortTriangles(triangles, mortonCodes)

  // 步骤 5：根据 Morton 码自下而上生成 BVH 树
  val bvhTree = generateBVHTree(sortedTriangles)

  // 将 BVH 树写入输出文件
  writeBVHTree(io.outputFileName, bvhTree)
   */
}
