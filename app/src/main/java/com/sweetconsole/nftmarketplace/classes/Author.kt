package com.sweetconsole.nftmarketplace.classes

import kotlin.properties.Delegates

class Author {
    lateinit var id: String
    lateinit var name: String
    lateinit var bio: String
    lateinit var photo: String
    var sales by Delegates.notNull<Int>()
}