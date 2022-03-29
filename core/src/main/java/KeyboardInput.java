import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.INPUT;

import java.awt.AWTException;
import java.awt.Robot;

public class KeyboardInput {
    public static final int KEYEVENTF_KEYDOWN = 0;
    public static final int KEYEVENTF_KEYUP = 2;

    public static final int KEYEVENT_SCANCODE = 0x0008;
    public static final int KEYEVENT_UP = 0x0002;
    /**
     * Check if a key is pressed.
     *
     * @param vkCode
     *            Key-code. For example: <i>KeyEvent.VK_SHIFT </i>
     *
     * @return {@code true} if key is down. False otherwise.
     */
    public static boolean isKeyDown(int vkCode) {
        short state = User32.INSTANCE.GetAsyncKeyState(vkCode);
        return (0x1 & (state >> (Short.SIZE - 1))) != 0;
    }


    /**
     * Sends a key-down input followed by a key-up input for the given character
     * value c.
     *
     * @param c
     */
    public static void pressKey(int c) {
        sendKeyDown(c);
        sendKeyUp(c);
    }

    /**
     * Sends a key-down input for the given character value c.
     *
     * @param c
     */
    public static void sendKeyDown(int c) {
        INPUT input = new INPUT();
        input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
        input.input.setType("ki");
        input.input.ki.time = new DWORD(0);
        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
        input.input.ki.wVk = new WinDef.WORD(0);
        input.input.ki.dwFlags = new DWORD(KEYEVENTF_KEYDOWN);
        input.input.ki.wScan = new WORD(c);
        User32.INSTANCE.SendInput(new DWORD(1), (INPUT[]) input.toArray(1), input.size());
    }

    /**
     * Sends a key-up input for the given character value c.
     *
     * @param c
     */
    public static void sendKeyUp(int c) {
        INPUT input = new INPUT();
        input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
        input.input.setType("ki");
        input.input.ki.time = new DWORD(0);
        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
        input.input.ki.wVk = new WinDef.WORD(0);
        input.input.ki.dwFlags = new DWORD(KEYEVENTF_KEYUP);
        input.input.ki.wScan = new WORD(c);
        User32.INSTANCE.SendInput(new DWORD(1), (INPUT[]) input.toArray(1), input.size());
   }

    public static void pressKeyWithRobot(int c) throws AWTException {
        final Robot r = new Robot();
        r.keyPress(c);
        r.keyRelease(c);
    }
}
