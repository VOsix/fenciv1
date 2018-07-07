package HanlLp

import java.io.{BufferedReader, InputStream, InputStreamReader}

import com.hankcs.hanlp.HanLP
import com.hankcs.hanlp.dictionary.CustomDictionary

import scala.collection.immutable

object fenci extends Serializable {
    addWords
    def addWords {
      val fis: InputStream = this.getClass.getClassLoader.getResourceAsStream("ext.dic")
      val br = new BufferedReader(new InputStreamReader(fis))
      //先读一次，看是否为空
      var str: String = br.readLine()
      while (str != null) {
        CustomDictionary.add(str.trim)
        str = br.readLine()
      }
      br.close()
    }

    def tokenizer(string: String): Seq[String] = {
      val p1 = new scala.util.matching.Regex("[a-zA-Z]+|[\u4e00-\u9fa5]")
      val titleTermList  = HanLP.segment(string)
      val titleWords:Seq[String] = for(i <- 0 until  titleTermList.size) yield {titleTermList.get(i).word.replaceAll(" ","")}
      titleWords.map(t=>p1.findAllIn(t).mkString("").toLowerCase).filter(_.nonEmpty).filter(t=>t.length<=10)
    }

}
