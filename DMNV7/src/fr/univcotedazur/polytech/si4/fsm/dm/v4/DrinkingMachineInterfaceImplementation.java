package fr.univcotedazur.polytech.si4.fsm.dm.v4;

import dmnv6.defaultsm.IDefaultSMStatemachine.SCInterfaceListener;

class DrinkingMachineInterfaceImplementation implements SCInterfaceListener {
		DrinkFactoryMachine theGui;
          public DrinkingMachineInterfaceImplementation(DrinkFactoryMachine dm) {theGui = dm; }
		@Override
		public void onDoRestartRaised() {

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
		public void onDoPrepareRaised() {
			// TODO Auto-generated method stub

		}
		@Override
		public void onDoPayRaised() {
			theGui.doPay();
		}
		@Override
		public void onDoCancelRaised() {
			// TODO Auto-generated method stub

		}
		@Override
		public void onDoReceiptRaised() {
          	theGui.doReceipt();
			// TODO Auto-generated method stub

		}
		@Override
		public void onDoPoorRaised() {
			// TODO Auto-generated method stub

		}
		@Override
		public void onDoDosetteRaised() {
			// TODO Auto-generated method stub

		}
		@Override
		public void onDoGrainRaised() {
			// TODO Auto-generated method stub

		}
		@Override
		public void onDoSachetRaised() {
			// TODO Auto-generated method stub

		}
		@Override
		public void onDoGobeletRaised() {
			// TODO Auto-generated method stub

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
