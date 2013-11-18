package com.turpgames.framework.v0;

import com.turpgames.framework.v0.util.Version;

public interface IAfterUpdateProcess {
	void execute();

	Version getVersion();
}
