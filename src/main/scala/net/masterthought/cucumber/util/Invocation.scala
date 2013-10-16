package net.masterthought.cucumber.util;

abstract class Invocation[-T, +R] {
  def call(t: T): R
}
