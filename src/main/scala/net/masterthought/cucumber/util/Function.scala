package net.masterthought.cucumber.util;

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

class Function[T, R](inv: Invocation[T, R]) extends Function1[T, R] {
  override def apply(t: T): R = inv.call(t)
}

object Function {
  def getInstance[T, R](inv: Invocation[T, R]) = new Function[T, R](inv)
  def map[T1, T2](input: java.util.List[T1])(f: T1 => T2): java.util.List[T2] =
    input.map(f(_))
  def filter[T](input: java.util.List[T])(f: T => java.lang.Boolean): java.util.List[T] =
    input.filter(f(_))
}
