#import <Foundation/Foundation.h>
#import <Capacitor/Capacitor.h>

// Define the plugin using the CAP_PLUGIN Macro, and
// each method the plugin supports using the CAP_PLUGIN_METHOD macro.
CAP_PLUGIN(HomeIndicatorPlugin, "HomeIndicator",
          CAP_PLUGIN_METHOD(hide, CAPPluginReturnPromise);
          CAP_PLUGIN_METHOD(show, CAPPluginReturnPromise);
          CAP_PLUGIN_METHOD(isHidden, CAPPluginReturnPromise);
          CAP_PLUGIN_METHOD(getPluginVersion, CAPPluginReturnPromise);
)
