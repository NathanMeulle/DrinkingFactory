package fr.univcotedazur.polytech.si4.fsm.dm.v4;

import dmnv6.defaultsm.IDefaultSMStatemachine.SCInterfaceOperationCallback;

public class DrinkingFactoryCallBackInterfaceImplementation implements SCInterfaceOperationCallback {

	DrinkFactoryMachine theGui;
    public DrinkingFactoryCallBackInterfaceImplementation(DrinkFactoryMachine sw) {theGui = sw; }
	@Override
	public String getSelection() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isHot() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isPay() {
		return theGui.isPay();
	}
	@Override
	public boolean isPoor() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isTaken() {
		// TODO Auto-generated method stub
		return false;
	}
    

}
