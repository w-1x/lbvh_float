package lbvh

import chisel3._
import chisel3.util.experimental._
import config.Configs._

class ReadTriangles extends Module {
  val io = IO(new Bundle {
    val addr = Input(UInt(ADDR_WIDTH.W))
    val mem_out = Output(new TriangleModule(DATA_WIDTH))
  })

  val memory = SyncReadMem(DEPTH * 9, UInt(DATA_WIDTH.W))

  loadMemoryFromFile(memory, InputFileName)

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
