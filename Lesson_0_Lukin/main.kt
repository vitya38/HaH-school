fun function(n: Int): Array<Array<Int>>{
    var array = arrayOf<Array<Int>>()
    var used = arrayOf<Int>()
    for(i in 0 until n){
        var m = (1..10000).random()
        while (used.contains(m))
            m = (1..10000).random()
        used+=m
        var arr = arrayOf<Int>()
        for(j in 0 until m)
            arr+=(1..Int.MAX_VALUE).random()
        array+=arr
    }
    for(i in 0 until n){
        array[i].sort()
        if(i%2==1)
            array[i].reverse()
    }
    return array
}

fun main(){
    val n = readLine()!!.toInt()
    var array =function(n)
    for(i in 0 until n){
        for(j in 0 until array[i].size){
            print(array[i][j])
            print('\t')
        }
        print('\n')
    }
}