package fr.univcotedazur.polytech.si4.fsm.dm.v4;

import dmnv6.defaultsm.IDefaultSMStatemachine.SCInterfaceListener;

class DrinkingMachineInterfaceImplementation implements SCInterfaceListener {
		DrinkFactoryMachine theGui;
          public DrinkingMachineInterfaceImplementation(DrinkFactoryMachine dm) {theGui = dm; }
		@Override
		public void onDoRestartRaised() {
			theGui.doRestart();

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
		public void onDoSelectRaised() {
			theGui.doSelect();
		}
		@Override
		public void onDoPayRaised() {
			theGui.doPay();
		}
		@Override
		public void onDoCancelRaised() {
			theGui.doCancel();

		}
		@Override
		public void onDoReceiptRaised() {
          	theGui.doReceipt();
			// TODO Auto-generated method stub

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
		public void onDoGobeletRaised() {
			theGui.doGobelet();
		}
		@Override
		public void onDoHeatRaised() {
			theGui.doHeat();

		}
		@Override
		public void onDoInfuseRaised() {
			// TODO Auto-generated method stub

		}
		@Override
		public void onDoRetakeRaised() {
			// TODO Auto-generated method stub

		}

}
