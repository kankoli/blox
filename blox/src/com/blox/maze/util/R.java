package com.blox.maze.util;

import com.blox.framework.v0.util.AnimationInfo;
import com.blox.maze.model.Maze;

public final class R {
	public static final class animations {
		public static final class Block {
			public static final AnimationInfo def = new AnimationInfo("Block", "turnmaze/body_full40-2.png", 1, Maze.blockWidth, Maze.blockHeight, false);
		}

		public static final class Lokum {
			public static final AnimationInfo def = new AnimationInfo("Lokum", "turnmaze/lokum.png", 0.1f, Maze.blockWidth, Maze.blockHeight, false);
			public static final AnimationInfo onTrap = new AnimationInfo("FellOnTrap", "turnmaze/lokumtrap.png", 0.15f, Maze.blockWidth, Maze.blockHeight, false);
			public static final AnimationInfo onObjective = new AnimationInfo("FellOnObjective", "turnmaze/lokumobj.png", 0.1f, Maze.blockWidth, Maze.blockHeight, false);
			public static final AnimationInfo onPortal = new AnimationInfo("FellOnPortal", "turnmaze/lokum.png", 0.15f, Maze.blockWidth, Maze.blockHeight, false);
		}

		public static final class PortalDoor {
			private static final float frameDuration = 0.3f;

			public static final String defName = "Portal";
			public static final String enterName = "EnterDoor";
			public static final String exitName = "ExitDoor";

			public static final AnimationInfo defBlue = new AnimationInfo(defName, "turnmaze/portalblue.png", frameDuration, Maze.blockWidth, Maze.blockHeight, false);
			public static final AnimationInfo defGreen = new AnimationInfo(defName, "turnmaze/portalgreen.png", frameDuration, Maze.blockWidth, Maze.blockHeight, false);

			public static final AnimationInfo enterBlue = new AnimationInfo(enterName, "turnmaze/portalenterblue.png", frameDuration, Maze.blockWidth, Maze.blockHeight, false);
			public static final AnimationInfo enterGreen = new AnimationInfo(enterName, "turnmaze/portalentergreen.png", frameDuration, Maze.blockWidth, Maze.blockHeight, false);

			public static final AnimationInfo exitBlue = new AnimationInfo(exitName, "turnmaze/portalexitblue.png", frameDuration, Maze.blockWidth, Maze.blockHeight, false);
			public static final AnimationInfo exitGreen = new AnimationInfo(exitName, "turnmaze/portalexitgreen.png", frameDuration, Maze.blockWidth, Maze.blockHeight, false);
		}

		public static final class Trap {
			public static final AnimationInfo def = new AnimationInfo("Trap", "turnmaze/trap.png", 0.1f, Maze.blockWidth, Maze.blockHeight, true);
		}

		public static final class Objective {
			public static final AnimationInfo def = new AnimationInfo("Objective", "turnmaze/door.gif", 1, Maze.blockWidth, Maze.blockHeight, false);
		}

		public static final class Background {
			public static final AnimationInfo zeroth = new AnimationInfo("bg", "screen2.jpg", 1, 480, 800, false);
			public static final AnimationInfo first = new AnimationInfo("bg", "screen1.jpg", 1, 480, 800, false);
			public static final AnimationInfo second = new AnimationInfo("bg", "screen2.jpg", 1, 480, 800, false);
		}
	}
}
