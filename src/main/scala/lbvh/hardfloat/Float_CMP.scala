package hardfloat

import chisel3._

class Float_CMP_le extends Module {
  val io = IO(new Bundle {
    val a = Input(Bits(32.W))
    val b = Input(Bits(32.W))
    val out_bool = Output(Bool())
    val out_max = Output(Bits(32.W))
    val out_min = Output(Bits(32.W))
  })
  val fcmp = Module(new ValExec_CompareRecF32_le)
  fcmp.io.a := io.a
  fcmp.io.b := io.b
  fcmp.io.expected.out := 0.U
  fcmp.io.expected.exceptionFlags := 0.U

  io.out_bool := fcmp.io.actual.out

  when(io.out_bool) {
    io.out_max := io.b
    io.out_min := io.a
  }.otherwise {
    io.out_max := io.a
    io.out_min := io.b
  }

}
