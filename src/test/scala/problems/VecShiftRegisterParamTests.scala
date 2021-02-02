// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class VecShiftRegisterParamTests(c: VecShiftRegisterParam) extends PeekPokeTester(c) {
  val reg = Array.fill(c.n){ 0 }
  for (t <- 0 until 16) {
    val in = rnd.nextInt(1 << c.w)
    poke(c.io.in, in)
    step(1)
    for (i <- c.n-1 to 1 by -1)
      reg(i) = reg(i-1)
    reg(0) = in
    expect(c.io.out, reg(c.n-1))
  }
}

class VecShiftRegisterParamTester extends ChiselFlatSpec {
  behavior of "VecShiftRegisterParam"
  backends foreach {backend =>
    it should s"correctly count randomly generated numbers in $backend" in {
      Driver(() => new VecShiftRegisterParam(8, 16), backend)(c => new VecShiftRegisterParamTests(c)) should be (true)
    }
  }
}
