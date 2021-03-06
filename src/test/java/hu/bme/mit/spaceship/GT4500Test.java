package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private TorpedoStore mockPrimaryTS;
  private TorpedoStore mockSecondaryTS;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    this.mockPrimaryTS = mock(TorpedoStore.class);
    this.mockSecondaryTS = mock(TorpedoStore.class);
    this.ship = new GT4500(this.mockPrimaryTS, this.mockSecondaryTS);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
	when(this.mockPrimaryTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(this.mockPrimaryTS, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_Primary_Store_Empty() {
	  

	    // Arrange
		when(this.mockPrimaryTS.isEmpty()).thenReturn(true);
		when(this.mockSecondaryTS.fire(1)).thenReturn(true);

	    // Act
	    boolean result = ship.fireTorpedo(FiringMode.ALL);
	    
	    // Assert
	    verify(this.mockPrimaryTS, times(1)).isEmpty();
	    verify(this.mockSecondaryTS, times(1)).fire(1);
	    assertEquals(true, result);
	    
  }

  @Test
  public void fireTorpedo_Single_All_Store_Empty() {
	  

	    // Arrange
		when(this.mockPrimaryTS.isEmpty()).thenReturn(true);
		when(this.mockSecondaryTS.isEmpty()).thenReturn(true);

	    // Act
	    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
	    
	    // Assert
	    verify(this.mockPrimaryTS, times(1)).isEmpty();
	    verify(this.mockPrimaryTS, times(0)).fire(1);
	    verify(this.mockSecondaryTS, times(1)).isEmpty();
	    verify(this.mockSecondaryTS, times(0)).fire(1);
	    assertEquals(false, result);
	    
  }

  @Test
  public void fireTorpedo_Single_Primary_Store_Failed() {
	  

	    // Arrange
		when(this.mockPrimaryTS.fire(1)).thenReturn(false);
		when(this.mockSecondaryTS.fire(1)).thenReturn(true);

	    // Act
	    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
	    
	    // Assert
	    verify(this.mockPrimaryTS, times(1)).fire(1);
	    verify(this.mockSecondaryTS, times(0)).fire(1);
	    assertEquals(false, result);
	    
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
	when(this.mockPrimaryTS.fire(1)).thenReturn(true);
	when(this.mockSecondaryTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    
    // Assert
    verify(this.mockPrimaryTS, times(1)).fire(1);
    verify(this.mockSecondaryTS, times(1)).fire(1);
    assertEquals(true, result);
    
  }

  @Test
  public void fireTorpedo_All_Primary_Store_Failed() {
	  

	    // Arrange
		when(this.mockPrimaryTS.fire(1)).thenReturn(false);
		when(this.mockSecondaryTS.fire(1)).thenReturn(true);

	    // Act
	    boolean result = ship.fireTorpedo(FiringMode.ALL);
	    
	    // Assert
	    verify(this.mockPrimaryTS, times(1)).fire(1);
	    verify(this.mockSecondaryTS, times(1)).fire(1);
	    assertEquals(true, result);
	    
  }

  @Test
  public void fireTorpedo_All_Secondary_Store_Failed() {
	  

	    // Arrange
		when(this.mockPrimaryTS.fire(1)).thenReturn(true);
		when(this.mockSecondaryTS.fire(1)).thenReturn(false);

	    // Act
	    boolean result = ship.fireTorpedo(FiringMode.ALL);
	    
	    // Assert
	    verify(this.mockPrimaryTS, times(1)).fire(1);
	    verify(this.mockSecondaryTS, times(1)).fire(1);
	    assertEquals(true, result);
	    
  }
  
  @Test
  public void fireTorpedo_Single_Primary_Then_Secondary_Not_Empty() {
	  

	    // Arrange
		when(this.mockPrimaryTS.fire(1)).thenReturn(true);
		when(this.mockSecondaryTS.isEmpty()).thenReturn(false);
		when(this.mockSecondaryTS.fire(1)).thenReturn(true);

	    // Act
	    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
	    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
	    
	    // Assert
	    verify(this.mockPrimaryTS, times(1)).fire(1);
	    verify(this.mockSecondaryTS, times(1)).fire(1);
	    assertEquals(true, result1);
	    assertEquals(true, result2);
	    
  }
  
  @Test
  public void fireTorpedo_Single_Primary_Then_Secondary_Empty_And_Primary_Not_Empty() {
	  

	    // Arrange
		when(this.mockPrimaryTS.fire(1)).thenReturn(true);
		when(this.mockSecondaryTS.isEmpty()).thenReturn(true);

	    // Act
	    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
	    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
	    
	    // Assert
	    verify(this.mockPrimaryTS, times(2)).fire(1);
	    verify(this.mockSecondaryTS, times(0)).fire(1);
	    assertEquals(true, result1);
	    assertEquals(true, result2);
	    
  }
  
  @Test
  public void fireTorpedo_Single_Primary_Empty_Then_Secondary_Twice() {
	  

	    // Arrange
		when(this.mockSecondaryTS.fire(1)).thenReturn(true);
		when(this.mockPrimaryTS.isEmpty()).thenReturn(true);

	    // Act
	    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
	    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
	    
	    // Assert
	    verify(this.mockSecondaryTS, times(2)).fire(1);
	    verify(this.mockPrimaryTS, times(0)).fire(1);
	    assertEquals(true, result1);
	    assertEquals(true, result2);
	    
  }
  
}
