//Range
val r = 1 to 10
println(r)

r.foreach(x => println(s"Value is ${x}"))
println(s"Length of r is ${r.length}")

//Filter. Applies evaluation to each value in the Range
val f = r.filter{_%2 == 0}

val v = "Hello World, I'm here"

//Can also apply filter to each character of a String
val s = v.filter(_.isUpper)

//Works the same way as above
val t = v.filter(Character.isUpperCase(_))
//Check if a string has upper case
val hasUpperCase = s.exists(_.isUpper)
