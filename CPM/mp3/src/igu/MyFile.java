package igu;

import java.io.File;

public class MyFile {
	private File f;

	public MyFile(File f) {
		super();
		this.f = f;
	}

	public File getF() {
		return f;
	}

	@Override
	public String toString() {
		return f.getName();
	}
	
	

}
