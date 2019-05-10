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

}
