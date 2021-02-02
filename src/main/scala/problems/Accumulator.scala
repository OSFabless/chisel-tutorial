// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Count incoming trues
// (increase counter every clock if 'in' is asserted)
//
class Accumulator extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(1.W))
    val out = Output(UInt(8.W))
  })

  // Implement below ----------
  val acc = RegInit(0.U(io.out.getWidth.W))
  acc := acc + io.in
  io.out := acc
  // Implement above ----------
}
