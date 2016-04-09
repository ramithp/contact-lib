package com.contact.spec

import org.scalatest.Matchers
import scala.collection.mutable.Stack
import org.scalatest.FlatSpec

class TestSpec extends FlatSpec with Matchers {
  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    assert(stack.pop() == 2)
    assert(stack.pop() == 1)
  }
}