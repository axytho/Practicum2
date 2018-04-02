package filesystem;

import static org.junit.Assert.*;


import org.junit.*;


public class TestFileSystem {
	
	private static File	fileAB, fileABC, fileB, fileC, fileD, fileF, fileG, fileH, fileI;
	
	private Directory DirectoryAlpha;
	
	@Before
	public void setUp()	{
		String name = "DirectoryAlpha";
		DirectoryAlpha = new Directory(name);
		fileAB = new File(DirectoryAlpha,"fileAB", Type.TEXT);
		fileABC = new File(DirectoryAlpha,"fileABC", Type.TEXT);
		fileB = new File(DirectoryAlpha,"fileB", Type.TEXT);
		fileC = new File(DirectoryAlpha,"fileC", Type.TEXT);
		fileD = new File(DirectoryAlpha,"fileD", Type.TEXT);
		fileF = new File(DirectoryAlpha,"fileF", Type.TEXT);
		fileG = new File(DirectoryAlpha,"fileG", Type.TEXT);
		fileH = new File(DirectoryAlpha, "fileH", Type.TEXT);
		fileI = new File(DirectoryAlpha, "fileI	", Type.TEXT);
		
	}
	
	@Test
	public void testAddToList$checkOrder()	{
		assertEquals(DirectoryAlpha.getItemAt(3).getName(),"fileC");
	}
	
	@Test
	public void binarySearch$findsCorrectIndexNineEntries() {
		assertEquals(DirectoryAlpha.binarySearch("fileAB"), 0);
		assertEquals(DirectoryAlpha.binarySearch("fileC"), 3);
		assertEquals(DirectoryAlpha.binarySearch("fileI"), 8);
	}
}
