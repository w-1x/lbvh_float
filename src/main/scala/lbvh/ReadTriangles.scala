package lbvh

import chisel3._
import chisel3.util.experimental._

class ReadTriangles(depth: Int, width: Int, inputFileName: String)
    extends Module {
  val io = IO(new Bundle {
    val mem_out = Output(Vec(depth, new TriangleModule(width)))
  })

  val memory = SyncReadMem(depth * 9, UInt(width.W))

  loadMemoryFromFile(memory, inputFileName)

  io.mem_out(0).p0.x := memory.read(0.U)
  io.mem_out(0).p0.y := memory.read(1.U)
  io.mem_out(0).p0.z := memory.read(2.U)
  io.mem_out(0).p1.x := memory.read(3.U)
  io.mem_out(0).p1.y := memory.read(4.U)
  io.mem_out(0).p1.z := memory.read(5.U)
  io.mem_out(0).p2.x := memory.read(6.U)
  io.mem_out(0).p2.y := memory.read(7.U)
  io.mem_out(0).p2.z := memory.read(8.U)
}
