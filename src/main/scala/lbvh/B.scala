/*package lbvh

import chisel3._

class Top extends Module {
  val io = IO(new Bundle {
    val in = Input(UInt(32.W))
    val out = Output(UInt(32.W))
  })

  val triangles = Module(
    new Triangles_memory(1, "src/main/scala/lbvh/newtriangle.hex_copy.txt")
  )
  val a = Module(new A)

  triangles.io.addr := io.in
  a.io.in := triangles.io.out
  io.out := a.io.out
}
 */
