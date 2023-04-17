/*
package lbvh

import chisel3._
import chisel3.util._
import hardfloat._

class VectorModule(width: Int) extends Bundle {
  val x = UInt(width.W)
  val y = UInt(width.W)
  val z = UInt(width.W)
}

class TriangleModule(width: Int) extends Bundle {
  val p0 = new VectorModule(width)
  val p1 = new VectorModule(width)
  val p2 = new VectorModule(width)
}
/*
class BoundingBoxModule(width: Int) extends Bundle {
  val minPoint = new VectorModule(width)
  val maxPoint = new VectorModule(width)
}
 */

class ComputeBoundingBoxesAndCentersModule(primitiveCount: Int, width: Int)
    extends Module {
  val io = IO(new Bundle {
    val triangles = Input(Vec(primitiveCount, new TriangleModule(width)))
    /*    val boundingBoxes =
      Output(Vec(primitiveCount, new BoundingBoxModule(width)))
 */
    val centers = Output(Vec(primitiveCount, new VectorModule(width)))
  })

  // Compute bounding boxes and centers
  for (i <- 0 until primitiveCount) {
    val triangle = io.triangles(i)
    /*
    // Compute bounding box
    val bbMinX = Wire(UInt(width.W))
    val bbMinY = Wire(UInt(width.W))
    val bbMinZ = Wire(UInt(width.W))
    val bbMaxX = Wire(UInt(width.W))
    val bbMaxY = Wire(UInt(width.W))
    val bbMaxZ = Wire(UInt(width.W))

    bbMinX := triangle.p0.x.min(triangle.p1.x).min(triangle.p2.x)
    bbMinY := triangle.p0.y.min(triangle.p1.y).min(triangle.p2.y)
    bbMinZ := triangle.p0.z.min(triangle.p1.z).min(triangle.p2.z)
    bbMaxX := triangle.p0.x.max(triangle.p1.x).max(triangle.p2.x)
    bbMaxY := triangle.p0.y.max(triangle.p1.y).max(triangle.p2.y)
    bbMaxZ := triangle.p0.z.max(triangle.p1.z).max(triangle.p2.z)

    val boundingBox = Wire(new BoundingBoxModule(width))
    boundingBox.minPoint.x := bbMinX
    boundingBox.minPoint.y := bbMinY
    boundingBox.minPoint.z := bbMinZ
    boundingBox.maxPoint.x := bbMaxX
    boundingBox.maxPoint.y := bbMaxY
    boundingBox.maxPoint.z := bbMaxZ

    io.boundingBoxes(i) := boundingBox
 */
    // Compute center
    val Float_Add_1_x = Module(new Float_Add)
    val Float_Add_2_x = Module(new Float_Add)

    Float_Add_1_x.io.a := triangle.p0.x
    Float_Add_1_x.io.b := triangle.p1.x

    Float_Add_2_x.io.a := Float_Add_1_x.io.out
    Float_Add_2_x.io.b := triangle.p2.x

    val Float_Add_1_y = Module(new Float_Add)
    val Float_Add_2_y = Module(new Float_Add)

    Float_Add_1_y.io.a := triangle.p0.x
    Float_Add_1_y.io.b := triangle.p1.x

    Float_Add_2_y.io.a := Float_Add_1_y.io.out
    Float_Add_2_y.io.b := triangle.p2.x

    val Float_Add_1_z = Module(new Float_Add)
    val Float_Add_2_z = Module(new Float_Add)

    Float_Add_1_z.io.a := triangle.p0.x
    Float_Add_1_z.io.b := triangle.p1.x

    Float_Add_2_z.io.a := Float_Add_1_z.io.out
    Float_Add_2_z.io.b := triangle.p2.x

    val centerX = Wire(UInt(width.W))
    val centerY = Wire(UInt(width.W))
    val centerZ = Wire(UInt(width.W))

    centerX := Float_Add_2_x.io.out
    centerY := Float_Add_2_y.io.out
    centerZ := Float_Add_2_z.io.out
    /*
    centerX := (triangle.p0.x + triangle.p1.x + triangle.p2.x) / 3.U
    centerY := (triangle.p0.y + triangle.p1.y + triangle.p2.y) / 3.U
    centerZ := (triangle.p0.z + triangle.p1.z + triangle.p2.z) / 3.U
 */
    val center = Wire(new VectorModule(width))
    center.x := centerX
    center.y := centerY
    center.z := centerZ

    io.centers(i) := center
  }
}

object Compute_bounding_boxes_and_centers extends App {
  emitVerilog(
    new ComputeBoundingBoxesAndCentersModule(2, 32),
    Array("--target-dir", "generated")
  )
}
 */
