package se.bfiles.entities

import org.specs2.mutable.Specification
import org.specs2.specification.Fragments
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StorageSpec extends Specification {

  class StorageTester extends Storage {}
  class LocationTester extends Location {}
  class TestStorable extends Storable {}

  "A Storage thingy " should {
    "be able to store a Storable " in {
      val testStorage = new StorageTester
      val testStorable = new TestStorable	  

      testStorage store testStorable
      
      testStorage has testStorable must beTrue
    }
  }
  
  "A Storable " should {
    "be able to tell where it's stored " in {
   val testStorage = new StorageTester
      val testStorable = new TestStorable	  

      testStorage store testStorable
      
      testStorable storedIn testStorage must beTrue      
    }
  }
  
  "A Location " should {
    "be able to take another Location " in {
      val testLocation = new LocationTester()
      val secondLocation = new LocationTester()
      
      testLocation + secondLocation
      
      testLocation has secondLocation must beTrue 
    }
  }
  
  
}