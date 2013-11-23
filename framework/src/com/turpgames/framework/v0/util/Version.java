package com.turpgames.framework.v0.util;

public class Version implements Comparable<Version> {
	private int major;
	private int minor;
	private int revision;

	public Version(String version) {
		String[] ss = version.split("\\.");
		
		major = Utils.parseInt(ss[0]);

		if (ss.length > 1)
			minor = Utils.parseInt(ss[1]);
		if (ss.length > 2)
			revision = Utils.parseInt(ss[2]);
	}

	public int getMajor() {
		return major;
	}

	public int getMinor() {
		return minor;
	}

	public int getRevision() {
		return revision;
	}
	
	@Override
	public String toString() {
		return String.format("%d.%d.%d", major, minor, revision);
	}

	@Override
	public int compareTo(Version version) {
		if (this.major > version.major)
			return 1;
		
		if (this.major < version.major)
			return -1;
		
		if (this.minor > version.minor)
			return 1;
		
		if (this.minor < version.minor)
			return -1;
		
		if (this.revision > version.revision)
			return 1;
		
		if (this.revision < version.revision)
			return -1;
		
		return 0;
	}
}
