package com.example.janeproj


class Cupon {
    val cupon_name:String
    val cupon_valid:String
    val cupon_store:String
    var cupon_end:Boolean
    var cupon_fav:Boolean

    constructor(cupon_name: String, cupon_valid: String, cupon_store: String, cupon_end:Boolean, cupon_fav:Boolean) {
        this.cupon_name = cupon_name
        this.cupon_valid = cupon_valid
        this.cupon_store = cupon_store
        this.cupon_end=cupon_end
        this.cupon_fav=cupon_fav
    }
}