package ee.forgr.home_indicator;

import android.view.View;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "HomeIndicator")
public class HomeIndicatorPlugin extends Plugin {

    @PluginMethod
    public void hide(PluginCall call) {

        View decorView = getActivity().getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        JSObject ret = new JSObject();
        call.resolve(ret);
    }

    @PluginMethod
    public void show(PluginCall call) {
        View decorView = getActivity().getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        JSObject ret = new JSObject();
        call.resolve(ret);
    }
    
    @PluginMethod
    public void isHidden(PluginCall call) {
        View decorView = getActivity().getWindow().getDecorView();
        int uiOptions = decorView.getSystemUiVisibility();
        JSObject ret = new JSObject();
        ret.put("hidden", (uiOptions & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        call.resolve(ret);
    }
}
