/**
 * @author Rachana Deolikar
 * @version 1.0
 */

package com.project.EcoRe;

import java.util.Map;

import com.project.logic.BackendLogic;

/**
 *
 * @author rachanadeolikar
 */
public class RecycleItem {
	private String itemName;
	private String itemType;
	private double itemWeight, itemWeightUnit;
	private double unitPrice;

	private BackendLogic logic = new BackendLogic();

    /**
     * this method returns the recyclable item name
     *
     * @return String
     */
    public String getItemName() {
		return itemName;
	}

    /**
     * this method sets the recyclable item name
     *
     * @param itemName
     * @return String
     */
    public void setItemName(String itemName) {
		this.itemName = itemName;
	}

    /**
     * this method returns the recyclable item type
     *
     * @return String
     */
    public String getItemType() {
		return itemType;
	}

    /**
     * this method sets the recyclable item name
     *
     * @param itemType 
     * @return String
     */
    public void setItemType(String itemType) {
		this.itemType = itemType;
	}

    /**
     * this method returns the recyclable item weight
     *
     * @return double
     */
    public double getItemWeight() {
		return itemWeight;
	}

    /**
     * this method sets the recyclable item weight
     *
     * @param itemWeight
     */
    public void setItemWeight(double itemWeight) {
		this.itemWeight = itemWeight;
	}

    /**
     * this method returns the recyclable item unit weight
     * 
     * @return double
     */
    public double getWeightUnit() {
		return itemWeightUnit;
	}

    /**
     * this method sets the recyclable item unit weight
     * 
     * @param itemWeightUnit
     */
    public void setWeightUnit(double itemWeightUnit) {
		this.itemWeightUnit = itemWeightUnit;
	}

    /**
     * this method returns the recyclable item unit price
     * 
     * @return double
     */
    public double getUnitPrice() {
		return unitPrice;
	}

    /**
     * this method sets the recyclable item unit price
     *
     * @param unitPrice
     */
    public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

    /**
     * this method adds the recyclable item to the database
     *
     * @param itemType
     * @param itemWeightUnit
     * @param unitPrice
     * @return boolean
     */
    public boolean addItem(String itemType, double itemWeightUnit,
			double unitPrice) {

		this.itemType = itemType;
		this.itemWeightUnit = itemWeightUnit;
		this.unitPrice = unitPrice;
		boolean addItem = logic.addItem(itemType, itemWeightUnit, unitPrice);

		return addItem;
	}
						
    /**
     * this method removes the recyclable item from the database
     * 
     * @param itemType
     * @return
     */
    public boolean removeItem(String itemType) {

		boolean deleteItem = logic.removeItem(itemType);
		return deleteItem;
	}

    /**
     * this method updates the price of the recyclable item to the database
     *
     * @param itemType
     * @param uPrice
     * @return boolean
     */
    public boolean updatePrice(String itemType, double uPrice) {

		boolean updatePrice = logic.updatePrice(itemType, uPrice);
		return updatePrice;
	}

    /**
     * this method fetches the list of recyclable items from the database
     *
     * @return Map
     */
    public Map<String, String> getItemList() {

		Map<String, String> itemList = logic.getItemList();
		return itemList;
		}


}
