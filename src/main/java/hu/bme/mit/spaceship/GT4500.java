package hu.bme.mit.spaceship;

/**
* A simple spaceship with two proton torpedos and four lasers
*/
public class GT4500 implements SpaceShip {

  private TorpedoStore primaryTorpedoStore;
  private TorpedoStore secondaryTorpedoStore;

  private boolean wasPrimaryFiredLast = false;

  public GT4500() {
    this.primaryTorpedoStore = new TorpedoStore(10);
    this.secondaryTorpedoStore = new TorpedoStore(10);
  }

  public boolean fireLasers(FiringMode firingMode) {
    // TODO not implemented yet
    return false;
  }

  private boolean onAllFiringMode(){
  // try to fire both of the torpedos
  //TODO implement feature
    return false;
  }

  private boolean onSingleFiringMode(){
  
    if (wasPrimaryFiredLast) {
          // try to fire the secondary first
          if (! secondaryTorpedoStore.isEmpty()) {
            wasPrimaryFiredLast = false;
            return secondaryTorpedoStore.fire(1);
          }
          else {
            // although primary was fired last time, but the secondary is empty
            // thus try to fire primary again
            if (! primaryTorpedoStore.isEmpty()) {
              wasPrimaryFiredLast = true;
              return primaryTorpedoStore.fire(1);
            }

            // if both of the stores are empty, nothing can be done, return failure
          }
    }
    else {
          // try to fire the primary first
          if (! primaryTorpedoStore.isEmpty()) {
            wasPrimaryFiredLast = true;
            return primaryTorpedoStore.fire(1);
          }
          else {
            // although secondary was fired last time, but primary is empty
            // thus try to fire secondary again
            if (! secondaryTorpedoStore.isEmpty()) {
              wasPrimaryFiredLast = false;
              return secondaryTorpedoStore.fire(1);
            }

            // if both of the stores are empty, nothing can be done, return failure
          }
    }

  }
  

  /**
  * Tries to fire the torpedo stores of the ship.
  *
  * @param firingMode how many torpedo bays to fire
  * 	SINGLE: fires only one of the bays.
  * 			- For the first time the primary store is fired.
  * 			- To give some cooling time to the torpedo stores, torpedo stores are fired alternating.
  * 			- But if the store next in line is empty the ship tries to fire the other store.
  * 			- If the fired store reports a failure, the ship does not try to fire the other one.
  * 	ALL:	tries to fire both of the torpedo stores.
  *
  * @return whether at least one torpedo was fired successfully
  */
  @Override
  public boolean fireTorpedos(FiringMode firingMode) {

    if(firingMode == SINGLE){
      return onSingleFiringMode();
    }
    else if(firingMode == ALL){
      return onAllFiringMode();
    }
    return false;
  }

}
