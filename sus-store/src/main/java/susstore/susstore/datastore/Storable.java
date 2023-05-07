package susstore.susstore.datastore;

import java.io.Serializable;

/**
 * Classes that implements the Storable object are expected to have a default no-argument constructor
 * for it to be able to be stored.
 *
 * The classes' elements also has to have a default
 * no-argument constructor in order to be stored properly.
 */
public interface Storable extends Serializable {}
