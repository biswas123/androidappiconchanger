package cordova.plugin.androidappiconchanger;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.Context;
/**
 * This class echoes a string called from JavaScript.
 */
public class AndroidAppIconChanger extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("changeAppIcon")) {
            String package_name = args.getString(0);
			String icon_name = args.getString(1);
            this.coolMethod(package_name, icon_name, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String package_name, String icon_name, CallbackContext callbackContext) {
        if (package_name != null && package_name.length() > 0 && icon_name != null && icon_name.length() > 0) {            
			try {
	
				this.cordova.getActivity().getPackageManager().setComponentEnabledSetting(
                    this.cordova.getActivity().getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

				this.cordova.getActivity().getPackageManager().setComponentEnabledSetting(
                    new ComponentName(this.cordova.getActivity().getApplicationContext(),
                            package_name + "." + icon_name),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);
				callbackContext.success("OK");
			} catch (Exception e) {
				callbackContext.success("NOT_OK");
			}		
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
