//import ScanCode.DIK_A
//import ScanCode.DIK_D
//import ScanCode.DIK_F
//import ScanCode.DIK_S
//import com.leagueofjire.core.game.IGameLocalPlayer.localPlayer
//import com.leagueofjire.core.game.IGameUnitManager.champions
//import com.leagueofjire.core.game.unit.IGameUnit
//import com.leagueofjire.game.unit.champion.buff.GameChampionBuff
//import com.leagueofjire.scripts.utils.core.IGeometry
//import com.leagueofjire.scripts.utils.core.ITargeting
//import com.yogur.panel.scripts.AutoSpell
//import com.yogur.panel.scripts.CooldownTracker.infoSpellTrackerIsEnabled
//import it.unimi.dsi.fastutil.objects.ObjectList
//import java.awt.MouseInfo
//import java.awt.event.KeyEvent.VK_3
//import java.util.Optional
//
//var flagQ = false
//var flagW = false
//var flagE = false
//var flagR = false
//var qTimer = 0L
//var wTimer = 0L
//var eTimer = 0L
//var rTimer = 0L
//val toggleSpace = KeyEvent.VK_5
//val targeting = ITargeting()
//val geometry = IGeometry()
//
//val infoQSpell = getInfoSpell(0).get()
//val infoWSpell = getInfoSpell(1).get()
//val infoESpell = getInfoSpell(2).get()
//val infoRSpell = getInfoSpell(4).get()
//////////////////////////Spell Q + 55%AP////////////////////////
//val qChampDamageLvl1 = 80F
//val qChampDamageLvl2 = 110F
//val qChampDamageLvl3 = 140F
//val qChampDamageLvl4 = 170F
//val qChampDamageLvl5 = 200F
//////////////////////////Spell Q - ManaCost////////////////////////
////val qManaCost = 50F
//////////////////////////Damage W + 60%AP////////////////////////
//val wChampDamageLvl1 = 75F
//val wChampDamageLvl2 = 120F
//val wChampDamageLvl3 = 165F
//val wChampDamageLvl4 = 210F
//val wChampDamageLvl5 = 255F
//////////////////////////Spell W - ManaCost////////////////////////
//val wManaCostLvl1 = 60F
//val wManaCostLvl2 = 70F
//val wManaCostLvl3 = 80F
//val wManaCostLvl4 = 90F
//val wManaCostLvl5 = 100F
//////////////////////////Damage E + 45%AP////////////////////////
//val eChampDamageLvl1 = 70F
//val eChampDamageLvl2 = 95F
//val eChampDamageLvl3 = 120F
//val eChampDamageLvl4 = 145F
//val eChampDamageLvl5 = 170F
//////////////////////////Spell E - ManaCost////////////////////////
//val eManaCostLvl1 = 70F
//val eManaCostLvl2 = 75F
//val eManaCostLvl3 = 80F
//val eManaCostLvl4 = 85F
//val eManaCostLvl5 = 90F
//////////////////////////Damage R + 25%AP////////////////////////
//val rChampDamageLvl1 = 100F
//val rChampDamageLvl2 = 200F
//val rChampDamageLvl3 = 300F
//////////////////////////Spell R - ManaCost////////////////////////
////val rManaCost = 100F
//val pasiva = "Los hechizos de Brand prenden a sus objetivos en llamas e infligen daño a lo largo de 4 seg. (Se acumula hasta 3 veces)\n            Si Brand mata a un enemigo en llamas, recupera maná.\n            Llamarada se vuelve inestable cuando alcanza el máximo de acumulaciones contra un campeón o un monstruo.\n            Detona en 2 seg, lo que aplica efectos de hechizo e inflige daño masivo en una zona alrededor de la víctima."
//
//if (getLocalPlayer().name == "brand"){
//    var lastToggle = -1F
//    val targeting = ITargeting()
//    eachChampion {
//        if(chatOpen.chatOpen) return@eachChampion
//        if(!AutoSpell.isEnabledSpecific()) return@eachChampion
//        if (infoSpellTrackerIsEnabled()){
//            val color = Color.SALMON
//            shapes.color = color
//            val y = renderer.height / 2
//            font.color = color
//            font.text("Daño Q: "+getQDamage(), 9, y+50)
//            font.text("Daño W: "+getWDamage(), 9, y+100)
//            font.text("Daño E: "+getEDamage(), 9, y+150)
//            font.text("Daño R: "+getRDamage(), 9, y+200)
//            font.text("Pasiva: $pasiva", 9, y+250)
//        }
//        val timeToggle = time.seconds
//
//        /* Q */
//        if (timeToggle - lastToggle >= 0.5F && KeyboardInput.isKeyPressed(KeyEvent.VK_1)) {
//            lastToggle = timeToggle
//            val infoSpell = getInfoSpell(0)
//            if (infoSpell.isEmpty) return@eachChampion
//            val remaining = gameContext.me.localPlayer.spells[0].readyAtSeconds - time.seconds
//            val ready = remaining <= 0
//            if (!ready) return@eachChampion
//            val spell = infoSpell.get()
//            val range = spell.castRange
//            val target = targeting.getBestTargetForRange(gameContext.unitManager.champions, gameContext.me.localPlayer, range)
//            if (target.isEmpty) return@eachChampion
//            val screeToPositionForCollision = targeting.castPointForCollision(target, spell, gameContext.me.localPlayer, renderer, 0.8F, false)
//            val oldPos = MouseInfo.getPointerInfo().location
//            if (screeToPositionForCollision.isEmpty) return@eachChampion
//            val thread = Thread{
//                run{
//                    MouseInput.mouseMove(screeToPositionForCollision.get().x, screeToPositionForCollision.get().y)
//                    MouseInput.pressKeyboardButtom(DIK_A)
//                    Thread.sleep(15)
//                    MouseInput.mouseMove(oldPos.x, oldPos.y)
//                    Thread.interrupted()
//
//                }
//            }
//            thread.start()
//            return@eachChampion
//        }
//
//        /* W */
//        if (timeToggle - lastToggle >= 0.5F && KeyboardInput.isKeyPressed(KeyEvent.VK_2)) {
//            val infoSpell = getInfoSpell(1)
//            if (infoSpell.isEmpty) return@eachChampion
//            val remaining = gameContext.me.localPlayer.spells[1].readyAtSeconds - time.seconds
//            val ready = remaining <= 0
//            if (!ready) return@eachChampion
//            val spell = infoSpell.get()
//            val range = spell.castRange
//            val target = targeting.getBestTargetForRangeArea(gameContext.unitManager.champions, gameContext.me.localPlayer, range)
//            if (target.isEmpty) return@eachChampion
//            val screeToPositionForCollision = targeting.castPointForCircle(target, spell, gameContext.me.localPlayer, renderer)
//            val oldPos = MouseInfo.getPointerInfo().location
//            if (screeToPositionForCollision.isEmpty) return@eachChampion
//            val thread = Thread{
//                run{
//                    MouseInput.mouseMove(screeToPositionForCollision.get().x, screeToPositionForCollision.get().y)
//                    MouseInput.pressKeyboardButtom(DIK_S)
//                    Thread.sleep(15)
//                    MouseInput.mouseMove(oldPos.x, oldPos.y)
//                    Thread.interrupted()
//                }
//            }
//            thread.start()
//            return@eachChampion
//        }
//
//        /* E */
//        if (timeToggle - lastToggle >= 0.5F && KeyboardInput.isKeyPressed(VK_3)) {
//            val infoSpell = getInfoSpell(2)
//            if (infoSpell.isEmpty) return@eachChampion
//            val remaining = gameContext.me.localPlayer.spells[2].readyAtSeconds - time.seconds
//            val ready = remaining <= 0
//            if (!ready) return@eachChampion
//            val spell = infoSpell.get()
//            val range = spell.castRange
//            val target = targeting.getBestTargetForRangeArea(gameContext.unitManager.champions, gameContext.me.localPlayer, range)
//            if (target.isEmpty) return@eachChampion
//            val screeToPositionForCollision = targeting.castPointForCollision(target, spell, gameContext.me.localPlayer, renderer, 0F, true)
//            val oldPos = MouseInfo.getPointerInfo().location
//            if (screeToPositionForCollision.isEmpty) return@eachChampion
//            val thread = Thread{
//                run{
//                    MouseInput.mouseMove(screeToPositionForCollision.get().x, screeToPositionForCollision.get().y)
//                    MouseInput.pressKeyboardButtom(DIK_D)
//                    Thread.sleep(15)
//                    MouseInput.mouseMove(oldPos.x, oldPos.y)
//                    Thread.interrupted()
//
//                }
//            }
//            thread.start()
//            return@eachChampion
//        }
//
//        /* R */
//        if (timeToggle - lastToggle >= 0.5F && KeyboardInput.isKeyPressed(KeyEvent.VK_4)) {
//            val infoSpell = getInfoSpell(3)
//            if (infoSpell.isEmpty) return@eachChampion
//            val remaining = gameContext.me.localPlayer.spells[3].readyAtSeconds - time.seconds
//            val ready = remaining <= 0
//            if (!ready) return@eachChampion
//            val spell = infoSpell.get()
//            val range = spell.castRange
//            val target = targeting.getBestTargetForRangeArea(gameContext.unitManager.champions, gameContext.me.localPlayer, range)
//            if (target.isEmpty) return@eachChampion
//            val screeToPositionForCollision = targeting.castPointForCollision(target, spell, gameContext.me.localPlayer, renderer, 0F, true)
//            val oldPos = MouseInfo.getPointerInfo().location
//            if (screeToPositionForCollision.isEmpty) return@eachChampion
//            val thread = Thread{
//                run{
//                    MouseInput.mouseMove(screeToPositionForCollision.get().x, screeToPositionForCollision.get().y)
//                    MouseInput.pressKeyboardButtom(DIK_D)
//                    Thread.sleep(15)
//                    MouseInput.mouseMove(oldPos.x, oldPos.y)
//                    Thread.interrupted()
//
//                }
//            }
//            thread.start()
//            return@eachChampion
//        }
//
//        /* COMBO */
//        if (timeToggle - lastToggle >= 0.5F && KeyboardInput.isKeyPressed(toggleSpace)){
//            val buffE = targetStuned(champions, localPlayer, 1100F)
//            val qManaCost = getQManaCost()
//            val wManaCost = getWManaCost()
//            val eManaCost = getEManaCost()
//            val rManaCost = getRManaCost()
//            val manaCostCombo1 = rManaCost + eManaCost + qManaCost + wManaCost
//            val target = targeting.getBestTargetForRangeArea(champions, localPlayer, 675F)
//            if (!target.isEmpty){
//                val distance = geometry.distanceBetweenTargets(target.get().position, localPlayer.position)
//                val castDelayTotal = infoQSpell.delay + infoWSpell.delay + infoESpell.delay + infoRSpell.delay + 0.050F
//                if (distance <675){
//                    println(mana)
//                    println(manaCostCombo1)
//                    if (mana >= manaCostCombo1){
//                        val combo = Thread{
//                            run{
//                                if (gameContext.me.localPlayer.spells[2].level > 0 && time.seconds >= gameContext.me.localPlayer.spells[2].readyAtSeconds) {
//                                    eWasPressed()
//                                    if (!flagE) {
//                                        val pressE = Thread {
//                                            run {
//                                                hitE()
//                                            }
//                                        }
//                                        targeting.setAutoSpelling(true, castDelayTotal)
//                                        pressE.start()
//                                        flagE = true
//                                        eTimer = timer()
//                                    }
//                                }
//                                Thread.sleep(200)
//                                if(!buffE.isEmpty){
//                                    if (gameContext.me.localPlayer.spells[0].level > 0 && time.seconds >= gameContext.me.localPlayer.spells[0].readyAtSeconds){
//                                        qWasPressed()
//                                        if(!flagQ){
//                                            val pressQ = Thread {
//                                                run {
//                                                    hitQ()
//                                                }
//                                            }
//                                            pressQ.start()
//                                            flagQ = true
//                                            qTimer = timer()
//                                        }
//                                    }
//                                    Thread.sleep(300)
//                                    if (gameContext.me.localPlayer.spells[1].level > 0 && time.seconds >= gameContext.me.localPlayer.spells[1].readyAtSeconds){
//                                        wWasPressed()
//                                        if(!flagW){
//                                            val pressW = Thread {
//                                                run {
//                                                    hitW()
//                                                }
//                                            }
//                                            pressW.start()
//                                            flagW = true
//                                            wTimer = timer()
//                                        }
//                                    }
//                                    Thread.sleep(200)
//                                    if (gameContext.me.localPlayer.spells[3].level > 0 && time.seconds >= gameContext.me.localPlayer.spells[3].readyAtSeconds){
//                                        rWasPressed()
//                                        if(!flagR){
//                                            val pressR = Thread {
//                                                run {
//                                                    hitR()
//                                                }
//                                            }
//                                            pressR.start()
//                                            flagR = true
//                                            rTimer = timer()
//                                        }
//                                    }
//                                }
//                                targeting.setAutoSpelling(false, 0F)
//                            }
//                        }
//                        combo.start()
//                        return@eachChampion
//                    }
//                }
//            }
//        }
//
//    }
//}
//fun timer(): Long{
//    return System.currentTimeMillis()
//}
//fun qWasPressed(){
//    if ((timer()- qTimer) > 1000){
//        flagQ = false
//    }
//}
//fun wWasPressed(){
//    if ((timer()- wTimer) > 3000){
//        flagW = false
//    }
//}
//fun eWasPressed(){
//    if ((timer()- eTimer) > 1000){
//        flagE = false
//    }
//}
//fun rWasPressed(){
//    if ((timer()- rTimer) > 1000){
//        flagR = false
//    }
//}
//
//fun targetStuned(champions: ObjectList<IGameUnit>, localPlayer: IGameUnit, range: Float): Optional<GameUnit> {
//    var lastHealth = Float.MAX_VALUE
//    var target: Optional<GameUnit> = Optional.empty()
//    champions
//        .forEach {
//            if (!enemyHasE(it.buffs))return@forEach
//            if (targeting.isClone(it)) return@forEach
//            if (it.team == localPlayer.team) return@forEach
//            if (!targeting.isInNormalSkillRange(it, localPlayer, range)) return@forEach
//            if (!it.isTargetable) return@forEach
//            if (!it.isAlive) return@forEach
//            if (!it.isVisible) return@forEach
//            if (lastHealth >= it.health){
//                lastHealth = it.health
//                target =  Optional.of(it)
//            }
//        }
//    if (target.isEmpty) return Optional.empty()
//    return target
//}
//
//fun enemyHasE(buffs: ArrayList<GameChampionBuff>): Boolean{
//    for(buff in buffs){
//        if (targeting.buffIsAlive(buff.startTime, buff.endTime, time)){
//            if (buff.name == "brandablaze"){
//                return true
//            }
//        }
//    }
//    return false
//}
//
//fun hitQ() {
//    val infoSpell = getInfoSpell(0)
//    if (!infoSpell.isEmpty){
//        if (gameContext.me.localPlayer.spells[0].level > 0 && time.seconds >= gameContext.me.localPlayer.spells[0].readyAtSeconds){
//            val target = targeting.getBestTargetForRange(gameContext.unitManager.champions, gameContext.me.localPlayer, infoQSpell.castRange)
//            if (!target.isEmpty) {
//                val screeToPositionForCollision = targeting.castPointForCollision(target, infoQSpell, gameContext.me.localPlayer, renderer, 0.8F, false)
//                if (!screeToPositionForCollision.isEmpty){
//                    hit(DIK_A, screeToPositionForCollision.get())
//                }
//            }
//        }
//    }
//}
//
//fun hitW() {
//    val infoSpell = getInfoSpell(1)
//    if (!infoSpell.isEmpty){
//        if (gameContext.me.localPlayer.spells[1].level > 0 && time.seconds >= gameContext.me.localPlayer.spells[1].readyAtSeconds){
//            val target = targeting.getBestTargetForRangeArea(gameContext.unitManager.champions, gameContext.me.localPlayer, infoWSpell.castRange)
//            if (!target.isEmpty) {
//                val screeToPositionForCollision = targeting.castPointForCircle(target, infoWSpell, gameContext.me.localPlayer,renderer)
//                if (!screeToPositionForCollision.isEmpty){
//                    hit(DIK_S, screeToPositionForCollision.get())
//                }
//            }
//        }
//    }
//}
//
//fun hitE() {
//    val infoSpell = getInfoSpell(2)
//    if (!infoSpell.isEmpty){
//        if (gameContext.me.localPlayer.spells[2].level > 0 && time.seconds >= gameContext.me.localPlayer.spells[2].readyAtSeconds){
//            val target = targeting.getBestTargetForRangeArea(gameContext.unitManager.champions, gameContext.me.localPlayer, infoESpell.castRange)
//            if (!target.isEmpty) {
//                hit(DIK_D, renderer[target.get().position])
//            }
//        }
//    }
//}
//fun hitR() {
//    val infoSpell = getInfoSpell(3)
//    if (!infoSpell.isEmpty){
//        if (gameContext.me.localPlayer.spells[3].level > 0 && time.seconds >= gameContext.me.localPlayer.spells[3].readyAtSeconds){
//            val target = targeting.getBestTargetForRangeArea(gameContext.unitManager.champions, gameContext.me.localPlayer, infoQSpell.castRange)
//            if (!target.isEmpty) {
//                hit(DIK_F, renderer[target.get().position])
//            }
//        }
//    }
//}
//
//fun hit(key: Int, screeToPositionForCollision: ScreenPosition){
//    val oldPos = MouseInfo.getPointerInfo().location
//    val thread = Thread{
//        run{
//            MouseInput.mouseMove(screeToPositionForCollision.x, screeToPositionForCollision.y)
//            MouseInput.pressKeyboardButtom(key)
//            Thread.sleep(15)
//            MouseInput.mouseMove(oldPos.x, oldPos.y)
//            Thread.interrupted()
//        }
//    }
//    thread.start()
//}
//
//fun getQDamage(): Float{
//    if (gameContext.me.localPlayer.spells[0].level == 1){
//        return qChampDamageLvl1 + ((gameContext.me.localPlayer.abilityPower/100) * 55)
//    }
//    if (gameContext.me.localPlayer.spells[0].level == 2){
//        return qChampDamageLvl2 + ((gameContext.me.localPlayer.abilityPower/100) * 55)
//    }
//    if (gameContext.me.localPlayer.spells[0].level == 3){
//        return qChampDamageLvl3 + ((gameContext.me.localPlayer.abilityPower/100) * 55)
//    }
//    if (gameContext.me.localPlayer.spells[0].level == 4){
//        return qChampDamageLvl4 + ((gameContext.me.localPlayer.abilityPower/100) * 55)
//    }
//    if (gameContext.me.localPlayer.spells[0].level == 5){
//        return qChampDamageLvl5 + ((gameContext.me.localPlayer.abilityPower/100) * 55)
//    }
//    return 0F
//}
//
//fun getQManaCost(): Float{
//    return 50F
//}
//
//fun getWDamage(): Float{
//    if (gameContext.me.localPlayer.spells[1].level == 1){
//        return wChampDamageLvl1 + ((gameContext.me.localPlayer.abilityPower/100) * 60)
//    }
//    if (gameContext.me.localPlayer.spells[1].level == 2){
//        return wChampDamageLvl2 + ((gameContext.me.localPlayer.abilityPower/100) * 60)
//    }
//    if (gameContext.me.localPlayer.spells[1].level == 3){
//        return wChampDamageLvl3 + ((gameContext.me.localPlayer.abilityPower/100) * 60)
//    }
//    if (gameContext.me.localPlayer.spells[1].level == 4){
//        return wChampDamageLvl4 + ((gameContext.me.localPlayer.abilityPower/100) * 60)
//    }
//    if (gameContext.me.localPlayer.spells[1].level == 5){
//        return wChampDamageLvl5 + ((gameContext.me.localPlayer.abilityPower/100) * 60)
//    }
//    return 0F
//}
//
//fun getWManaCost(): Float{
//    if (gameContext.me.localPlayer.spells[1].level == 1){
//        return wManaCostLvl1
//    }
//    if (gameContext.me.localPlayer.spells[1].level == 2){
//        return wManaCostLvl2
//    }
//    if (gameContext.me.localPlayer.spells[1].level == 3){
//        return wManaCostLvl3
//    }
//    if (gameContext.me.localPlayer.spells[1].level == 4){
//        return wManaCostLvl4
//    }
//    if (gameContext.me.localPlayer.spells[1].level == 5){
//        return wManaCostLvl5
//    }
//    return 0F
//}
//
//fun getEDamage(): Float{
//    if (gameContext.me.localPlayer.spells[2].level == 1){
//        return eChampDamageLvl1 + ((gameContext.me.localPlayer.abilityPower/100) * 45)
//    }
//    if (gameContext.me.localPlayer.spells[2].level == 2){
//        return eChampDamageLvl2 + ((gameContext.me.localPlayer.abilityPower/100) * 45)
//    }
//    if (gameContext.me.localPlayer.spells[2].level == 3){
//        return eChampDamageLvl3 + ((gameContext.me.localPlayer.abilityPower/100) * 45)
//    }
//    if (gameContext.me.localPlayer.spells[2].level == 4){
//        return eChampDamageLvl4 + ((gameContext.me.localPlayer.abilityPower/100) * 45)
//    }
//    if (gameContext.me.localPlayer.spells[2].level == 5){
//        return eChampDamageLvl5 + ((gameContext.me.localPlayer.abilityPower/100) * 45)
//    }
//    return 0F
//}
//
//fun getEManaCost(): Float{
//    if (gameContext.me.localPlayer.spells[2].level == 1){
//        return eManaCostLvl1
//    }
//    if (gameContext.me.localPlayer.spells[2].level == 2){
//        return eManaCostLvl2
//    }
//    if (gameContext.me.localPlayer.spells[2].level == 3){
//        return eManaCostLvl3
//    }
//    if (gameContext.me.localPlayer.spells[2].level == 4){
//        return eManaCostLvl4
//    }
//    if (gameContext.me.localPlayer.spells[2].level == 5){
//        return eManaCostLvl5
//    }
//    return 0F
//}
//
//fun getRDamage(): Float{
//    if (gameContext.me.localPlayer.spells[3].level == 1){
//        return rChampDamageLvl1 + ((gameContext.me.localPlayer.abilityPower/100) * 25)
//    }
//    if (gameContext.me.localPlayer.spells[3].level == 2){
//        return  rChampDamageLvl2 + ((gameContext.me.localPlayer.abilityPower/100) * 25)
//    }
//    if (gameContext.me.localPlayer.spells[3].level == 3){
//        return  rChampDamageLvl3 + ((gameContext.me.localPlayer.abilityPower/100) * 25)
//    }
//    return 0F
//}
//
//fun getRManaCost(): Float{
//    return 100F
//}