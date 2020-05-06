package com.example.firstandmainwilliamson

import java.util.*

data class StoreItem(var name: String,
                     var description: String,
                     var type: String,
                     var pictureUrl: String
) {
    constructor():this("","","", "")
}
