package com.blox;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.blox.maze.IMazeSaveHandler;

public class MazeSaveHandler implements IMazeSaveHandler {
	public void save(int[][] mazeData) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("C:/maze.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(mazeData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStream(oos);
			closeStream(fos);	
		}
	}
	
	private void closeStream(OutputStream s) {
		if (s != null) {
			try {
				s.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
