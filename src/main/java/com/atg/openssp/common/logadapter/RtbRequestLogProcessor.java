package com.atg.openssp.common.logadapter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Threadsafe logging of rtb bidrequest data with a {@link BlockingQueue}
 * 
 * @author André Schmer
 */
public class RtbRequestLogProcessor extends Thread {

	private static final Logger log = LoggerFactory.getLogger(RtbRequestLogProcessor.class);

	public static final RtbRequestLogProcessor instance = new RtbRequestLogProcessor();
	private final BlockingQueue<ParamMessage> logQueue = new ArrayBlockingQueue<>(1000, true);
	private boolean shuttingDown, loggerTerminated;

	private RtbRequestLogProcessor() {
		super.start();
	}

	@Override
	public void run() {
		try {
			while (shuttingDown == false) {
				final ParamMessage item = logQueue.take();
				LogFacade.logRtbRequest(item.getMessage(), item.getParams()); 
			}
		} catch (final InterruptedException e) {
			log.error(e.getMessage());
			loggerTerminated = true;
			Thread.currentThread().interrupt();
		}
	}
	
	public void setLogData(final Object object, final String... params) {
		if (shuttingDown || loggerTerminated) {
			return;
		}
		final ParamMessage paramMessage = new ParamMessage();
		paramMessage.setMessage(object.toString());
		paramMessage.setParams(params);
		try {
			logQueue.put(paramMessage);
		} catch (final InterruptedException e) {
			try {
				// try again
				logQueue.put(paramMessage);
			} catch (final InterruptedException ignore) {
				log.error("interrupted again, giving up. {}", e.getMessage());
				Thread.currentThread().interrupt();
			}
		}
	}

	/**
	 * Sets an indicator to shutdown this thread.
	 */
	public void shutDown() {
		shuttingDown = true;
		log.info("shutDown request received");
	}
}
