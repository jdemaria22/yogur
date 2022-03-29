//import com.yogur.panel.scripts.InfoTracker
//
//render {
//    if(!InfoTracker.isEnabled()) return@render
//    val hashMap:HashMap<String,Any> = HashMap()
//
//    hashMap["INFO"] = ""
//    hashMap["attackSpeedMulti: "] = gameContext.me.localPlayer.attackSpeedMulti
//    hashMap["baseAttack: "] = gameContext.me.localPlayer.baseAttack
//    hashMap["abilityPower: "] = gameContext.me.localPlayer.abilityPower
//    hashMap["team: "] = gameContext.me.localPlayer.team
//    hashMap["address: "] = gameContext.me.localPlayer.address
//    hashMap["armor: "] = gameContext.me.localPlayer.armor
//    hashMap["attackRange: "] = gameContext.me.localPlayer.attackRange
//    hashMap["bonusArmor: "] = gameContext.me.localPlayer.bonusArmor
//    hashMap["bonusAttack: "] = gameContext.me.localPlayer.bonusAttack
//    hashMap["crit: "] = gameContext.me.localPlayer.crit
//    hashMap["critMulti: "] = gameContext.me.localPlayer.critMulti
//    hashMap["duration: "] = gameContext.me.localPlayer.duration
//    hashMap["gameplayRadius: "] = gameContext.me.localPlayer.gameplayRadius
//    hashMap["health: "] = gameContext.me.localPlayer.health
//    hashMap["isVisible: "] = gameContext.me.localPlayer.isVisible
//    hashMap["movementSpeed: "] = gameContext.me.localPlayer.movementSpeed
//    hashMap["isAlive: "] = gameContext.me.localPlayer.isAlive
//    hashMap["critmagicResistMulti: "] = gameContext.me.localPlayer.magicResist
//    hashMap["maxHealth: "] = gameContext.me.localPlayer.maxHealth
//    hashMap["positionX: "] = gameContext.me.localPlayer.position.x
//    hashMap["positionY: "] = gameContext.me.localPlayer.position.y
//    hashMap["positionZ: "] = gameContext.me.localPlayer.position.z
//    hashMap["sizeMultiplier: "] = gameContext.me.localPlayer.sizeMultiplier
//    hashMap["spawnCount: "] = gameContext.me.localPlayer.spawnCount
//    hashMap["networkID: "] = gameContext.me.localPlayer.networkID
//    hashMap["basicAtkWindup: "] = gameContext.me.localPlayer.info.basicAtkWindup
//    hashMap["acquisitionRange: "] = gameContext.me.localPlayer.info.acquisitionRange
//    hashMap["baseMoveSpeed: "] = gameContext.me.localPlayer.info.baseMoveSpeed
//    hashMap["basicAtkMissileSpeed: "] = gameContext.me.localPlayer.info.basicAtkMissileSpeed
//    hashMap["gameplayRadius: "] = gameContext.me.localPlayer.info.gameplayRadius
//    hashMap["pathingRadius: "] = gameContext.me.localPlayer.info.pathingRadius
//    hashMap["selectionRadius: "] = gameContext.me.localPlayer.info.selectionRadius
//    hashMap["attackSpeedRatio: "] = gameContext.me.localPlayer.info.attackSpeedRatio
//
//    hashMap["BUFFs"] = ""
//    gameContext.me.localPlayer.buffs.forEach {
//        hashMap[it.name] = "-"  + it.count + "-" + it.endTime
//    }
//
//    InfoTracker.updateInfo(hashMap);
//}
