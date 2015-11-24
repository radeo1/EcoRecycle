package com.project.EcoRe;
import com.scu.logic.BackendLogic;
import java.sql.ResultSet;

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
	public boolean addItem(String itemType, double itemWeightUnit, double unitPrice) {
		// TODO Auto-generated method stub
		this.itemType = itemType;
                this.itemWeightUnit= itemWeightUnit;
                this.unitPrice = unitPrice;
                boolean addItem = logic.addItem(itemType, itemWeightUnit, unitPrice);
			
			return addItem;
	}
	public boolean removeItem(String itemType) {
		// TODO Auto-generated method stub
		boolean deleteItem = logic.removeItem(itemType);
                return deleteItem;
	}
	public boolean updatePrice(String itemType, double uPrice) {
		// TODO Auto-generated method stub
		boolean updatePrice = logic.updatePrice(itemType, uPrice);
                return updatePrice;
	}
	public ResultSet getItemList() {
		// TODO Auto-generated method stub
		ResultSet itemList = logic.getItemList();
                return itemList;
        }
	
	
	
}
