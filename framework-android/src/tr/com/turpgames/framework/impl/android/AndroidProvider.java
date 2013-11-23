package tr.com.turpgames.framework.impl.android;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

import com.turpgames.framework.v0.IEnvironmentProvider;
import com.turpgames.framework.v0.util.Version;

public class AndroidProvider implements IEnvironmentProvider {
	private final Context context;

	public AndroidProvider(Context context) {
		this.context = context;
	}

	private Version version;

	@Override
	public Version getVersion() {
		if (version == null) {
			try {
				String versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
				version = new Version(versionName);
			}
			catch (NameNotFoundException e) {
				e.printStackTrace();
				version = new Version("1.0");
			}
		}
		return version;
	}
}
