package ee.forgr.home_indicator;

import android.view.View;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "HomeIndicator")
public class HomeIndicatorPlugin extends Plugin {


    private void setCssVar() {
        WindowInsets windowInsets = MainActivity.this.getBridge().getActivity().getWindow().getDecorView().getRootWindowInsets();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && windowInsets != null) {
            var res = windowInsets.getInsets(WindowInsets.Type.systemBars());
            float density = MainActivity.this.getBridge().getActivity().getResources().getDisplayMetrics().density;
            getBridge().getWebView().evaluateJavascript("document.documentElement.style.setProperty('--safe-area-inset-bottom', '" + res.bottom / density + "px');", null);
            getBridge().getWebView().evaluateJavascript("document.documentElement.style.setProperty('--safe-area-inset-top', '" + res.top / density + "px');", null);
            getBridge().getWebView().evaluateJavascript("document.documentElement.style.setProperty('--safe-area-inset-right', '" + res.right / density + "px');", null);
            getBridge().getWebView().evaluateJavascript("document.documentElement.style.setProperty('--safe-area-inset-left', '" + res.left / density + "px');", null);
        }
    }

    @Override
    public void load() {
        super.load();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // start task after 500ms in android
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    HomeIndicatorPlugin.this.setCssVar();
                }
            }, 500);
        }
    }

    @PluginMethod
    public void hide(PluginCall call) {
        getActivity()
            .runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        View decorView = getActivity().getWindow().getDecorView();
                        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
                        decorView.setSystemUiVisibility(uiOptions);
                        HomeIndicatorPlugin.this.setCssVar();
                        JSObject ret = new JSObject();
                        call.resolve(ret);
                    }
                }
            );
    }

    @PluginMethod
    public void show(PluginCall call) {
        getActivity()
            .runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        View decorView = getActivity().getWindow().getDecorView();
                        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
                        decorView.setSystemUiVisibility(uiOptions);
                        HomeIndicatorPlugin.this.setCssVar();
                        JSObject ret = new JSObject();
                        call.resolve(ret);
                    }
                }
            );
    }

    @PluginMethod
    public void isHidden(PluginCall call) {
        getActivity()
            .runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        View decorView = getActivity().getWindow().getDecorView();
                        int uiOptions = decorView.getSystemUiVisibility();
                        HomeIndicatorPlugin.this.setCssVar();
                        JSObject ret = new JSObject();
                        ret.put("hidden", (uiOptions & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                        call.resolve(ret);
                    }
                }
            );
    }
}
