package filesystem;

import static org.junit.Assert.*;


import org.junit.*;



public class TestFileSystem {
	
	private static File	fileAB, fileABC, fileB, fileC, fileD, fileF, fileG, fileH, fileI;
	
	private static Directory Dir_writable, Dir_notWritable, Dir_super, Dir_sub1, Dir_sub2;
	
	private Directory DirectoryAlpha;
	
	@Before
	public void setUp()	{
		String NameA = "DirectoryAlpha";
		
		DirectoryAlpha = new Directory(NameA);
		fileAB = new File(DirectoryAlpha,"fileAB", Type.TEXT);
		fileABC = new File(DirectoryAlpha,"fileABC", Type.TEXT);
		fileB = new File(DirectoryAlpha,"fileB", Type.TEXT);
		fileC = new File(DirectoryAlpha,"fileC", Type.TEXT);
		fileD = new File(DirectoryAlpha,"fileD", Type.TEXT);
		fileF = new File(DirectoryAlpha,"fileF", Type.TEXT);
		fileG = new File(DirectoryAlpha,"fileG", Type.TEXT);
		fileH = new File(DirectoryAlpha, "fileH", Type.TEXT);
		fileI = new File(DirectoryAlpha, "fileI", Type.TEXT);
		
		String NameB = "DirectoryBeta";
		String NameC = "DirectoryGamma";
		String NameSuper = "DirectorySuper";
		String NameDir_sub1 = "Dir_sub1";
		String NameDir_sub2 = "Dir_sub2";
		
		Dir_writable = new Directory(NameB,true);
		Dir_notWritable = new Directory(NameC,false);
		Dir_super = new Directory(NameSuper);
		Dir_sub1 = new Directory(Dir_super,NameDir_sub1,true);
		Dir_sub2 = new Directory(Dir_super,NameDir_sub2);
		
		
		
	}
	@Test 
	public void DirectoryConstructor_LegalCase() {
		assertEquals(Dir_writable.getName(),"DirectoryBeta");
		assertEquals(Dir_writable.isWritable(), true);
		assertEquals(Dir_sub1.getDirectory(),Dir_super);
		assertEquals(Dir_sub2.getDirectory(),Dir_super); 
	}
	
	
	@Test 
	public void Move$LegalCase() {
		Dir_sub1.move(DirectoryAlpha);
		assertEquals(Dir_sub1.getDirectory(), "DirectoryAlpha"); 
	}
	
	@Test (expected = LoopException.class)
	public void Move$IllegalCase() {
		Dir_super.move(Dir_sub1);
	}
	
	@Test
	public void insert$legalCase() {
		Dir_sub1.insert(fileAB);
		assertEquals(Dir_sub1.getItemAt(1), fileAB);
	}
	
	@Test (expected = AlreadyInListException.class)
	public void insert$illegalCase() {
		DirectoryAlpha.insert(fileAB);
		fail("expected fail.");
	}
	
	@Test 
	public void getNbItems$legalCase() {
		assertEquals(Dir_super.getNbItems(),2);
		assertEquals(Dir_sub2.getNbItems(),0);
	}
	

	
	
	
	public void testAddToList$checkOrder()	{
		assertEquals(DirectoryAlpha.getItemAt(3).getName(),"fileC");
	}
	
	@Test
	public void binarySearch$findsCorrectIndexNineEntries() {
		assertEquals(DirectoryAlpha.binarySearch("fileAB"), 0);
		assertEquals(DirectoryAlpha.binarySearch("fileC"), 3);
		assertEquals(DirectoryAlpha.binarySearch("fileG"), 6);
		assertEquals(DirectoryAlpha.binarySearch("fileI"), 8);
	}
	
	@Test
	public void binarySearchForAddition$findCorrectIndex()	{
		assertEquals(DirectoryAlpha.getPlace("file"),-1);
		assertEquals(DirectoryAlpha.getPlace("fileABBA"), 0);
		assertEquals(DirectoryAlpha.getPlace("fileE"),4);
		assertEquals(DirectoryAlpha.getPlace("fileIndia"),8);
		assertEquals(DirectoryAlpha.getPlace("fileJulliette"),9);
	}
	
	
	
}
