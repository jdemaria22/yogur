
import com.sun.jna.Function;
import com.sun.jna.NativeLibrary;
import com.sun.jna.platform.win32.Kernel32;

public class Win32Core {
    public static int getTickCount(){
        return Kernel32.INSTANCE.GetTickCount();
    }

    public static void blockInput(Boolean bool) {
        NativeLibrary lib = NativeLibrary.getInstance("user32");
        Function fun = lib.getFunction("BlockInput");
        if (bool){
            fun.invoke(new Object[]{Boolean.TRUE});
        }
        fun.invoke(new Object[]{Boolean.FALSE});
        lib.dispose();
    }
}
