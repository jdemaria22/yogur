package com.leagueofjire.core.offsets

object Offsets {
	//Para buscar los patrones en IDA -> CTRL + B y tomar el primer mov dword
	//Para crear patron en IDA -> CTRL + ALT + S sobre algun xref que este refiriendose al offset buscado.
	const val GameTime = 0x310DF84L //F3 0F 11 05 ? ? ? ? 8B 49
	const val ObjectManager = 0x1879830L //89 ? ? ? ? ? 57 C7 06 ? ? ? ? 66 C7 46 04 ? ?
	const val LocalPlayer = 0x31168D4L //51 8B 0D ? ? ? ? 85 C9 74 26
	const val UnderMouseObject = 0x310A9D8L //8B 0D ? ? ? ? C7 04 24 ? ? ? ? FF 74 24 58 - 89 0D ? ? ? ? C7 41 ? ? ? ? ? C7 41 ? ? ? ? ? C7 01 ? ? ? ?
	const val ZoomClass = 0x310D610L //A3 ? ? ? ? 83 FA 10 72 32
	const val Chat = 0x3116F60L //8B 0D ? ? ? ? 8A D8 85
	const val ViewProjMatrices = 0x3140F40L //B9 ? ? ? ? 0F 10 80
	const val Renderer = 0x3143DE0L //8B 0D ? ? ? ? 50 E8 ? ? ? ? 5E 8B
	const val MinimapObject = 0x310F288L //8B 1D ? ? ? ? 85 DB 74 19


	const val ObjIndex = 0x20L //always the same
	const val ObjTeam = 0x4CL //always the same
	const val ObjMissileName = 0x6CL //always the same
	const val ObjNetworkID = 0xCCL //always the same
	const val ObjPos = 0x1F4L //11.18
	const val ObjMissileSpellCast = 0x250L //always the same
	const val ObjVisibility = 0x28CL
	const val ObjSpawnCount = 0x2A0L
	const val ObjSrcIndex = 0x2ACL //always the same
	const val ObjMana = 0x2B4L
	const val ObjMaxMana = 0x2C4L
	const val ObjRecallState = 0xDA8L
	const val ObjHealth = 0xDB4L
	const val ObjMaxHealth = 0xDC4L
	const val ObjAbilityHaste = 0x110CL
	const val ObjLethality = 0x11F8L
	const val ObjArmor = 0x12E4L
	const val ObjBonusArmor = 0x12F0L
	const val ObjMagicRes = 0x12ECL
	const val ObjBonusMagicRes = 0x12F0L
	const val ObjBaseAtk = 0x12BCL
	const val ObjBonusAtk = 0x1234L
	const val ObjMoveSpeed = 0x12FCL
	const val ObjSpellBook = 0x27F8L
	const val ObjTransformation = 0x3040L//always the same
	const val ObjName = 0x2BE4L
	const val ObjLvl = 0x339CL
	const val ObjSizeMultiplier = 0x12D4L
	const val ObjExpiry = 0x298L//always the same
	const val ObjCrit = 0x12E0L
	const val ObjCritMulti = 0x12D0L
	const val ObjAbilityPower = 0x1788L
	const val ObjAtkSpeedMulti = 0x12B8L
	const val ObjAtkRange = 0x1304L
	const val ObjTargetable = 0xD1CL
	const val ObjInvulnerable = 0x3ECL
	const val ObjIsMoving = 0x32EFL//always the same
	const val ObjDirection = 0x1C10L//0x1BD8L
	const val ObjItemList = 0x33E8L//always the same
	const val ObjExpierience = 0x3384L
	const val ObjMagicPen = 0x11DCL
	const val ObjMagicPenMulti = 0x11E4L
	const val ObjAdditionalApMulti = 0x1230L
	const val ObjManaRegen = 0x1150L
	const val ObjHealthRegen = 0x12F8L
	const val ObjPercentArmorPen = 0x11E0L
	const val ObjPercentMagicPen = 0x11E4L
	const val ObjLifeSteal = 0x12A0L
	const val ObjCritChance = 0x12E0L

	const val ObjExperience = 0x3394L
	const val ObjAvaiableSpellPoints = 0x33CCL

	const val ObjSummonerSpell_D = 0x3858L
	const val ObjSummonerSpell_F = 0x3864L
	const val ObjKeystone = 0x3878L



	const val MaxZoom = 0x20L //always the same


	const val ChatIsOpen = 0x73CL //C7 86 ? ? ? ? ? ? ? ? E8 ? ? ? ? 83 C4 04 85 C0 75 30 F6 86 ? ? ? ? ? 75 1B 38 86 ? ? ? ?

	const val SpellBookActiveSpellCast = 0x20L //always the same
	const val SpellBookSpellSlots = 0x488L //always the same


	const val ObjBuffManager = 0x21B8L //11.18,0x21B8 the ones below are always the same //8D 83 ? ? ? ? 50 8D AB ? ? ? ? // 4 first characters are the offset
	const val BuffManagerEntriesArray = 0x10L
	const val BuffManagerEndArray = 0x14L
	const val BuffEntryBuff = 0x8L
	const val BuffType = 0x4L
	const val BuffEntryBuffStartTime = 0xCL
	const val BuffEntryBuffEndTime = 0x10L
	const val BuffEntryBuffCount = 0x74L
	const val BuffEntryBuffCountAlt = 0x24L
	const val BuffEntryBuffCountAlt2 = 0x20L
	const val BuffName = 0x8L
	const val BuffEntryBuffNodeStart = 0x20L
	const val BuffEntryBuffNodeCurrent = 0x24L


	//always the same
	const val ItemListItem = 0xCL
	const val ItemInfo = 0x20L
	const val ItemInfoId = 0x68L

	//always the same
	const val CurrentDashSpeed = 0x1D0L
	const val IsDashing = 0x398L
	const val DashPos = 0x1FCL
	const val IsMoving = 0x198L
	const val NavBegin = 0x1BCL
	const val NavEnd = 0x1C0L//0x32B0 12.2


	//never change
	const val RendererWidth = 0xCL
	const val RendererHeight = 0x10L

	//spellslots never change
	const val SpellSlotLevel = 0x20L
	const val SpellSlotTime = 0x28L
	const val SpellSlotCharges = 0x58L
	const val SpellSlotTimeCharge = 0x78L
	const val SpellSlotDamage = 0x94L
	const val SpellSlotSpellInfo = 0x144L
	const val SpellInfoSpellData = 0x44L
	const val SpellDataSpellName = 0x6CL
	const val SpellDataMissileName = 0x6CL
	const val SpellSlotSmiteTimer = 0x64L
	const val SpellSlotSmiteCharges = 0x58L
	const val SpellSlotItemMaxStackCount = 0x60
	const val SpellSlotItemNextRefillTime = 0x64
	const val SpellSlotItemStackState = 0x70
	const val SpellSlotItemActiveState = 0x74
	const val SpellSlotItemCoolDown = 0x78
	const val SpellSlotItemTargetingClient = 0x138
	const val SpellSlotItemName = 0x13C


	//these never change
	const val ObjectMapCount = 0x2CL
	const val ObjectMapRoot = 0x28L
	const val ObjectMapNodeNetId = 0x10L
	const val ObjectMapNodeObject = 0x14L


	//these never change
	const val SpellCastSpellInfo = 0x8L
	const val SpellCastStartTime = 0x544L
	const val SpellCastStartTimeAlt = 0x534L
	const val SpellCastCastTime = 0x4C0L
	const val SpellCastStart = 0x80L
	const val SpellCastEnd = 0x8CL
	const val SpellCastSrcIdx = 0x68L
	const val SpellCastDestIdx = 0xC0L


	const val MinimapObjectHud = 0x110L
	const val MinimapHudPos = 0x44L
	const val MinimapHudSize = 0x4CL


	// not in use, no need of updating
	const val AiManager = 0x2C98L
	const val AiManagerStartPath = 0x1CCL // Funciona
	const val AiManagerEndPath = 0x1D8L // Funciona
	const val AiManagerClickRightPosition = 0x10L
	const val AiManagerCastPosition = 0x388L
	const val AiManagerIsMoving = 0x1C0L // Funciona
	const val AiManagerOwnPosition = 0x3E4L // Funciona
	const val AiManagerMoveSpeed = 0x1BCL // Funciona
	const val AiManagerServPosition = 0x2E4L // Funciona
	const val AiManagerIsDashing = 0x3C0L //controlar con Ghost Spell
	const val AiManagerCurrentSegment = 0x1C4L
	const val AiManagerDashSpeed = 0x1F8L //Ghost Spell . si no viene el speed is not dashing
	const val AiManagerVelocity = 0x2F0L
	const val AiManagerPointerPath = 0x1E4L

	// TestGamePing
	const val TestGamePingA = 0x30E1604L //A1 ?? ?? ?? ?? 85 C0 74 07 C7 40 ?? ?? ?? ?? ?? C2
	const val TestGamePingB = 0x3cL
	const val TestGamePingC = 0x28L

	const val MissileMap = 0x34F848CL //12.1
	const val MissileMapCount = 0x78L //12.1
	const val MissileMapRoot = 0x74L //12.1
	const val MissileMapKey = 0x10L //12.1
	const val MissileMapVal = 0x14L //12.1
	const val MissileSpellInfo = 0x8L //12.1
	const val MissileSrcIdx = 0x6CL //12.1
	const val MissileDestIdx = 0xC0L //12.1
	const val MissileStartPos = 0x90L //12.1
	const val MissileEndPos = 0x9CL //12.1

}