package com.examples

import scala.io.StdIn

object DeadOrAliveGameInteractive extends App {
  var xMax = 0
  var yMax = 0
  var iter = 0

  while (xMax <= 0 || yMax <= 0 || iter < 1) {
    println("Enter X bound, Y bound, num of iterations, for example: 3 4 3")
    val header = StdIn.readLine()
    val params = header.split(" ")
    xMax = params(0).toInt
    yMax = params(1).toInt
    iter = params(2).toInt
    if (xMax <= 0 || yMax <= 0 || iter < 1) {
      println("X bound or Y bound or num of iterations not valid")
    }
  }

  var aliveCells = Seq[(Int, Int)]()
  println("Enter X and Y for alive cell(Ends with an empty input), for example: 0 0")
  var line = StdIn.readLine()
  while (line != "") {
    val axis = line.split(" ")
    val (x, y) = (axis(0).toInt, axis(1).toInt)
    if (x >= 0 && x < xMax && y >= 0 && y < yMax) {
      aliveCells :+= (x, y)
    } else {
      println("X or Y out of Bound")
    }
    println("Enter X and Y for alive cell(Ends with an empty input), for example: 0 0")
    line = StdIn.readLine()
  }

  println("The grid :")
  DeadOrAliveGame.printDeadOrALiveForGridInGivenTime(xMax, yMax, aliveCells, iter)

}
