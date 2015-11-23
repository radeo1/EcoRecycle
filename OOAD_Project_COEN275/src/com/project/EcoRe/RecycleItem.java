package com.project.EcoRe;
import com.scu.logic.BackendLogic;
public class RecycleItem
{
	private String itemName;
	private String itemType;
	private double itemWeight, itemWeightUnit;
	private double unitPrice;
	
	private BackendLogic logic = new BackendLogic();
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public double getItemWeight() {
		return itemWeight;
	}
	public void setItemWeight(double itemWeight) {
		this.itemWeight = itemWeight;
	}
	public double getWeightUnit() {
		return itemWeightUnit;
	}
	public void setWeightUnit(double itemWeightUnit) {
		this.itemWeightUnit = itemWeightUnit;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String addItem(String itemType, int unitPrice) {
		// TODO Auto-generated method stub
		this.itemType = itemType;
                this.unitPrice = unitPrice;
                String addItem = logic.addItem(itemType, unitPrice);
		return addItem;
	}
	public String removeItem(String itemType) {
		// TODO Auto-generated method stub
		String deleteItem = logic.removeItem(itemType);
                return deleteItem;
	}
	public String updatePrice(String itemType, int uPrice) {
		// TODO Auto-generated method stub
		String updatePrice = logic.updatePrice(itemType, uPrice);
                return updatePrice;
	}
	public String getItemList() {
		// TODO Auto-generated method stub
		String itemList = logic.getItemList();
                return itemList;
        }
	
	
}
