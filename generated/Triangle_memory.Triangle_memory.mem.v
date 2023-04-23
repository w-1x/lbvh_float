module BindsTo_0_Triangle_memory(
  input         clock,
  input         reset,
  input  [9:0]  io_addr,
  input         io_wrEna,
  input  [31:0] io_wrData,
  output [31:0] io_rdData
);

initial begin
  $readmemh("src/main/scala/lbvh/p0.hex.txt", Triangle_memory.mem);
end
                      endmodule

bind Triangle_memory BindsTo_0_Triangle_memory BindsTo_0_Triangle_memory_Inst(.*);