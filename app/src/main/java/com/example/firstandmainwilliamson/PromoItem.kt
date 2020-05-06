package com.example.firstandmainwilliamson

data class PromoItem(var name: String,
                     var description: String,
                     var store: String,
                     var expiration: String,
                     var pictureUrl: String
){
    constructor():this("","","","", "")
}