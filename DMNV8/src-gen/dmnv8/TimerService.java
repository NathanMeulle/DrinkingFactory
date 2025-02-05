/** Generated by YAKINDU Statechart Tools code generator. */
package dmnv8;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Default timer service implementation.
 *
 */
public class TimerService implements ITimer {

	private final Timer timer = new Timer();
	
	private final List<TimeEventTask> timerTaskList = new ArrayList<TimeEventTask>();
	
	private final Lock lock = new ReentrantLock();
	
	/**
	 * Timer task that reflects a time event. It's internally used by
	 * {@link TimerService}.
	 *
	 */
	private class TimeEventTask extends TimerTask {
	
		private ITimerCallback callback;
	
		int eventID;
	
		/**
		 * Constructor for a time event.
		 *
		 * @param callback
		 *            : Object that implements ITimerCallback, is called
		 *            when the timer expires.
		 *
		 * @param eventID
		 *            : Index position within the state machine's timeEvent
		 *            array.
		 */
		public TimeEventTask(ITimerCallback callback, int eventID) {
			this.callback = callback;
			this.eventID = eventID;
		}
	
		public void run() {
			callback.timeElapsed(eventID);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof TimeEventTask) {
				return ((TimeEventTask) obj).callback.equals(callback)
						&& ((TimeEventTask) obj).eventID == eventID;
			}
			return super.equals(obj);
		}
		
		@Override
		public int hashCode() {
			int prime = 37;
			int result = 1;
			
			int c = (int) this.eventID;
			result = prime * result + c;
			c = this.callback.hashCode();
			result = prime * result + c;
			return result;
		}
		
	}
	
	public void setTimer(final ITimerCallback callback, final int eventID,
			long time, boolean isPeriodic) {
	
		// Create a new TimerTask for given event and store it.
		TimeEventTask timerTask = new TimeEventTask(callback, eventID);
		lock.lock();
		timerTaskList.add(timerTask);
	
		// start scheduling the timer
		if (isPeriodic) {
			timer.scheduleAtFixedRate(timerTask, time, time);
		} else {
			timer.schedule(timerTask, time);
		}
		lock.unlock();
	}
	
	public void unsetTimer(ITimerCallback callback, int eventID) {
		lock.lock();
		int index = timerTaskList.indexOf(new TimeEventTask(callback, eventID));
		if (index != -1) {
			timerTaskList.get(index).cancel();
			timer.purge();
			timerTaskList.remove(index);
		}
		lock.unlock();
	}
	
	/**
	 * Cancel timer service. Use this to end possible timing threads and free
	 * memory resources.
	 */
	public void cancel() {
		lock.lock();
		timer.cancel();
		timer.purge();
		lock.unlock();
	}
}

