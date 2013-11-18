package com.turpgames.framework.v0.impl;

import java.util.ArrayList;
import java.util.List;

import com.turpgames.framework.v0.IAfterUpdateProcess;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Version;

public final class UpdateProcessor {
	public static final UpdateProcessor instance = new UpdateProcessor();

	private List<IAfterUpdateProcess> processList = new ArrayList<IAfterUpdateProcess>();

	private UpdateProcessor() {

	}

	public void execute() {
		Version lastUpdateProcessVersion = new Version(Settings.getString("last-update-process-version", "0.0"));
		for (IAfterUpdateProcess process : processList) {
			if (process.getVersion().compareTo(lastUpdateProcessVersion) > 0)
				process.execute();
		}
		Settings.putString("last-update-process-version", Game.getVersion().toString());
	}

	public void addProcess(IAfterUpdateProcess process) {
		processList.add(process);
	}
}
