package com.xy.asyc.test.temp;

import java.io.Serializable;

public abstract class AsynCallback implements Runnable, Serializable {

	private static final long serialVersionUID = 8336590534114781715L;

	public abstract void doNotify();

	@Override
	public void run() {
		doNotify();
	}

}
