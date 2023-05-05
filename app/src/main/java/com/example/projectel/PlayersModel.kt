package com.example.projectel

class PlayersModel(
    var nameAndNumber: String,
    var stat1: String,
    var stat2: String,
    var stat3: String,
    var image: Int
) {
    //lateinit var nameAndNumber : String;
    //lateinit var stat1 : String;
    //lateinit var stat2 : String;
    //lateinit var stat3 : String;
    //lateinit var image : Integer;

    fun getnameAndNumber() : String{
        return this.nameAndNumber;
    }
    fun getstat1() : String{
        return this.stat1;
    } fun getstat2() : String{
        return this.stat2;
    }
    fun getstat3() : String{
        return this.stat3;
    }
    fun getimage() : Int{
        return this.image;
    }

}