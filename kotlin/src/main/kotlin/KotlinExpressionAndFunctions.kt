fun main(){
    /*
        Lambda Expression looks like
        val lambdaVariable: (DataType1, DataType2) -> ReturnType = {v1: DataType1, v2: DataType2 -> methodBody}
    */
    val sum = {x:Int ,y:Int -> x+y}
    val add : (Int,Int) -> Int = {x,y -> x+y}
    println("Sum of 4 and 5 is ${sum(4,5)}")
    println("Adding 3 and 5 to get ${add(3,5)}")

    val voidLambda : (String) -> Unit = {name -> println("Hi $name")}
    voidLambda("Iqbal")
    voidLambda.invoke("Kanchi")

    /* 
        Anonymous function - is like lambda but explicity has return type on definition side
        val anonymous: (DataType1, DataType2) -> ReturnType = fun(v1: DataType1, v2: DataType2): ReturnType -> {methodBody + return ReturnType}
     */

    val mul = fun(x:Int, y:Int):Int {
        println("Multiplying $x and $y")
        return x*y
    }
    println("Result ${mul(4,5)}")

    /*
        Higher Order Functions : Accept one or more functions and/or variables as parameters and returns a function or variable as result
    */
    println("=".repeat(128))
    fun operateOnNumbers(x: Int, y:Int, operation: (Int,Int)->Int):Int{
        return operation(x,y)
    }

    val result = operateOnNumbers(5, 4, mul)
    println("Result of operation is : $result")
}