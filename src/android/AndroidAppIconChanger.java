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
			String alias_name = args.getString(1);
            this.changeAppIcon(package_name, alias_name, callbackContext);
            return true;
        }
        return false;
    }

    private void changeAppIcon(String package_name, String alias_name, CallbackContext callbackContext) {
        if (package_name != null && package_name.length() > 0 && alias_name != null && alias_name.length() > 0) {            
			try {
	
				this.cordova.getActivity().getPackageManager().setComponentEnabledSetting(this.cordova.getActivity().getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

				this.cordova.getActivity().getPackageManager().setComponentEnabledSetting(new ComponentName(this.cordova.getActivity().getApplicationContext(),
                            package_name + "." + alias_name),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);
				callbackContext.success("OK");
			} catch (Exception e) {
				callbackContext.success("ERROR" + e.getMessage());
			}		
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
