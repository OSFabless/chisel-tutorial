// See LICENSE.txt for license details.
package problems

import chisel3._
import scala.collection.mutable.ArrayBuffer
import chisel3.util.Cat

// Problem:
//
// Implement a four-by-four multiplier using a look-up table.
//
class Mul extends Module {
  val io = IO(new Bundle {
    val x   = Input(UInt(4.W))
    val y   = Input(UInt(4.W))
    val z   = Output(UInt(8.W))
  })
  val mulsValues = new ArrayBuffer[UInt]()

  // Calculate io.z = io.x * io.y by generating a table of values for mulsValues

  // Implement below ----------
  for (i <- 0 until (1 << (io.x.getWidth)))
    for (j <- 0 until (1 << (io.y.getWidth)))
      mulsValues += (i * j).asUInt((io.x.getWidth + io.y.getWidth).W)
  val lut = VecInit(mulsValues)
  io.z := lut(Cat(io.x, io.y))
  // Implement above ----------
}
