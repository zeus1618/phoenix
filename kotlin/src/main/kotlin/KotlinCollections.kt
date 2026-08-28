
fun main(){
    val fruits = listOf("Apple", "Banana", "Kiwi")
    printList(fruits)
    //fruits.add("Papaya") Not allowed, since it is immutable

    println("=".repeat(128))
    // val mFruits = mutableListOf("Apple", "Banana", "Kiwi")
    val mFruits = fruits.toMutableList()
    printList(mFruits)
    
    println("-".repeat(64))
    mFruits.add("Papaya")
    printList(mFruits)

    println("=".repeat(128))
    val days = setOf("monday", "tuesday", "wednesday", "thursday", "thursday", "friday", "saturday", "sunday")
    printSet(days)
    
    println("-".repeat(64))
    val mDays = days.toMutableSet()
    mDays.add("mon")
    printSet(mDays)

    println("=".repeat(128))
    val months = mapOf(
        "1" to "January",
        "2" to "February",
        "3" to "March",
        "4" to "April",
        "5" to "May",
        "6" to "June"
    )
    printMap(months)

    println("-".repeat(64))
    val mMonths = months.toMutableMap()
    mMonths.put("7", "July")
    printMap(mMonths)

}

fun printList(list: List<Any>){
    list.forEach{ v->
        println(v)
    }
}

fun printSet(set: Set<Any>){
    set.forEachIndexed { i,v ->
        println("At $i : $v")
     }
}

fun <K,V> printMap(map: Map<K, V>){
    map.entries.forEach{e -> 
        println("For key ${e.key} : ${e.value}")
    }
}