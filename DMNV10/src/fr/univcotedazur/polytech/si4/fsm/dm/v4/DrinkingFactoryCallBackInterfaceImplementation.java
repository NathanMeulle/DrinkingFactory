package fr.univcotedazur.polytech.si4.fsm.dm.v4;

import dmnv10.defaultsm.IDefaultSMStatemachine.SCInterfaceOperationCallback;

public class DrinkingFactoryCallBackInterfaceImplementation implements SCInterfaceOperationCallback {

	DrinkFactoryMachine theGui;
    public DrinkingFactoryCallBackInterfaceImplementation(DrinkFactoryMachine sw) {theGui = sw; }
	@Override
	public String getSelection() {
		return theGui.getSelection();
	}
	@Override
	public boolean isHot() {
		return theGui.isHot();
	}

	@Override
	public boolean isCool() {
		return theGui.isCool();
	}

	@Override
	public boolean isPay() {
		return theGui.isPay();
	}
	@Override
	public boolean isPoor() {
		return theGui.isPoor();
	}
	@Override
	public boolean isTaken() {
		return theGui.isTaken();
	}


}
