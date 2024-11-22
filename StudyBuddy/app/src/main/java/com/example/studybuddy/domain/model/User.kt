package com.example.studybuddy.domain.model

data class UserData(
    val userId:String? = "",
    val name: String? = "",
    val number: String? = null,
    val PremiumMember:Boolean? = true,
    val expiry:Long? = null
){
    fun toMap() = mapOf(
        "userId" to userId,
        "userName" to name,
        "number" to number,
        "PremiumMember" to PremiumMember,
        "Expiry" to expiry,

    )
}
