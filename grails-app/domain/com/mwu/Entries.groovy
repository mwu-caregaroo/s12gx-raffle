package com.mwu

class Entries {
	String name
	Boolean winner
	
    static constraints = {
		name blank:false
		winner nullable:true
    }
}
