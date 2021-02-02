// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

// Problem:
//
// Implement test for MaxN using PeekPokeTester
//
class MaxNTests(c: MaxN) extends PeekPokeTester(c) {
  for (i <- 0 until 10) {
    var mx = 0
    for (i <- 0 until c.n) {
      // Implement below ----------
      val in = rnd.nextInt(0x1 << (c.w - 1))
      mx = if (in > mx) in else mx
      poke(c.io.ins(i), in)
      // Implement above ----------
    }
    step(1)
    // Implement below ----------
    expect(c.io.out, mx)
    // Implement above ----------
  }
}

class MaxNTester extends ChiselFlatSpec {
  behavior of "MaxN"
  backends foreach {backend =>
    it should s"correctly count randomly generated numbers in $backend" in {
      Driver(() => new MaxN(4, 16), backend)(c => new MaxNTests(c)) should be (true)
    }
  }
}
