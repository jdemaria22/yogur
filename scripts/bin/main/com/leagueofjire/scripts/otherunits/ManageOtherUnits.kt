package com.leagueofjire.scripts.otherunits


interface ManageOtherUnits {
    val traps: ArrayList<Trap>
    val clones: ArrayList<Clone>
    val wards: ArrayList<Ward>
    val listWardAwareness: ArrayList<WardAwareness>
    fun init(): Boolean
}