/* Salim Aweys
   cssc1454
*/
import data_structures.*;
import java.util.Iterator;

public class ProductLookup {
  private DictionaryADT<String, StockItem> list;

  public ProductLookup(int maxSize) {
    list = new Hashtable(maxSize);
  }

  // Adds a new StockItem to the dictionary
  public void addItem(String SKU, StockItem item) {
    list.add(SKU, item);
  }
  // Returns the StockItem associated with the given SKU, if it is
  // in the ProductLookup, null if it is not.
  public StockItem getItem(String SKU) {
    return list.getValue(SKU);
  }

  // Returns the retail price associated with the given SKU value.
  // -.01 if the item is not in the dictionary
  public float getRetail(String SKU) {
    StockItem retail = list.getValue(SKU);
    if (retail == null) return -.01f;
    return retail.getRetail();
  }

  // Returns the cost price associated with the given SKU value.
  // -.01 if the item is not in the dictionary
  public float getCost(String SKU) {
    StockItem cost = list.getValue(SKU);
    if (cost == null) return -.01f;
    return cost.getCost();
  }

  // Returns the description of the item, null if not in the dictionary.
  public String getDescription(String SKU) {
    StockItem descript = list.getValue(SKU);
    if (descript == null) return null;
    return descript.getDescription();
  }

  // Deletes the StockItem associated with the SKU if it is
  // in the ProductLookup. Returns true if it was found and
  // deleted, otherwise false.
  public boolean deleteItem(String SKU) {
    return list.delete(SKU);
  }

  // Prints a directory of all StockItems with their associated
  // price, in sorted order (ordered by SKU).
  public void printAll() {
    Iterator<StockItem> I = list.values();
    while (I.hasNext()) {
      System.out.println(I.next());
    }
  }

  // Prints a directory of all StockItems from the given vendor,
  // in sorted order (ordered by SKU).
  public void print(String vendor) {
    Iterator<StockItem> I = list.values();
    StockItem temp = null;
    while (I.hasNext()) {
      temp = I.next();
      if (temp.vendor.compareTo(vendor) == 0) System.out.println(temp);
    }
  }

  // An iterator of the SKU keys.
  public Iterator<String> keys() {
    return list.keys();
  }

  // An iterator of the StockItem values.
  public Iterator<StockItem> values() {
    return list.values();
  }
}
