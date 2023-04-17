package lbvh

import chisel3._
import chisel3.util.experimental._

class A extends Module {
  val io = IO(new Bundle {
    val mem_out = Output(new TriangleModule(32))
  })

  val clock_count_reg = RegInit(0.U(64.W))
  val addr_in_reg = RegInit(0.U(32.W))

  clock_count_reg := clock_count_reg + 1.U

  when(clock_count_reg % 3.U === 0.U) {
    addr_in_reg := addr_in_reg + 9.U
  }

  val memory = SyncReadMem(36, UInt(32.W))

  loadMemoryFromFile(memory, "src/main/scala/lbvh/1.txt")

  io.mem_out.p0.x := memory.read(addr_in_reg + 0.U)
  io.mem_out.p0.y := memory.read(addr_in_reg + 1.U)
  io.mem_out.p0.z := memory.read(addr_in_reg + 2.U)
  io.mem_out.p1.x := memory.read(addr_in_reg + 3.U)
  io.mem_out.p1.y := memory.read(addr_in_reg + 4.U)
  io.mem_out.p1.z := memory.read(addr_in_reg + 5.U)
  io.mem_out.p2.x := memory.read(addr_in_reg + 6.U)
  io.mem_out.p2.y := memory.read(addr_in_reg + 7.U)
  io.mem_out.p2.z := memory.read(addr_in_reg + 8.U)
}
