

import javax.swing.*;
import java.util.*;

public class SortedListModel extends AbstractListModel {

  // Define a SortedSet
  private SortedSet model;

  public SortedListModel() {
    // Create a TreeSet
    // Store it in SortedSet variable
    model = new TreeSet();
  }

  // ListModel methods
  public int getSize() {
    // Return the model size
    return model.size();
  }

  public Object getElementAt(int index) {
    // Return the appropriate element
    return model.toArray()[index];
  }

  // Other methods
  public void addElement(Object element) {
    if (model.add(element)) {
      fireContentsChanged(this, 0, getSize());
    }
  }

  public void addAll(Object elements[]) {
    Collection c = Arrays.asList(elements);
    model.addAll(c);
    fireContentsChanged(this, 0, getSize());
  }

  public void clear() {
    model.clear();
    fireContentsChanged(this, 0, getSize());
  }

  public boolean contains(Object element) {
    return model.contains(element);
  }

  public Object firstElement() {
    // Return the appropriate element
    return model.first();
  }

  public Iterator iterator() {
    return model.iterator();
  }

  public Object lastElement() {
    // Return the appropriate element
    return model.last();
  }

  public boolean removeElement(Object element) {
    boolean removed = model.remove(element);
    if (removed) {
      fireContentsChanged(this, 0, getSize());
    }
    return removed;   
  }
}