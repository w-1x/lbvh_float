package lbvh

import chisel3._
import chisel3.util._
import chisel3.util.experimental._

class ReadTriangles_1(depth: Int, width: Int, inputFileName: String)
    extends Module {
  val io = IO(new Bundle {
    val addr = Input(UInt(64.W))
    val mem_out = Output(new TriangleModule(width))
  })

  val memory = SyncReadMem(depth * 9, UInt(width.W))

  loadMemoryFromFile(memory, inputFileName)

  io.mem_out.p0.x := memory.read(io.addr + 0.U)
  io.mem_out.p0.y := memory.read(io.addr + 1.U)
  io.mem_out.p0.z := memory.read(io.addr + 2.U)
  io.mem_out.p1.x := memory.read(io.addr + 3.U)
  io.mem_out.p1.y := memory.read(io.addr + 4.U)
  io.mem_out.p1.z := memory.read(io.addr + 5.U)
  io.mem_out.p2.x := memory.read(io.addr + 6.U)
  io.mem_out.p2.y := memory.read(io.addr + 7.U)
  io.mem_out.p2.z := memory.read(io.addr + 8.U)

}
