package com.leagueofjire.scripts.otherunits.core

import com.badlogic.gdx.graphics.Color
import com.leagueofjire.core.game.unit.IGamePosition
import com.leagueofjire.scripts.otherunits.*

class IManageOtherUnits: ManageOtherUnits {
    override val clones: ArrayList<Clone> = arrayListOf()
    override val traps: ArrayList<Trap> = arrayListOf()
    override val wards: ArrayList<Ward> = arrayListOf()
    override val listWardAwareness: ArrayList<WardAwareness> = arrayListOf()

    private val wardAwareness = IWardAwareness()
    override fun init(): Boolean{
        try {
            clones.add(createClone("shaco"))
            clones.add(createClone("leblanc"))
            clones.add(createClone("monkeyking"))
            clones.add(createClone("neeko"))
            clones.add(createClone("fiddlesticks"))

            traps.add(createTrap("caitlyntrap", 50))
            traps.add(createTrap("jhintrap", 140))
            traps.add(createTrap("jinxmine", 50))
            traps.add(createTrap("maokaisproutling", 50))
            traps.add(createTrap("nidaleespear", 50))
            traps.add(createTrap("shacobox", 300))
            traps.add(createTrap("teemomushroom", 75))

            wards.add(createWard("bluetrinket", 900, Color.BLUE))
            wards.add(createWard("jammerdevice", 900, Color.RED))
            wards.add(createWard("perkszombieward", 900, Color.YELLOW))
            wards.add(createWard("sightward", 900, Color.YELLOW))
            wards.add(createWard("visionward", 900, Color.YELLOW))
            wards.add(createWard("yellowtrinket", 900, Color.YELLOW))
            wards.add(createWard("yellowtrinketupgrade", 900, Color.YELLOW))
            wards.add(createWard("ward", 900, Color.YELLOW))

            listWardAwareness.add(wardAwareness
                .createWardAwareness(
                    "blue_to_side_brush"
                    ,IGamePosition(2380.09F, -71.24F,11004.69F)
                    ,IGamePosition(2826.47F, -71.02F,11221.34F)
                    ,IGamePosition(1774F, 52.84F,10856F)))


            listWardAwareness.add(wardAwareness
                .createWardAwareness(
                    "mid_to_wolves_blue_side",
                    IGamePosition(5174.83F, 50.57F, 7119.81F),
                    IGamePosition(4909.10F, 50.65F, 7110.90F),
                    IGamePosition(5749.25F, 51.65F, 7282.75F)))

            listWardAwareness.add(wardAwareness
                .createWardAwareness(
                    "tower_to_wolves_blue_side",
                    IGamePosition(5239.21F, 50.67F, 6944.90F),
                    IGamePosition(4919.83F, 50.64F, 7023.80F),
                    IGamePosition(5574F, 51.74F, 6458F)))

            listWardAwareness.add(wardAwareness
                .createWardAwareness(
                    "red_blue_side",
                    IGamePosition(8463.64F, 50.60F, 4658.71F),
                    IGamePosition(8512.29F, 51.30F, 4745.90F),
                    IGamePosition(8022F, 53.72F, 4258F)))

            listWardAwareness.add(wardAwareness
                .createWardAwareness(
                    "dragon_got_bush",
                    IGamePosition(10301.03F, 49.03F, 3333.20F),
                    IGamePosition(10322.94F, 49.03F, 3244.38F),
                    IGamePosition(10072F, -71.24F, 3908F)))

            listWardAwareness.add(wardAwareness
                .createWardAwareness(
                    "baron_top_bush",
                    IGamePosition(4633.83F, 50.51F, 11354.40F),
                    IGamePosition(4524.69F, 53.25F, 11515.21F),
                    IGamePosition(4824F, -71.24F, 10906F)))

            listWardAwareness.add(wardAwareness
                .createWardAwareness(
                    "red_red_side",
                    IGamePosition(6360.12F, 52.61F, 10362.71F),
                    IGamePosition(6269.35F, 53.72F, 10306.69F),
                    IGamePosition(6824F, 56F, 10656F)))

            listWardAwareness.add(wardAwareness
                .createWardAwareness(
                    "tower_to_wolves",

                    IGamePosition(9586.57F, 59.62F, 8020.29F),
                    IGamePosition(9871.77F, 51.47F, 8014.44F),
                    IGamePosition(9122F, 53.74F, 8356F)))

            listWardAwareness.add(wardAwareness
                .createWardAwareness(
                    "mid_to_wolves",
                    IGamePosition(9647.62F, 51.31F, 7889.96F),
                    IGamePosition(9874.42F, 51.50F, 7969.29F),
                    IGamePosition(9122F, 52.60F, 7606F)))

            listWardAwareness.add(wardAwareness
                .createWardAwareness(
                    "red_bot_side_bush",
                    IGamePosition(12427.00F, -35.46F, 3984.26F),
                    IGamePosition(11975.34F, 66.37F, 3927.68F),
                    IGamePosition(13022F, 51.37F, 3808F)))

            if (listWardAwareness.isEmpty()) return false
            if (traps.isEmpty()) return false
            if (clones.isEmpty()) return false
            if (wards.isEmpty()) return false
            return true
        }
        catch (ex: Exception){
            println("Error in IManageOtherUnits.init")
            return false
        }
    }

    private fun createWard(name: String, radius: Int, color: Color): Ward{
        val ward = IWard()
        return ward.createWard(name, radius, color)
    }
    private fun createTrap(name: String, radius: Int): Trap{
        val trap = ITrap()
        return trap.createTrap(name, radius)
    }
    fun createClone(name: String): Clone{
        val clone = IClone()
        return clone.createClone(name)
    }
}