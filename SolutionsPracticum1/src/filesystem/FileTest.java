package filesystem;
import static org.junit.Assert.*;
import java.util.Date;
import be.kuleuven.cs.som.annotate.*;
import org.junit.*;

/**
 * A JUnit test class for testing the public methods of the FileObject Class  
 * @author Tommy Messelis
 *
 */
public class FileTest {

	FileObject fileStringIntBoolean;
	FileObject fileString;
	Date timeBeforeConstruction, timeAfterConstruction;
	
	FileObject fileNotWritable;
	Date timeBeforeConstructionNotWritable, timeAfterConstructionNotWritable;
	
	@Before
	public void setUpFixture(){
		timeBeforeConstruction = new Date();
		fileStringIntBoolean = new FileObject("bestand.txt",100, true);
		fileString = new FileObject("bestand.txt");
		timeAfterConstruction = new Date();

		timeBeforeConstructionNotWritable = new Date();
		fileNotWritable = new FileObject("bestand.txt",100,false);
		timeAfterConstructionNotWritable = new Date();
	}

	@Test
	public void testFileStringIntBoolean_LegalCase() {
		assertEquals("bestand.txt",fileStringIntBoolean.getName());
		assertEquals(fileStringIntBoolean.getSize(),100);
		assertTrue(fileStringIntBoolean.isWritable());
		assertNull(fileStringIntBoolean.getModificationTime());
		assertFalse(timeBeforeConstruction.after(fileStringIntBoolean.getCreationTime()));
		assertFalse(fileStringIntBoolean.getCreationTime().after(timeAfterConstruction));
	}
	
	@Test
	public void testFileStringIntBoolean_IllegalCase() {
		timeBeforeConstruction = new Date();
		fileStringIntBoolean = new FileObject("$IllegalName$",FileObject.getMaximumSize(),false);
		timeAfterConstruction = new Date();
		assertTrue(FileObject.isValidName(fileStringIntBoolean.getName()));
		assertEquals(FileObject.getMaximumSize(),fileStringIntBoolean.getSize());
		assertFalse(fileStringIntBoolean.isWritable());
		assertNull(fileStringIntBoolean.getModificationTime());
		assertFalse(timeBeforeConstruction.after(fileStringIntBoolean.getCreationTime()));
		assertFalse(fileStringIntBoolean.getCreationTime().after(timeAfterConstruction));
	}

	@Test
	public void testFileString_LegalCase() {
		assertEquals("bestand.txt",fileString.getName());
		assertEquals(0,fileString.getSize());
		assertTrue(fileString.isWritable());
		assertNull(fileString.getModificationTime());
		assertFalse(timeBeforeConstruction.after(fileString.getCreationTime()));
		assertFalse(fileString.getCreationTime().after(timeAfterConstruction));
	}
	
	@Test
	public void testFileString_IllegalCase() {
		timeBeforeConstruction = new Date();
		fileString = new FileObject("$IllegalName$");
		timeAfterConstruction = new Date();
		assertTrue(FileObject.isValidName(fileString.getName()));
		assertEquals(0,fileString.getSize());
		assertTrue(fileString.isWritable());
		assertNull(fileString.getModificationTime());
		assertFalse(timeBeforeConstruction.after(fileString.getCreationTime()));
		assertFalse(fileString.getCreationTime().after(timeAfterConstruction));
	}

	@Test
	public void testIsValidName_LegalCase() {
		assertTrue(FileObject.isValidName("abcDEF123-_."));
	}

	@Test
	public void testIsValidName_IllegalCase() {
		assertFalse(FileObject.isValidName(null));
		assertFalse(FileObject.isValidName(""));
		assertFalse(FileObject.isValidName("%illegalSymbol"));
		
	}

	@Test
	public void testChangeName_LegalCase() {
		Date timeBeforeSetName = new Date();
		fileString.changeName("NewLegalName");
		Date timeAfterSetName = new Date();
		
		assertEquals("NewLegalName",fileString.getName());
		assertNotNull(fileString.getModificationTime());
		assertFalse(fileString.getModificationTime().before(timeBeforeSetName));
		assertFalse(timeAfterSetName.before(fileString.getModificationTime()));
	}
	
	@Test (expected = FileNotWritableException.class)
	public void testChangeName_FileNotWritable() {
		fileNotWritable.changeName("NewLegalName");
	}
	
	@Test
	public void testChangeName_IllegalName() {
		fileString.changeName("$IllegalName$");
		assertEquals("bestand.txt",fileString.getName());
		assertNull(fileString.getModificationTime());
	}

	@Test
	public void testIsValidSize_LegalCase() {
		assertTrue(FileObject.isValidSize(0));
		assertTrue(FileObject.isValidSize(FileObject.getMaximumSize()/2));
		assertTrue(FileObject.isValidSize(FileObject.getMaximumSize()));
	}
	
	@Test
	public void testIsValidSize_IllegalCase() {
		assertFalse(FileObject.isValidSize(-1));
		if (FileObject.getMaximumSize() < Integer.MAX_VALUE) {
			assertFalse(FileObject.isValidSize(FileObject.getMaximumSize()+1));
		}
	}

	@Test
	public void testEnlarge_LegalCase() {
		FileObject file = new FileObject("bestand.txt",FileObject.getMaximumSize()-1,true);
		Date timeBeforeEnlarge = new Date();
		file.enlarge(1);
		Date timeAfterEnlarge = new Date();		
		assertEquals(file.getSize(),FileObject.getMaximumSize());
		assertNotNull(file.getModificationTime());
		assertFalse(file.getModificationTime().before(timeBeforeEnlarge));
		assertFalse(timeAfterEnlarge.before(file.getModificationTime()));  
	}
	
	@Test (expected = FileNotWritableException.class)
	public void testEnlarge_FileNotWritable() {
		fileNotWritable.enlarge(1);
	}
	
	@Test
	public void testShorten_LegalCase() {
		fileStringIntBoolean.shorten(1);
		Date timeAfterShorten = new Date();		
		assertEquals(fileStringIntBoolean.getSize(),99);
		assertNotNull(fileStringIntBoolean.getModificationTime());
		assertFalse(fileStringIntBoolean.getModificationTime().before(timeAfterConstruction));
		assertFalse(timeAfterShorten.before(fileStringIntBoolean.getModificationTime()));
	}
	
	@Test (expected = FileNotWritableException.class)
	public void testShorten_FileNotWritable() {
		fileNotWritable.shorten(1);
	}

	@Test
	public void testIsValidCreationTime_LegalCase() {
		Date now = new Date();
		assertTrue(FileObject.isValidCreationTime(now));
	}
	
	@Test
	public void testIsValidCreationTime_IllegalCase() {
		assertFalse(FileObject.isValidCreationTime(null));
		Date inFuture = new Date(System.currentTimeMillis() + 1000*60*60);
		assertFalse(FileObject.isValidCreationTime(inFuture));		
	}
	
	@Test
	public void testcanHaveAsModificationTime_LegalCase() {
		assertTrue(fileString.canHaveAsModificationTime(null));
		assertTrue(fileString.canHaveAsModificationTime(new Date()));
	}
	
	@Test
	public void testcanHaveAsModificationTime_IllegalCase() {
		assertFalse(fileString.canHaveAsModificationTime(new Date(timeAfterConstruction.getTime() - 1000*60*60)));
		assertFalse(fileString.canHaveAsModificationTime(new Date(System.currentTimeMillis() + 1000*60*60)));
	}

	@Test
	public void testHasOverlappingUsePeriod_UnmodifiedFiles() {
		// one = implicit argument ; other = explicit argument
		FileObject one = new FileObject("one");
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		FileObject other = new FileObject("other");
		
		//1 Test unmodified case
		assertFalse(one.hasOverlappingUsePeriod(other));
		
		//2 Test one unmodified case
		other.enlarge(FileObject.getMaximumSize());
		assertFalse(one.hasOverlappingUsePeriod(other));
		
		//3 Test other unmodified case
		//so re-initialise the other file
		other = new FileObject("other");
		one.enlarge(FileObject.getMaximumSize());
		assertFalse(one.hasOverlappingUsePeriod(other));
		
	}
	
	@Test
	public void testHasOverlappingUsePeriod_ModifiedNoOverlap() {
		// one = implicit argument ; other = explicit argument
		FileObject one, other;
		one = new FileObject("one");
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new FileObject("other");
		
		//1 Test one created and modified before other created and modified case
		one.enlarge(FileObject.getMaximumSize());
        sleep();
        //re-initialise the other
        other = new FileObject("other");
        other.enlarge(FileObject.getMaximumSize());
	    assertFalse(one.hasOverlappingUsePeriod(other));
	    
	    //2 Test other created and modified before one created and modified
		other.enlarge(FileObject.getMaximumSize());
        sleep();
        one = new FileObject("one");
        one.enlarge(FileObject.getMaximumSize());
        assertFalse(one.hasOverlappingUsePeriod(other));
	
	}
	
	@Test
	public void testHasOverlappingUsePeriod_ModifiedOverlap_A() {
		// one = implicit argument ; other = explicit argument
		//A Test one created before other created before one modified before other modified
	    FileObject one, other;
		one = new FileObject("one");
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new FileObject("other");
	
		one.enlarge(FileObject.getMaximumSize());
        sleep();
        other.enlarge(FileObject.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}
	
	@Test
	public void testHasOverlappingUsePeriod_ModifiedOverlap_B() {
		// one = implicit argument ; other = explicit argument
		//B Test one created before other created before other modified before one modified
       	FileObject one, other;
		one = new FileObject("one");
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new FileObject("other");
	
		other.enlarge(FileObject.getMaximumSize());
        sleep();
        one.enlarge(FileObject.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}
	
	@Test
	public void testHasOverlappingUsePeriod_ModifiedOverlap_C() {
		// one = implicit argument ; other = explicit argument
		//C Test other created before one created before other modified before one modified
        FileObject one, other;
		other = new FileObject("other");
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		one = new FileObject("one");
		
		other.enlarge(FileObject.getMaximumSize());
        sleep();
        one.enlarge(FileObject.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}
	
	@Test
	public void testHasOverlappingUsePeriod_ModifiedOverlap_D() {
		// one = implicit argument ; other = explicit argument
		//D Test other created before one created before one modified before other modified
		FileObject one, other;
		other = new FileObject("one");
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		one = new FileObject("other");
	
		one.enlarge(FileObject.getMaximumSize());
        sleep();
        other.enlarge(FileObject.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}

	@Test
	public void testSetWritable() {
		fileString.setWritable(false);
		fileNotWritable.setWritable(true);
		assertFalse(fileString.isWritable());
		assertTrue(fileNotWritable.isWritable());
	}
	
	private void sleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
