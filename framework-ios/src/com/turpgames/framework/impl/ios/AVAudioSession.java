package com.turpgames.framework.impl.ios;

import org.robovm.cocoatouch.foundation.NSError;
import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.objc.ObjCClass;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.Selector;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.ptr.Ptr;

@Library("AVFoundation")
@NativeClass
final class AVAudioSession extends NSObject {
    static {
        ObjCRuntime.bind();
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(AVAudioSession.class);

    private static final Selector sharedInstance = Selector.register("sharedInstance");
    @Bridge private native static AVAudioSession objc_getSharedInstance(ObjCClass __self__, Selector __cmd__);
    public static AVAudioSession getSharedInstance() {
        return objc_getSharedInstance(objCClass, sharedInstance);
    }

    private static final Selector setCategory$error$ = Selector.register("setCategory:error:");
    @Bridge private native static boolean objc_setCategory(AVAudioSession __self__, Selector __cmd__, String category, Ptr<NSError> error);
    public boolean setCategory(String category, Ptr<NSError> error) {
        return objc_setCategory(this, setCategory$error$ , category, error);
    }
}