// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Implement a parametrized simple shift register.
// 'n' is the number of elements in the shift register.
// 'w' is the width of one element.

class VecShiftRegisterParam(val n: Int, val w: Int) extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(w.W))
    val out = Output(UInt(w.W))
  })

  // Implement below ----------
  val initValues = Seq.fill(n) { 0.U(w.W) }
  val delays = RegInit(VecInit(initValues))

  for (i <- 0 until n) { if (i == 0) delays(i) := io.in else delays(i) := delays(i-1) }
  io.out := delays(n-1)
}
// Implement above ----------
