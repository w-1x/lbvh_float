circuit Triangle_memory :
  module Triangle_memory :
    input clock : Clock
    input reset : UInt<1>
    output io : { flip addr : UInt<10>, flip wrEna : UInt<1>, flip wrData : UInt<32>, rdData : UInt<32>}

    smem mem : UInt<32> [1024] @[Triangle_memory.scala 15:24]
    when io.wrEna : @[Triangle_memory.scala 19:18]
      write mport MPORT = mem[io.addr], clock
      MPORT <= io.wrData
    wire _io_rdData_WIRE : UInt @[Triangle_memory.scala 23:24]
    _io_rdData_WIRE is invalid @[Triangle_memory.scala 23:24]
    when UInt<1>("h1") : @[Triangle_memory.scala 23:24]
      _io_rdData_WIRE <= io.addr @[Triangle_memory.scala 23:24]
      node _io_rdData_T = or(_io_rdData_WIRE, UInt<10>("h0")) @[Triangle_memory.scala 23:24]
      node _io_rdData_T_1 = bits(_io_rdData_T, 9, 0) @[Triangle_memory.scala 23:24]
      read mport io_rdData_MPORT = mem[_io_rdData_T_1], clock @[Triangle_memory.scala 23:24]
    io.rdData <= io_rdData_MPORT @[Triangle_memory.scala 23:13]

