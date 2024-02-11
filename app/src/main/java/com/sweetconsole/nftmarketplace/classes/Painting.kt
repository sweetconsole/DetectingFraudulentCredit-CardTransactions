package com.sweetconsole.nftmarketplace.classes

import kotlin.properties.Delegates

class Painting {
    lateinit var id: String
    lateinit var name: String
    lateinit var description: String
    lateinit var author: String
    lateinit var url: String
    var price by Delegates.notNull<Int>()
    var bet by Delegates.notNull<Int>()
}