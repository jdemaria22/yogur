package com.leagueofjire.core.native

import com.sun.jna.Pointer
import com.sun.jna.platform.win32.WinDef
import com.sun.jna.platform.win32.WinUser
import com.sun.jna.ptr.IntByReference
import com.sun.jna.win32.StdCallLibrary
import com.sun.jna.platform.win32.WinDef.BOOL

object UserLib32 : DirectNativeLib("user32") {
	external fun SendInput(nInputs: WinDef.DWORD, pInputs: WinUser.INPUT, cbSize: Int): WinDef.DWORD
	external fun GetKeyState(nVirtKey: Int): Short
	external fun BlockInput(fBlockIt: BOOL?): BOOL?
	external fun mouse_event(dwFlags: Int, dx: Int, dy: Int, dwData: Int, dwExtraInfo: Long)
	external fun keybd_event(dwFlags: Int, dx: Int, dy: Int, dwData: Int)
	external fun SetWindowDisplayAffinity(window: Pointer, affinity: Int): Boolean
	external fun EnumWindows(lpEnumFunc: WinUser.WNDENUMPROC, userData: Pointer?): Boolean
	external fun GetWindowTextA(hWnd: Pointer, lpString: ByteArray, nMaxCount: Int): Int
	external fun GetWindow(hWnd: Pointer, uCmd: Int): Pointer
	
	interface WndEnumProc : StdCallLibrary.StdCallCallback {
		fun callback(hwnd: Long): Boolean
	}
	
	external fun SetWindowDisplayAffinity(hwnd: Long, dwAffinity: Long): Boolean
	external fun SetActiveWindow(hwnd: Long): Long
	external fun FindWindowA(s: String?, s1: String?): Long
	external fun GetWindowLongA(hwnd: Long, i: Int): Int
	external fun SetWindowLongA(hwnd: Long, i: Int, i1: Int): Int
	external fun SetWindowPos(hwnd: Long, hwnd1: Long, i: Int, i1: Int, i2: Int, i3: Int, i4: Int): Boolean
	external fun SetForegroundWindow(hwnd: Long): Boolean
	external fun SetFocus(hwnd: Long): Long
	external fun IsWindowVisible(hwnd: Long): Boolean
	external fun ShowWindow(hwnd: Long, i: Int): Boolean
	external fun GetWindowThreadProcessId(hwnd: Long, intByReference: IntByReference?): Int
	external fun AttachThreadInput(dword: Long, dword1: Long, b: Boolean): Boolean
	external fun EnumWindows(enumProc: WndEnumProc): Boolean
	external fun GetForegroundWindow(): Long
	
}