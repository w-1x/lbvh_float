package lbvh

import chisel3._

class VectorModule(width: Int) extends Bundle {
  val x = Bits(width.W)
  val y = Bits(width.W)
  val z = Bits(width.W)
}

class TriangleModule(width: Int) extends Bundle {
  val p0 = new VectorModule(width)
  val p1 = new VectorModule(width)
  val p2 = new VectorModule(width)
}

class BoundingBoxModule(width: Int) extends Bundle {
  val minPoint = new VectorModule(width)
  val maxPoint = new VectorModule(width)
}

class Primitive(width1: Int) extends Bundle {
  val triangle = new TriangleModule(width1)
  val centr = new VectorModule(width1)

}
/*
class Primitive(width1: Int, width2: Int) extends Bundle {
  val triangle = new TriangleModule(width1)
  val bbox = new BoundingBoxModule(width1)
  val mortonCode = UInt(width2.W)
  val primitiveBeforesort = UInt(width1.W)
  val primitiveAftersort = UInt(width1.W)
}
 */
