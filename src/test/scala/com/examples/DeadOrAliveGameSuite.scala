package com.examples

import org.scalatest.flatspec.AnyFlatSpec

class DeadOrAliveGameSuite extends AnyFlatSpec {
  it should "calculate the grid in a given time" in {
    //Given
    val aliveCells = Seq((0, 0), (1, 0), (2, 0))
    val expected1 = Array(Array(".", "#", "."), Array(".", "#", "."), Array(".", ".", "."))
    val expected2 = Array(Array(".", ".", "."), Array(".", ".", "."), Array(".", ".", "."),  Array(".", ".", "."))
    //WHEN
    val res1 = DeadOrAliveGame.processDeadOrALiveForGridInGivenTime(3, 3, aliveCells, 1)
    val res2 = DeadOrAliveGame.processDeadOrALiveForGridInGivenTime(3, 4, aliveCells, 3)
    //THEN
    assert(expected1.deep == res1.deep)
    assert(expected2.deep == res2.deep)
  }
}
