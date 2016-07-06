package com.examples

package object Math {
    val PI = 3.1415
    val THETA = 2.0
}

class Computation {
    def computeArea(r: Double) = Math.PI * r * r
}

object ObjMain {
    def main(args: Array[String]):Unit = {
	println(new Computation().computeArea(4))
    }
}
