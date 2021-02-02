// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class Mux2Tests(c: Mux2) extends PeekPokeTester(c) {
  for (s <- 0 until 2) {
    for (i0 <- 0 until 2) {
      for (i1 <- 0 until 2) {
        poke(c.io.sel, s)
        poke(c.io.in1, i1)
        poke(c.io.in0, i0)
        step(1)
        expect(c.io.out, if (s == 1) i1 else i0)
      }
    }
  }
}

class Mux2Tester extends ChiselFlatSpec {
  behavior of "Mux2"
  backends foreach {backend =>
    it should s"correctly count randomly generated numbers in $backend" in {
      Driver(() => new Mux2, backend)(c => new Mux2Tests(c)) should be (true)
    }
  }
}
