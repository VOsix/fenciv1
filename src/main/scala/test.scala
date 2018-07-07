object test {
  def main(args: Array[String]): Unit = {
    val str = List("吃糠咽菜低配")
    str.map(t=>HanlLp.fenci.tokenizer(t)).filter(t=>t.nonEmpty).foreach(println)
  }
}
