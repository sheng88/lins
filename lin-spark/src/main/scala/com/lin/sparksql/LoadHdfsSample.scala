package com.lin.sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object LoadHdfsSample {

  case class Person(fname:String, lname:String)

  def main(args: Array[String]):Unit = {
    //The entry point to programming Spark with the Dataset and DataFrame API is to create a Spark session
   // val sparkConfig = new SparkConf
   // sparkConfig.setMaster("local[*]")
  //  sparkConfig.setAppName("Lins Spark HDFS SQL Sample")

    val spark = SparkSession.builder.appName("Lins Spark HDFS SQL Sample").master("local[*]").getOrCreate()

    val df = spark.read.json("/dev/com/lin/person/*")

    println("number of line = " + df.count())
    df.show()

    df.printSchema()

    val dfResult = df.select("FirstName", "Phones").show

    val dfResult2 = df.groupBy("FirstName").count().show

    //Create a table and run SQL against the table
    df.createOrReplaceTempView("borrowers")

    val dfResult3 = spark.sql("SELECT FirstName, LastName FROM borrowers").as[Person]
    dfResult3.show()


  }
}
