// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.stage.ChiselStage

// Problem:
//
// Implement a loadable shift register with four 4-bit stages using Vec
// Shift occurs if 'shift' is asserted
// Load  occurs if 'load'  is asserted
// Whole state should be replaced with 'ins' when loaded
//
class VecShiftRegister extends Module {
  val io = IO(new Bundle {
    val ins   = Input(Vec(4, UInt(4.W)))
    val load  = Input(Bool())
    val shift = Input(Bool())
    val out   = Output(UInt(4.W))
  })
  // Implement below ----------
  val res = Reg(io.ins.cloneType)
  when (io.load) {
    for (i <- 0 until io.ins.length) res(i) := io.ins(i)
  } .elsewhen(io.shift) {
    for (i <- 0 until io.ins.length) if (i == 0) res(i) := io.ins(i) else res(i) := res(i-1)
  }
  io.out := res(io.ins.length-1)

  // Implement above ----------
}
