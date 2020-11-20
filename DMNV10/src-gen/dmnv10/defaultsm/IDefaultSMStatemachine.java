/** Generated by YAKINDU Statechart Tools code generator. */
package dmnv10.defaultsm;

import dmnv10.IStatemachine;
import dmnv10.ITimerCallback;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public interface IDefaultSMStatemachine extends ITimerCallback,IStatemachine {
	public interface SCInterface {
	
		public void raiseAnyButton();
		
		public void raiseCoffee();
		
		public void raiseExpresso();
		
		public void raiseTea();
		
		public void raiseSoup();
		
		public void raiseIcedTea();
		
		public void raiseCinquanteCents();
		
		public void raiseVingtCinqCents();
		
		public void raiseDixCents();
		
		public void raiseBip();
		
		public void raiseCancel();
		
		public void raiseAddCup();
		
		public boolean isRaisedDoCoffee();
		
		public boolean isRaisedDoExpresso();
		
		public boolean isRaisedDoTea();
		
		public boolean isRaisedDoSoup();
		
		public boolean isRaisedDoIcedTea();
		
		public boolean isRaisedDoSugar();
		
		public boolean isRaisedDoErable();
		
		public boolean isRaisedDoCrouton();
		
		public boolean isRaisedDoSpices();
		
		public boolean isRaisedDoMilk();
		
		public boolean isRaisedDoGlace();
		
		public boolean isRaisedDoCancel();
		
		public boolean isRaisedDoReceipt();
		
		public boolean isRaisedDoPoor();
		
		public boolean isRaisedDoDosette();
		
		public boolean isRaisedDoGrain();
		
		public boolean isRaisedDoSachet();
		
		public boolean isRaisedDoSoupDose();
		
		public boolean isRaisedDoIcedTeaSachet();
		
		public boolean isRaisedDoGobelet();
		
		public boolean isRaisedDoHeat();
		
		public boolean isRaisedDoCool();
		
		public boolean isRaisedDoInfuse();
		
		public boolean isRaisedDoRetake();
		
		public boolean isRaisedDoFinish();
		
		public boolean isRaisedDoRestart();
		
		public boolean isRaisedDoWash();
		
		public String getMySelection();
		
		public void setMySelection(String value);
		
		public boolean getSelection();
		
		public void setSelection(boolean value);
		
		public boolean getPay();
		
		public void setPay(boolean value);
		
	public List<SCInterfaceListener> getListeners();
		public void setSCInterfaceOperationCallback(SCInterfaceOperationCallback operationCallback);
	
	}
	
	public interface SCInterfaceListener {
	
		public void onDoCoffeeRaised();
		public void onDoExpressoRaised();
		public void onDoTeaRaised();
		public void onDoSoupRaised();
		public void onDoIcedTeaRaised();
		public void onDoSugarRaised();
		public void onDoErableRaised();
		public void onDoCroutonRaised();
		public void onDoSpicesRaised();
		public void onDoMilkRaised();
		public void onDoGlaceRaised();
		public void onDoCancelRaised();
		public void onDoReceiptRaised();
		public void onDoPoorRaised();
		public void onDoDosetteRaised();
		public void onDoGrainRaised();
		public void onDoSachetRaised();
		public void onDoSoupDoseRaised();
		public void onDoIcedTeaSachetRaised();
		public void onDoGobeletRaised();
		public void onDoHeatRaised();
		public void onDoCoolRaised();
		public void onDoInfuseRaised();
		public void onDoRetakeRaised();
		public void onDoFinishRaised();
		public void onDoRestartRaised();
		public void onDoWashRaised();
		}
	
	public interface SCInterfaceOperationCallback {
	
		public String getSelection();
		
		public boolean isHot();
		
		public boolean isCool();
		
		public boolean isPay();
		
		public boolean isPoor();
		
		public boolean isTaken();
		
		public boolean isErable();
		
		public boolean isCrouton();
		
		public boolean isMilk();
		
		public boolean isGlace();
		
	}
	
	public SCInterface getSCInterface();
	
}
