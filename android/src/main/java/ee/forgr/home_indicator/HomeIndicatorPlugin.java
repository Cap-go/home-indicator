package ee.forgr.home_indicator;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowInsets;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "HomeIndicator")
public class HomeIndicatorPlugin extends Plugin {

    private OrientationEventListener orientationEventListener;
    private int previousOrientation = Configuration.ORIENTATION_UNDEFINED;
    public void UiChangeListener()
    {
        final View decorView = this.getBridge().getActivity().getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener (new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                Log.i("HomeIndicator", "onSystemUiVisibilityChange");
                HomeIndicatorPlugin.this.setCssVar();
            }
        });
    }
    private void setCssVar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowInsets windowInsets = this.getBridge().getActivity().getWindow().getDecorView().getRootWindowInsets();
            if (windowInsets == null) {
                return;
            }
            var res = windowInsets.getInsets(WindowInsets.Type.systemBars());
            float density = this.getBridge().getActivity().getResources().getDisplayMetrics().density;
            getBridge().getWebView().evaluateJavascript("document.documentElement.style.setProperty('--safe-area-inset-bottom', '" + res.bottom / density + "px');", null);
            getBridge().getWebView().evaluateJavascript("document.documentElement.style.setProperty('--safe-area-inset-top', '" + res.top / density + "px');", null);
            getBridge().getWebView().evaluateJavascript("document.documentElement.style.setProperty('--safe-area-inset-right', '" + res.right / density + "px');", null);
            getBridge().getWebView().evaluateJavascript("document.documentElement.style.setProperty('--safe-area-inset-left', '" + res.left / density + "px');", null);
        }
    }

    @Override
    public void load() {
        super.load();
        this.UiChangeListener();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // start task after 500ms in android
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    HomeIndicatorPlugin.this.setCssVar();
                }
            }, 500);
        }
        // Add orientation change listener
        orientationEventListener = new OrientationEventListener(getContext()) {
            @Override
            public void onOrientationChanged(int orientation) {
                int currentOrientation = HomeIndicatorPlugin.this.bridge.getActivity().getResources().getConfiguration().orientation;
                if (currentOrientation != previousOrientation) {
                    previousOrientation = currentOrientation;
                    Log.i("HomeIndicator", "onOrientationChanged " + currentOrientation);
                    HomeIndicatorPlugin.this.setCssVar();
                }
                HomeIndicatorPlugin.this.setCssVar();
            }
        };
        orientationEventListener.enable();
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
