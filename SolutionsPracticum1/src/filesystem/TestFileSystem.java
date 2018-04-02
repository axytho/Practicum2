package filesystem;

import static org.junit.Assert.*;


import org.junit.*;


public class TestFileSystem {
	
	private static File	fileAB, fileABC, fileB, fileC, fileD, fileF, fileG, fileH, fileI;
	
	@Before
	public void setUp()	{
		fileAB = new File("fileAB", Type.TEXT);
		fileABC = new File("fileABC", Type.TEXT);
		fileB = new File("fileB", Type.TEXT);
		fileC = new File("fileC", Type.TEXT);
		fileD = new File("fileD", Type.TEXT);
		fileF = new File("fileF", Type.TEXT);
		fileG = new File("fileG", Type.TEXT);
		fileH = new File("fileH", Type.TEXT);
		fileI = new File("fileI	", Type.TEXT);
		
	}
}
