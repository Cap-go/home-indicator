import Foundation
import Capacitor


extension CAPBridgeViewController{
    
    private struct Holder {
        static var _setHomeIndicator: Bool = false
    }
    
    public func hideHomeIndicator(_ ishomeIndicatorVisible: Bool){
        
        Holder._setHomeIndicator=ishomeIndicatorVisible
        self.setNeedsUpdateOfHomeIndicatorAutoHidden()
    }
    
    
    override public var prefersHomeIndicatorAutoHidden: Bool {

        return Holder._setHomeIndicator
        
    }
}


@objc(HomeIndicatorPlugin)
public class HomeIndicatorPlugin: CAPPlugin {

    @objc func hide(_ call: CAPPluginCall) {
        guard let bridgeVC = self.bridge?.viewController as? CAPBridgeViewController else {
                call.reject("")
                return
            }
        DispatchQueue.main.async {
            bridgeVC.hideHomeIndicator(true)
        }
    }

    @objc func show(_ call: CAPPluginCall) {
        guard let bridgeVC = self.bridge?.viewController as? CAPBridgeViewController else {
                call.reject("")
                return
            }
        DispatchQueue.main.async {
            bridgeVC.hideHomeIndicator(false)
        }
    }
}
