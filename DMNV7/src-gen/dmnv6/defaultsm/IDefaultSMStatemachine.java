/** Generated by YAKINDU Statechart Tools code generator. */
package dmnv6.defaultsm;

import dmnv6.IStatemachine;
import dmnv6.ITimerCallback;
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

		public boolean isRaisedDoRestart();

		public boolean isRaisedDoCoffee();

		public boolean isRaisedDoExpresso();

		public boolean isRaisedDoTea();

		public boolean isRaisedDoSoup();

		public boolean isRaisedDoIcedTea();

		public boolean isRaisedDoSugar();

		public boolean isRaisedDoSelect();

		public boolean isRaisedDoPay();

		public boolean isRaisedDoCancel();

		public boolean isRaisedDoReceipt();

		public boolean isRaisedDoPoor();

		public boolean isRaisedDoDosette();

		public boolean isRaisedDoGrain();

		public boolean isRaisedDoSachet();

		public boolean isRaisedDoGobelet();

		public boolean isRaisedDoHeat();

		public boolean isRaisedDoInfuse();

		public boolean isRaisedDoRetake();

		public String getMySelection();

		public void setMySelection(String value);

		public boolean getSelection();

		public void setSelection(boolean value);

		public boolean getPay();

		public void setPay(boolean value);

		public boolean getHot();

		public void setHot(boolean value);

		public boolean getPoor();

		public void setPoor(boolean value);

		public boolean getTaken();

		public void setTaken(boolean value);

	public List<SCInterfaceListener> getListeners();
		public void setSCInterfaceOperationCallback(SCInterfaceOperationCallback operationCallback);

	}

	public interface SCInterfaceListener {

		public void onDoRestartRaised();
		public void onDoCoffeeRaised();
		public void onDoExpressoRaised();
		public void onDoTeaRaised();
		public void onDoSoupRaised();
		public void onDoIcedTeaRaised();
		public void onDoSugarRaised();
		public void onDoSelectRaised();
		public void onDoPayRaised();
		public void onDoCancelRaised();
		public void onDoReceiptRaised();
		public void onDoPoorRaised();
		public void onDoDosetteRaised();
		public void onDoGrainRaised();
		public void onDoSachetRaised();
		public void onDoGobeletRaised();
		public void onDoHeatRaised();
		public void onDoInfuseRaised();
		public void onDoRetakeRaised();
		}

	public interface SCInterfaceOperationCallback {

		public String getSelection();

		public boolean isHot();

		public boolean isPay();

		public boolean isPoor();

		public boolean isTaken();

	}

	public SCInterface getSCInterface();

}
