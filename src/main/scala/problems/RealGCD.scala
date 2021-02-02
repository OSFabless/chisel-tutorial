// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util.{Valid, DeqIO}

// Problem:
// Implement a GCD circuit (the greatest common divisor of two numbers).
// Input numbers are bundled as 'RealGCDInput' and communicated using the Decoupled handshake protocol
//
class RealGCDInput extends Bundle {
  val a = UInt(16.W)
  val b = UInt(16.W)
}

class RealGCD extends Module {
  val io  = IO(new Bundle {
    val in  = DeqIO(new RealGCDInput())
    val out = Output(Valid(UInt(16.W)))
  })

  // Implement below ----------

  /** Euclidean algorithm
    * int gcd(int a, int b)
    * {
    *   return b ? gcd(b, a%b) : a;
    * }
    */
  val x = Reg(UInt(io.in.bits.a.getWidth.W))
  val y = Reg(UInt(io.in.bits.b.getWidth.W))
  val e = RegInit(false.B)

  io.in.ready := !e
  
  when (io.in.valid && !e) {
    x := io.in.bits.a
    y := io.in.bits.b
    e := !e
  }

  when (e) {
    when (x > y) {
      x := x % y
    } .otherwise {
      x := y
      y := x
    }
  }

  io.out.bits   := y
  io.out.valid  := x === 0.U && e

  when (io.out.valid) { e := false.B }

  // Implement above ----------

}
