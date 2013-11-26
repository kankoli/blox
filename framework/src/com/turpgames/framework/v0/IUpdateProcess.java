package com.turpgames.framework.v0;

import com.turpgames.framework.v0.util.Version;

public interface IUpdateProcess {
	void execute();

	Version getVersion();
}
