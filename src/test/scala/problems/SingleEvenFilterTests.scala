// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class SingleEvenFilterTests[T <: UInt](c: SingleEvenFilter[T]) extends PeekPokeTester(c) {
  val maxInt  = 1 << 16
  for (i <- 0 until 10) {
    val in = rnd.nextInt(maxInt)
    poke(c.io.in.valid, 1)
    poke(c.io.in.bits, in)
    val isSingleEven = (in <= 9) && (in%2 == 1)
    step(1)
    expect(c.io.out.valid, if (isSingleEven) 1 else 0)
    expect(c.io.out.bits, in)
  }
}

class SingleEvenFilterTester extends ChiselFlatSpec {
  behavior of "SingleEvenFilter"
  backends foreach {backend =>
    it should s"correctly count randomly generated numbers in $backend" in {
      Driver(() => new SingleEvenFilter(UInt(16.W)), backend)(c => new SingleEvenFilterTests(c)) should be (true)
    }
  }
}
