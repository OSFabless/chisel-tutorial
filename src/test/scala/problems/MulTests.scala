// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class MulTests(c: Mul) extends PeekPokeTester(c) {
  val maxInt  = 1 << 4
  for (i <- 0 until 10) {
    val x = rnd.nextInt(maxInt)
    val y = rnd.nextInt(maxInt)
    poke(c.io.x, x)
    poke(c.io.y, y)
    step(1)
    expect(c.io.z, x * y)
  }
}

class MulTester extends ChiselFlatSpec {
  behavior of "Mul"
  backends foreach {backend =>
    it should s"correctly count randomly generated numbers in $backend" in {
      Driver(() => new Mul, backend)(c => new MulTests(c)) should be (true)
    }
  }
}
