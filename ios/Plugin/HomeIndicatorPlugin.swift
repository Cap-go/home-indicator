import Foundation
import Capacitor

@objc(HomeIndicatorPlugin)
public class HomeIndicatorPlugin: CAPPlugin {

    @objc func hide(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            let controller = UIApplication.shared.windows.first(where: { $0.isKeyWindow })?.rootViewController
            if var appDelegateRootController = controller {
                appDelegateRootController.setNeedsUpdateOfHomeIndicatorAutoHidden()
                appDelegateRootController.prefersHomeIndicatorAutoHidden = true 
                call.resolve()
            } else {
                call.reject("Could not hide home indicator")
            }
        }
    }

    @objc func show(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            let controller = UIApplication.shared.windows.first(where: { $0.isKeyWindow })?.rootViewController
            if var appDelegateRootController = controller {
                appDelegateRootController.setNeedsUpdateOfHomeIndicatorAutoHidden()
                appDelegateRootController.prefersHomeIndicatorAutoHidden = false
                call.resolve()
            } else {
                call.reject("Could not show home indicator")
            }
        }
    }
}
