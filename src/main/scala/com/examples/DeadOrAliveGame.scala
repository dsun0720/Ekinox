package com.examples

object DeadOrAliveGame {

  def printDeadOrALiveForGridInGivenTime(xMax: Int, yMax: Int, aliveCells: Seq[(Int, Int)], iter: Int): Unit = {
    val grid = processDeadOrALiveForGridInGivenTime(xMax, yMax, aliveCells, iter)
    for (i <- 0 until yMax) {
      for (j <- 0 until xMax) {
        print(grid(i)(j))
      }
      print("\n")
    }
  }
  // iter = n, means t + n
  def processDeadOrALiveForGridInGivenTime(xMax: Int, yMax: Int, aliveCells: Seq[(Int, Int)], iter: Int): Array[Array[String]] = {
    val grid = initGrid(xMax, yMax, aliveCells)
    (1 to iter).foldLeft(grid) {
      case (prevGrid, _) => processDeadOrAliveForGrid(xMax, yMax, prevGrid)
    }
  }

  private def processDeadOrAliveForGrid(xMax: Int, yMax: Int, grid: Array[Array[String]]): Array[Array[String]] = {
    val res = Array.ofDim[String](yMax, xMax)
    for (i <- 0 until yMax;
         j <- 0 until xMax)
      res(i)(j) = processDeadOrAliveRuleForCellInNextStep(j, i, xMax, yMax, grid)
    res
  }

  private def processDeadOrAliveRuleForCellInNextStep(x: Int, y: Int, xMax: Int, yMax: Int, grid: Array[Array[String]]): String = {
    // 8 relative position of neighbors for a given cell
    val status = grid(y)(x)
    val neighbors = Seq(
      (-1, -1),
      (0, -1),
      (1, -1),
      (-1, 0),
      (1, 0),
      (-1, 1),
      (0, 1),
      (1, 1)
    )

    // total alive num of neighbors
    val alive = neighbors.map {
      case (x1, y1) => getDeadOrAliveInCell(x + x1, y + y1, xMax, yMax, grid)
    }.sum[Int]

    // next step status
    if (status == "#") {
      alive match {
        case num if (num < 2 || num > 3) => "." // < 2 neighbors or > 3 neighbors alive => dead :(
        case _ => "#" // 2 neighbors or 3 neighbors alive => still alive :)
      }
    } else {
      alive match {
        case 3 => "#" // 3 neighbors alive => reborn :)
        case _ => "." // still dead :(
      }
    }
  }

  // return 1 alive; return 0 if dead or out of bound
  private def getDeadOrAliveInCell(x: Int, y: Int, xMax: Int, yMax: Int, grid: Array[Array[String]]): Int = {
    if (x >= 0 && x < xMax && y >= 0 && y < yMax) {
      if (grid(y)(x) == "#") 1 else 0
    }
    else
      0
  }

  // init grid
  // . stands for dead and # for alive
  private def initGrid(xMax: Int, yMax: Int, aliveCells: Seq[(Int, Int)]): Array[Array[String]] = {
    val grid = Array.ofDim[String](yMax, xMax)
    for (i <- 0 until yMax;
         j <- 0 until xMax)
      grid(i)(j) = "."
    aliveCells.foreach {
      case (x, y) => grid(y)(x) = "#"
    }
    grid
  }

}
