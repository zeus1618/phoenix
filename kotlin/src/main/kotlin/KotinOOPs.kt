fun main(){
    println("Hello")
    sayHello("Jack","30")
    sayHello(age="40")
    sayHello("Jane")
    
    println("=".repeat(128))
    
    println("sum of int ${add(4, 5)}")
    println("sum of float ${add(4.3, 5.3)}")
    
    println("=".repeat(128))

    val lambo = Car("Lamborghini", 2, "petrol")
    lambo.greet("Kanchi")
    lambo.doors()
    lambo.fuelType()
    lambo.fuel = "E10"
    lambo.fuelType()
    lambo.drive()

    println("-".repeat(64))

    val bugati = RacingCar("Bugati", 2, "E20", "Formula 1")
    bugati.greet("Iqbal")
    bugati.whichRaceCar()
    bugati.doors()
    bugati.fuelType()
    bugati.start()
    bugati.drive()
    bugati.stop()

    println("-".repeat(64))

    val ninja = Bike("Ninja")
    ninja.greet("Bham")
    ninja.doors()
    ninja.drive()

    println("-".repeat(64))
    val dzire = CityCar("Dzire", 4, "petrol", Segment.MID)
    dzire.greet("Vinod")
    dzire.doors()
    dzire.fuelType()
    dzire.drive()
    println("Segment for this car is ${dzire.segment}")
    

    println("=".repeat(128))

}

fun sayHello(name : String? = "", age:String = "Not Specified"){
    println("Hello $name and your age is $age")
}

/*
==========================================================================
Begin Overloading
==========================================================================
*/

fun add(x: Int, y: Int) : Int{
    return x+y
}

fun add(x: Double, y: Double) : Double{
    return x+y
}

/*
--------------------------------------------------------------------------
Finish Overloading
--------------------------------------------------------------------------
*/

/*
==========================================================================
Begin OOPs
open : By default classes are final, open is needed to allow inheritance
interface, abstract class, abstract fun
primary constructor
secondary constructor : Either needs this() or super()
enum class

==========================================================================
*/

interface VehicleRunner {
    fun start()
    fun stop()
}

abstract class Vehicle(val name:String) : VehicleRunner{

    init{
        println("New Vehicle Created")
    }
    
    fun drive(){
        println("$name is moving")
    }

    fun greet(rider: String){
        println("Hi $rider, $name welcomes you")
    }

    abstract fun doors()

}

open class Car(name: String, val doors: Int): Vehicle(name){

    init{
        println("New Car Created")
    }

    var fuel: String? = null

    constructor(name:String, doors:Int, fuel: String): this(name, doors){
        this.fuel = fuel
    }

    fun fuelType(){
        println("Fuel type is ${if(fuel!=null) fuel else "Unknown"}")
    }

    override
    fun doors(){
        println("You Car has $doors doors")
    }

    override fun start() {
        println("$name has started")
     }

    override fun stop() {
        println("$name has stopped")
     }
}

class Bike(name: String) : Vehicle(name){
    override
    fun doors(){
        println("Bikes don't have doors")
    }

    override fun start() {
        println("$name has started")
     }

    override fun stop() {
        println("$name has stopped")
     }
}

class RacingCar(name: String, doors:Int, val raceCategory: String): Car(name, doors){
    constructor(name: String, doors: Int, fuel: String, raceCategory: String):this(name, doors, raceCategory){
        this.fuel = fuel
    }
    fun whichRaceCar(){
        println("$name is a $raceCategory car")
    }
}

enum class Segment {
    LOW, MID, HIGH
}

/*
super : needs the class should not have primary constructor
*/

class CityCar: Car{
    var segment: Segment? = null
        get() = field ?: Segment.LOW
        set(value) {
            println("Segment changing from $field to $value")
            field = value
        }
    constructor(name: String, doors: Int, fuel: String): super(name, doors, fuel)
    constructor(name: String, doors: Int, fuel: String, segment: Segment): super(name, doors, fuel){
        this.segment = segment
    }
    init {
        println("Hi, I am city car")
    }
}

/*
--------------------------------------------------------------------------
Finish OOPs
--------------------------------------------------------------------------
*/
