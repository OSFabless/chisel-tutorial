// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

// Problem:
//
// Implement test with PeekPokeTester
//
class Max2Tests(c: Max2) extends PeekPokeTester(c) {
  for (i <- 0 until 10) {

    // Implement below ----------
    val in0 = rnd.nextInt(1 << (c.io.in0.getWidth - 1))
    val in1 = rnd.nextInt(1 << (c.io.in1.getWidth - 1))
    poke(c.io.in0, in0)
    poke(c.io.in1, in1)
    step(1)
    expect(c.io.out, if (in0 > in1) in0 else in1)
    // Implement above ----------
  }
}

class Max2Tester extends ChiselFlatSpec {
  behavior of "Max2"
  backends foreach {backend =>
    it should s"correctly count randomly generated numbers in $backend" in {
      Driver(() => new Max2, backend)(c => new Max2Tests(c)) should be (true)
    }
  }
}
