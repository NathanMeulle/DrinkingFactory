package fr.univcotedazur.polytech.si4.fsm.dm.v4;

import dmnv6.defaultsm.IDefaultSMStatemachine.SCInterfaceListener;

class DrinkingMachineInterfaceImplementation implements SCInterfaceListener {
		DrinkFactoryMachine theGui;
          public DrinkingMachineInterfaceImplementation(DrinkFactoryMachine dm) {theGui = dm; }
		@Override
		public void onDoRestartRaised() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onDoCoffeeRaised() {
			theGui.doCoffee();
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onDoExpressoRaised() {
			theGui.doExpresso();
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onDoTeaRaised() {
			theGui.doTea();

			// TODO Auto-generated method stub
			
		}
		@Override
		public void onDoSoupRaised() {
			theGui.doSoup();

			// TODO Auto-generated method stub
			
		}
		@Override
		public void onDoIcedTeaRaised() {
			theGui.doIcedTea();

			// TODO Auto-generated method stub
			
		}
		@Override
		public void onDoSugarRaised() {
			theGui.doSugar();

			// TODO Auto-generated method stub
			
		}
		@Override
		public void onDoSelectRaised() {
			theGui.doSelect();

			// TODO Auto-generated method stub
			
		}
		@Override
		public void onDoPrepareRaised() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onDoPayRaised() {
			theGui.doPay();

			// TODO Auto-generated method stub
			
		}
		@Override
		public void onDoCancelRaised() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onDoReceiptRaised() {
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
			// TODO Auto-generated method stub
			
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