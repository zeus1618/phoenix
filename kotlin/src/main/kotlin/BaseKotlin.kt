
fun main(){
    println("Hello Kotlin")
    val pi = 3.14

    println("Value of pi is : " + pi)
    
    var int : Int = Int.MAX_VALUE
    var sh: Short = Short.MAX_VALUE
    var by: Byte = Byte.MAX_VALUE
    var l: Long = Long.MAX_VALUE

    var f: Float = Float.MAX_VALUE
    var d: Double = Double.MAX_VALUE

    var c: Char = 'K'
    var s: String = "Hello Kotlin"
    
    var bool: Boolean = true
    

    println("int = $int" + "\n" + "sh = $sh" + "\n" + "by = $by" + "\n" + "l = $l")
    println("\n\n" + "f = $f" + "\n" + "d = $d")
    println("\n\n" + "c = $c" + "\n" + "s = $s")
    println("\n\n" + "bool = $bool")

    println("\n" + "=".repeat(128))

    if(c.equals("H")){
        println("condition is true")
    } else {
        println("condition is false")
    }

    val day: Byte = 3;
    when(day.toInt()){
        1 -> println("Day is Monday")
        2 -> println("Day is Tuesday")
        3 -> println("Day is Wednesday")
        4 -> println("Day is Thursday")
        5 -> println("Day is Friday")
        6, 7 -> println("Day is Weekend")
        else -> println("Invalid day")
    }

    println("\n" + "=".repeat(128))

    for(i in 1..5){
        println("For Loop pos : $i")
    }
    println("\n" + "-".repeat(64))

    var k = 0
    while(k<=7){
        k+=1
        if(k%2==0){
            println("skipping 2 multiples")
            continue
        }
        println("While Loop pos : $k")
    }
    
    println("\n" + "-".repeat(64))

    k=0
    do{
        ++k
        if(k%3==0){
            println("First sighting of multiple of 3")
            break
        }
        println("Do-While Loop pos : $k")
    } while(k<=7)

    println("\n" + "=".repeat(128))
    

    val arr = arrayOf<Any>(0, "apple", "b", "c", "d", 1, 2)
    arr.forEach { value -> run{
            if(value is Int){
                println("Int : $value")
            } else {
                println("Not-Int : $value") 
            }
        }
    }

    println("\n" + "-".repeat(64))

    for(j in arr.indices){
        if(arr[j] is Int){
                println("Int : ${arr[j]}")
            } else {
                println("Not-Int : ${arr[j]}")
            }
    }

    println("\n" + "-".repeat(64))
    
    val range1 = 1..5
    val range2 = 1 until 5
    for(j in range1){
        println("Range1 val : $j")
    }
    println("\n" + "-".repeat(64))

    for(j in range2){
        println("Range2 val : $j")
    }
}