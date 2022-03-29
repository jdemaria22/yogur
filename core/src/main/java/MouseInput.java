import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import java.awt.*;
import java.awt.event.InputEvent;

public class MouseInput {
    public static final int KEYEVENTF_KEYDOWN = 0;
    public static final int KEYEVENTF_KEYUP = 2;

    public static WinDef.POINT getCursorPos(){
        WinDef.POINT cursorPos = new WinDef.POINT();
        boolean state = User32.INSTANCE.GetCursorPos(cursorPos);
        return cursorPos;
    }

    public static void mouseMove(int x, int y) {
        boolean state = User32.INSTANCE.SetCursorPos(x, y);
    }

    public static void pressLeftClick() throws AWTException, InterruptedException {
        mouseClick(0);
    }

    public static void pressRightClick() throws AWTException, InterruptedException {
        mouseClick(1);
    }

    public static void pressRightClickRobot() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    public static void mouseClick(int type) {
        final int MOUSEEVENTF_LEFTUP = 0x0004;
        final int MOUSEEVENTF_LEFTDOWN = 0x0002;
        final int MOUSEEVENTF_RIGTHDOWN = 0x0008;
        final int MOUSEEVENTF_RIGHTUP = 0x0010;

        if (type == 1){
            WinUser.INPUT input = new WinUser.INPUT();
            WinUser.INPUT[] click = (WinUser.INPUT[]) input.toArray(2);

            click[0].type = new WinDef.DWORD(WinUser.INPUT.INPUT_MOUSE);
            click[0].input.setType("mi");
            click[0].input.mi.dwFlags = new WinDef.DWORD(MOUSEEVENTF_RIGTHDOWN);

            click[1].type = new WinDef.DWORD(WinUser.INPUT.INPUT_MOUSE);
            click[1].input.setType("mi");
            click[1].input.mi.dwFlags = new WinDef.DWORD(MOUSEEVENTF_RIGHTUP);

            User32.INSTANCE.SendInput(new WinDef.DWORD(2), click, click[0].size());
            return;
        }
        WinUser.INPUT input = new WinUser.INPUT();
        WinUser.INPUT[] click = (WinUser.INPUT[]) input.toArray(2);

        click[0].type = new WinDef.DWORD(WinUser.INPUT.INPUT_MOUSE);
        click[0].input.setType("mi");
        click[0].input.mi.dwFlags = new WinDef.DWORD(MOUSEEVENTF_LEFTDOWN);

        click[1].type = new WinDef.DWORD(WinUser.INPUT.INPUT_MOUSE);
        click[1].input.setType("mi");
        click[1].input.mi.dwFlags = new WinDef.DWORD(MOUSEEVENTF_LEFTUP);

        User32.INSTANCE.SendInput(new WinDef.DWORD(2), click, click[0].size());
    }

    public static void moveAndPressKey(int x, int y, int key) throws InterruptedException {
        WinDef.POINT cursorPos = new WinDef.POINT();
        boolean statePos = User32.INSTANCE.GetCursorPos(cursorPos);

        boolean state = User32.INSTANCE.SetCursorPos(x, y);
        WinUser.INPUT input = new WinUser.INPUT();
        WinUser.INPUT[] inputArray = (WinUser.INPUT[]) input.toArray(2);
        inputArray[0].type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
        inputArray[0].input.setType("ki");
        inputArray[0].input.ki.time = new WinDef.DWORD(0);
        inputArray[0].input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
        inputArray[0].input.ki.wVk = new WinDef.WORD(0);
        inputArray[0].input.ki.dwFlags = new WinDef.DWORD(KEYEVENTF_KEYDOWN);
        inputArray[0].input.ki.wScan = new WinDef.WORD(key);

        inputArray[1].type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
        inputArray[1].input.setType("ki");
        inputArray[1].input.ki.time = new WinDef.DWORD(0);
        inputArray[1].input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
        inputArray[1].input.ki.wVk = new WinDef.WORD(0);
        inputArray[1].input.ki.dwFlags = new WinDef.DWORD(KEYEVENTF_KEYUP);
        inputArray[1].input.ki.wScan = new WinDef.WORD(key);

        User32.INSTANCE.SendInput(new WinDef.DWORD(2), inputArray, inputArray[0].size());

        Thread.sleep(15);

        state = User32.INSTANCE.SetCursorPos(cursorPos.x, cursorPos.y);
    }

}
