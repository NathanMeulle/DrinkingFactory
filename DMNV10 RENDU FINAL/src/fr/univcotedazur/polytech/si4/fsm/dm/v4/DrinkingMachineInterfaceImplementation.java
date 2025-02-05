package fr.univcotedazur.polytech.si4.fsm.dm.v4;

import dmnv10.defaultsm.IDefaultSMStatemachine.SCInterfaceListener;

class DrinkingMachineInterfaceImplementation implements SCInterfaceListener {
	DrinkFactoryMachine theGui;

	public DrinkingMachineInterfaceImplementation(DrinkFactoryMachine dm) {
		theGui = dm;
	}

	@Override
	public void onDoRestartRaised() {
		theGui.doRestart();
	}

	@Override
	public void onDoWashRaised() {
		theGui.doWash();
	}

	@Override
	public void onDoCoffeeRaised() {
		theGui.doCoffee();
	}

	@Override
	public void onDoExpressoRaised() {
		theGui.doExpresso();
	}

	@Override
	public void onDoTeaRaised() {
		theGui.doTea();
	}

	@Override
	public void onDoSoupRaised() {
		theGui.doSoup();
	}

	@Override
	public void onDoIcedTeaRaised() {
		theGui.doIcedTea();
	}

	@Override
	public void onDoSugarRaised() {
		theGui.doSugar();
	}

	@Override
	public void onDoErableRaised() {
		theGui.doErable();
	}

	@Override
	public void onDoCroutonRaised() {
		theGui.doCrouton();
	}

	@Override
	public void onDoCancelRaised() {
		theGui.doCancel();

	}

	@Override
	public void onDoReceiptRaised() {
		theGui.doReceipt();

	}

	@Override
	public void onDoPoorRaised() {
		theGui.doPoor();

	}

	@Override
	public void onDoDosetteRaised() {
		theGui.doDosette();

	}

	@Override
	public void onDoGrainRaised() {
		theGui.doGrain();
	}

	@Override
	public void onDoSachetRaised() {
		theGui.doSachet();
	}

	@Override
	public void onDoSoupDoseRaised() {
		theGui.doSoupDose();

	}

	@Override
	public void onDoIcedTeaSachetRaised() {
		theGui.doIcedTeaSachet();

	}

	@Override
	public void onDoGobeletRaised() {
		theGui.doGobelet();
	}

	@Override
	public void onDoHeatRaised() {
		theGui.doHeat();
	}

	@Override
	public void onDoCoolRaised() {
		theGui.doCool();
	}

	@Override
	public void onDoInfuseRaised() {
		theGui.doInfuse();
	}

	@Override
	public void onDoRetakeRaised() {
		theGui.doRetake();
	}

	@Override
	public void onDoFinishRaised() {
		theGui.doFinish();
	}

	@Override
	public void onDoSpicesRaised() {
		theGui.doSpices();

	}

	@Override
	public void onDoMilkRaised() {
		theGui.doMilk();

	}

	@Override
	public void onDoGlaceRaised() {
		theGui.doGlace();

	}

}
