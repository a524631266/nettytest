package com.homezhangll.datastruct.hash

import java.security.MessageDigest

import sun.security.provider.MD5

object MD5TEST {
  def digiest(method: String = "MD5"): Unit ={
    val md5 = MessageDigest.getInstance(method)
    val encoded = md5.digest("asdfasd".getBytes)
    val d = encoded.map("%02x".format(_)).mkString
    println(d)
  }
  def main(args: Array[String]): Unit = {
    digiest("MD5")
    digiest("SHA")
    digiest("SHA-1")
    digiest("SHA-256")
  }
}
